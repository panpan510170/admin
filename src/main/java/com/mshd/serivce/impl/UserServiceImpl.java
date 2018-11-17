package com.mshd.serivce.impl;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.enums.UserStatusEnum;
import com.mshd.ex.BOException;
import com.mshd.mapper.TUserMapper;
import com.mshd.model.TUser;
import com.mshd.serivce.UserService;
import com.mshd.util.JwtUtils;
import com.mshd.util.MD5Utils;
import com.mshd.vo.regist.RegistVO;
import com.mshd.vo.user.UserVO;
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
        }else{
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"用户名不存在");
        }
        userVO.setUserName(user.getUserName());
        userVO.setToken(JwtUtils.encode(user,60*1000*60));
        return userVO;
    }

    @Override
    @Transactional
    public UserVO regist(RegistVO registVO) throws Exception{
        Boolean flag = true;
        UserVO userVO = new UserVO();
        TUser tUser = new TUser();
        tUser.setUserName(registVO.getUserName().trim());
        tUser.setPassword(MD5Utils.md5(MD5Utils.md5(registVO.getPassword().trim())));
        tUser.setStatus(UserStatusEnum.normal.getId());
        tUser.setRegTime(new Date());
        int i = tUserMapper.insertSelective(tUser);
        if(i <= 0) throw new BOException(ResultCodeEnum.bussinessError.getId(),"注册失败,请稍后再试或联系管理员");
        TUser user = tUserMapper.selectUser(tUser);
        userVO.setUserName(user.getUserName());
        userVO.setToken(JwtUtils.encode(user,60*1000*60));
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
}
