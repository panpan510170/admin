package com.mshd.serivce;

import com.mshd.vo.user.UserRegistVO;
import com.mshd.vo.user.UserVO;

/**
 * Created by Pangaofeng on 2018/9/11
 */
public interface UserService {
    Boolean regist(UserRegistVO userRegistVO) throws Exception;

    UserVO login(String userName, String password);
}
