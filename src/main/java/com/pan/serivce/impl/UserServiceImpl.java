package com.pan.serivce.impl;

import com.pan.enums.ResultCodeEnum;
import com.pan.enums.UserStatusEnum;
import com.pan.ex.BOException;
import com.pan.mapper.TUserMapper;
import com.pan.mapper.TUserTokenMapper;
import com.pan.model.TUser;
import com.pan.model.TUserToken;
import com.pan.serivce.UserService;
import com.pan.util.JwtUtils;
import com.pan.util.MD5Utils;
import com.pan.vo.regist.RegistVO;
import com.pan.vo.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Pangaofeng on 2018/9/11
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TUserTokenMapper tUserTokenMapper;

    @Override
    public UserVO login(String userName, String password) throws Exception{
        UserVO userVO = new UserVO();
        TUser tUser = new TUser();
        tUser.setUserName(userName.trim());
        TUser user = tUserMapper.selectUser(tUser);
        if(null != user){
            if(UserStatusEnum.normal.getId() == user.getStatus()){
                String s = MD5Utils.md5(MD5Utils.md5(password.trim()));
                if(!s.equals(user.getPassword())) throw new BOException(ResultCodeEnum.bussinessError.getId(),"密码不正确");
            }else{
                throw new BOException(ResultCodeEnum.bussinessError.getId(),"您的账号已被停用,请联系管理员");
            }

            //封装token加密参数
            TUser user1 = new TUser();
            user1.setId(user.getId());
            String token = JwtUtils.encode(user1,60*1000*60);

            //保存token
            TUserToken userToken = new TUserToken();
            userToken.setUserId(user.getId());
            userToken.setToken(token);
            tUserTokenMapper.updateByUserId(userToken);

            //封装返回参数
            userVO.setUserName(user.getUserName());
            userVO.setToken(token);
        }else{
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"用户名不存在");
        }
        return userVO;
    }

    @Override
    @Transactional
    public UserVO regist(RegistVO registVO) throws Exception{

        TUser tUser = new TUser();
        tUser.setUserName(registVO.getUserName().trim());
        tUser.setPassword(MD5Utils.md5(MD5Utils.md5(registVO.getPassword().trim())));
        tUser.setStatus(UserStatusEnum.normal.getId());
        tUser.setRegTime(new Date());
        int i = tUserMapper.insertSelective(tUser);
        if(i <= 0) throw new BOException(ResultCodeEnum.bussinessError.getId(),"注册失败,请稍后再试或联系管理员");

        //封装token加密参数
        TUser user = new TUser();
        user.setId(tUser.getId());
        String token = JwtUtils.encode(user,60*1000*60);

        //保存token
        TUserToken userToken = new TUserToken();
        userToken.setUserId(user.getId());
        userToken.setToken(token);
        userToken.setCreateTime(new Date());
        tUserTokenMapper.insertSelective(userToken);

        //封装返回参数
        UserVO userVO = new UserVO();
        userVO.setToken(token);
        userVO.setUserName(tUser.getUserName());
        return userVO;
    }

    @Override
    public Boolean userNameOnly(String userName)throws Exception{
        Boolean flag = true;
        TUser tUser = new TUser();
        tUser.setUserName(userName);
        Integer count = tUserMapper.selectUserCount(tUser);
        if(count > 0 ) flag = false ;
        return flag;
    }

    @Override
    public TUser getUser(Long userId) {
        return tUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public TUserToken selectTokenByUserId(Long userId) {
        return tUserTokenMapper.selectTokenByUserId(userId);
    }
}
