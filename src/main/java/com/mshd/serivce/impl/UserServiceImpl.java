package com.mshd.serivce.impl;

import com.mshd.model.User;
import com.mshd.serivce.UserService;
import com.mshd.util.GenerateNO;
import com.mshd.util.JwtUtils;
import com.mshd.vo.Constants;
import com.mshd.vo.RedisKeys;
import com.mshd.vo.user.UserRegistVO;
import com.mshd.vo.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Pangaofeng on 2018/9/11
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean regist(UserRegistVO userRegistVO) throws Exception {
        User user = new User();
        user.setUserNo(GenerateNO.getPrefixNO(Constants.userPrefix));
        user.setUserName(userRegistVO.getUserName());
        user.setPassWord(userRegistVO.getPassword());
        redisTemplate.opsForValue().set(userRegistVO.getUserName() + userRegistVO.getPassword(),user);
        return true;
    }

    @Override
    public UserVO login(String userName, String password) {
        UserVO userVO = new UserVO();

        User user = (User)redisTemplate.opsForValue().get(userName + password);

        String token = JwtUtils.encode(user,60*60*60*6);

        if(null != user){
            BeanUtils.copyProperties(user, userVO);
            userVO.setToken(token);
        }
        return userVO;
    }
}
