package com.mshd.serivce;


import com.mshd.model.SRole;
import com.mshd.model.SUserToken;
import com.mshd.util.QueryResult;
import com.mshd.vo.PermissionsVO;
import com.mshd.vo.user.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/20
 */
public interface SystemService {

    UserVO login(String userName, String password) throws Exception;

    QueryResult getUserList(Map paramMap) throws Exception;

    QueryResult getRoleList(Map paramMap) throws Exception;

    QueryResult getPermissionsList(Map paramMap) throws Exception;

    void addPermissions(Map paramMap) throws Exception;

    List<PermissionsVO> userPermissionsList(Long userId) throws Exception;

    SUserToken selectTokenByUserId(Long id) throws Exception;

    QueryResult getUserRoleList(Map paramMap) throws Exception;

    List<Map<String, Object>> rolePermissionsTreeList(Map<String, Object> paramMap) throws Exception;

    void saveRolePermissionsTree(Map<String,Object> paramMap) throws Exception;

    void delPermissions(Map paramMap) throws Exception;

    void addSystemUser(Map paramMap) throws Exception;

    void addRole(Map paramMap) throws Exception;

    void updateSystemUser(Map paramMap) throws Exception;

    List<SRole> getRoleAllList(Map paramMap) throws Exception;

    void saveUserRole(Map paramMap) throws Exception;
}
