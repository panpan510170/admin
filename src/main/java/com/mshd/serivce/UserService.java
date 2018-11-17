package com.mshd.serivce;

import com.mshd.vo.regist.RegistVO;
import com.mshd.vo.user.UserVO;

/**
 * Created by Pangaofeng on 2018/9/11
 */
public interface UserService {
    UserVO login(String userName, String password) throws Exception;

    UserVO regist(RegistVO registVO) throws Exception;

    Boolean userNameOnly(String userName) throws Exception;
}
