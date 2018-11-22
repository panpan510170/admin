package com.mshd.serivce;

import com.mshd.model.SUser;
import com.mshd.util.QueryResult;
import com.mshd.vo.system.UserParamVO;
import com.mshd.vo.user.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/20
 */
public interface SystemService {

    UserVO login(String userName, String password) throws Exception;

    QueryResult getUserList(Map paramMap) throws Exception;
}
