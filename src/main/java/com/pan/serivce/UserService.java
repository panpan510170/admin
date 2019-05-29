package com.pan.serivce;

import com.pan.model.TUser;
import com.pan.model.TUserToken;
import com.pan.vo.regist.RegistVO;
import com.pan.vo.user.UserVO;

/**
 * Created by Pangaofeng on 2018/9/11
 */
public interface UserService {
    UserVO login(String userName, String password) throws Exception;

    UserVO regist(RegistVO registVO) throws Exception;

    Boolean userNameOnly(String userName) throws Exception;

    TUser getUser(Long id);

    TUserToken selectTokenByUserId(Long id);
}
