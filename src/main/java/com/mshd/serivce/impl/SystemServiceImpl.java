package com.mshd.serivce.impl;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.enums.UserStatusEnum;
import com.mshd.ex.BOException;
import com.mshd.mapper.SUserMapper;
import com.mshd.mapper.SUserTokenMapper;
import com.mshd.model.SUser;
import com.mshd.model.SUserToken;
import com.mshd.model.TUser;
import com.mshd.model.TUserToken;
import com.mshd.serivce.SystemService;
import com.mshd.util.*;
import com.mshd.vo.system.UserParamVO;
import com.mshd.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/20
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private SUserTokenMapper sUserTokenMapper;

    @Override
    public UserVO login(String userName, String password) throws Exception{
        UserVO userVO = new UserVO();
        SUser sUser = new SUser();
        sUser.setUserName(userName.trim());
        SUser user = sUserMapper.selectSUser(sUser);
        if(null != user){
            if(UserStatusEnum.normal.getId() == user.getStatus()){
                String s = MD5Utils.md5(MD5Utils.md5(password.trim()));
                if(!s.equals(user.getPassword())) throw new BOException(ResultCodeEnum.bussinessError.getId(),"密码不正确");
            }else{
                throw new BOException(ResultCodeEnum.bussinessError.getId(),"您的账号已被停用,请联系管理员");
            }

            //封装token加密参数
            SUser sUser1 = new SUser();
            sUser1.setId(user.getId());
            String token = JwtUtils.encode(sUser1,60*1000*60);

            //保存token
            SUserToken sUserToken = new SUserToken();
            sUserToken.setUserId(user.getId());
            sUserToken.setToken(token);
            sUserTokenMapper.updateByUserId(sUserToken);

            //封装返回参数
            userVO.setUserName(user.getUserName());
            userVO.setToken(token);
        }else{
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"用户名不存在");
        }
        return userVO;
    }

    @Override
    public QueryResult getUserList(Map paramMap) throws Exception{
        QueryResult queryResult = new QueryResult();

        SUser sUser  = (SUser) BeanMapUtil.Map2Bean(SUser.class,paramMap);

        List<SUser> sUserList = sUserMapper.getUserList(sUser);
        Integer count = sUserMapper.getUserListCount(sUser);

        queryResult.setRows(sUserList);
        queryResult.setTotal(count);
        return queryResult;
    }
}
