package com.pan.serivce;


import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.enums.UserStatusEnum;
import com.pan.base.ex.BOException;
import com.pan.base.handler.MapHandler;
import com.pan.base.util.JwtUtils;
import com.pan.base.util.MD5Utils;
import com.pan.base.util.QueryResult;
import com.pan.dao.mapper.*;
import com.pan.dao.repository.system.SUserLogRepository;
import com.pan.model.entitys.system.*;
import com.pan.model.vo.system.PermissionsVO;
import com.pan.model.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Pangaofeng on 2018/11/20
 */
@Service
public class SystemService {

    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private SRoleMapper sRoleMapper;

    @Autowired
    private SPermissionsMapper sPermissionsMapper;

    @Autowired
    private SUserRoleMapper sUserRoleMapper;

    @Autowired
    private SRolePermissionsMapper sRolePermissionsMapper;

    @Autowired
    private SUserTokenMapper sUserTokenMapper;

    @Autowired
    private SUserLogRepository sUserLogRepository;


    @Transactional
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
            userVO.setUserId(user.getId());
            userVO.setUserName(user.getUserName());
            userVO.setToken(token);
        }else{
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"用户名不存在");
        }
        return userVO;
    }

    public QueryResult getUserList(Map paramMap) throws Exception{
        QueryResult queryResult = new QueryResult();

        SUser sUser  = (SUser) MapHandler.map2Bean(SUser.class,paramMap);

        List<SUser> sUserList = sUserMapper.getUserList(sUser);
        Integer count = sUserMapper.getUserListCount(sUser);

        queryResult.setRows(sUserList);
        queryResult.setTotal(count);
        return queryResult;
    }

    public QueryResult getRoleList(Map paramMap) throws Exception{
        QueryResult queryResult = new QueryResult();

        SRole sRole  = (SRole) MapHandler.map2Bean(SRole.class,paramMap);

        List<SRole> sRoleList = sRoleMapper.getRoleList(sRole);
        Integer count = sRoleMapper.getRoleListCount(sRole);

        queryResult.setRows(sRoleList);
        queryResult.setTotal(count);
        return queryResult;
    }

    public QueryResult getPermissionsList(Map paramMap) throws Exception {
        QueryResult queryResult = new QueryResult();

        SPermissions sPermissions  = (SPermissions) MapHandler.map2Bean(SPermissions.class,paramMap);

        List<SPermissions> sPermissionsList = sPermissionsMapper.getPermissionsList(sPermissions);
        Integer count = sPermissionsMapper.getPermissionsListCount(sPermissions);

        queryResult.setRows(sPermissionsList);
        queryResult.setTotal(count);
        return queryResult;
    }

    @Transactional
    public void addPermissions(Map paramMap) throws Exception {
        SPermissions sPermissions  = (SPermissions) MapHandler.map2Bean(SPermissions.class,paramMap);

        if(null == sPermissions) throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());

        if(null == sPermissions.getPermissionsName()) throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());

        if(null == sPermissions.getType()) throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());

        if(null == sPermissions.getSerialNumber()) throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());

        sPermissions.setSerialNumber(sPermissions.getSerialNumber());
        sPermissions.setCreateTime(new Date());
        sPermissions.setUpdateTime(new Date());
        sPermissionsMapper.insertSelective(sPermissions);
    }

    public List<PermissionsVO> userPermissionsList(Long userId) throws Exception {
        List<PermissionsVO> list = new ArrayList<>();

        SPermissions firstPermissions = new SPermissions();
        firstPermissions.setType(1);
        firstPermissions.setUserId(userId);
        List<SPermissions> sPermissionsList = sPermissionsMapper.userPermissionsList(firstPermissions);

        for(SPermissions sPermissions : sPermissionsList){
            PermissionsVO firstPermissionsVO = new PermissionsVO();

            SPermissions secondPermissions = new SPermissions();
            secondPermissions.setType(2);
            secondPermissions.setUserId(userId);
            secondPermissions.setParentId(sPermissions.getId());
            List<SPermissions> secondPermissionslist = sPermissionsMapper.userPermissionsList(secondPermissions);

            List<PermissionsVO> secondList = new ArrayList<>();
            for(SPermissions sp : secondPermissionslist){
                PermissionsVO secondPermissionsVO = new PermissionsVO();

                secondPermissionsVO.setName(sp.getPermissionsName());
                secondPermissionsVO.setUrl(sp.getPermissionsUrl());
                secondPermissionsVO.setImageUrl(sp.getPermissionsImageUrl());
                secondList.add(secondPermissionsVO);
            }
            firstPermissionsVO.setName(sPermissions.getPermissionsName());
            firstPermissionsVO.setUrl(sPermissions.getPermissionsUrl());
            firstPermissionsVO.setImageUrl(sPermissions.getPermissionsImageUrl());
            firstPermissionsVO.setList(secondList);
            list.add(firstPermissionsVO);
        }
        return list;
    }

    public SUserToken selectTokenByUserId(Long userId) throws Exception{
        return sUserTokenMapper.selectTokenByUserId(userId);
    }

    public QueryResult getUserRoleList(Map paramMap) throws Exception {
        QueryResult queryResult = new QueryResult();
        SUser sUser  = (SUser) MapHandler.map2Bean(SUser.class,paramMap);
        List<SUser> sUserList = sUserMapper.getUserRoleList(sUser);
        Integer count = sUserMapper.getUserRoleListCount(sUser);
        queryResult.setRows(sUserList);
        queryResult.setTotal(count);
        return queryResult;
    }

    public List<Map<String, Object>> rolePermissionsTreeList(Map<String, Object> paramMap) throws Exception {
        if(null == paramMap.get("roleId")) {
            throw new BOException(ResultCodeEnum.paramError.getId(),"角色id必传");
        }
        if("".equals(paramMap.get("roleId").toString())) {
            throw new BOException(ResultCodeEnum.paramError.getId(),"角色id必传");
        }
        Long roleId = Long.parseLong(paramMap.get("roleId").toString());
        //查询一级菜单
        List<Map<String, Object>> listmap = new ArrayList<>();
        SPermissions firstPermissions = new SPermissions();
        firstPermissions.setType(1);
        List<SPermissions> allPermissionsList = sPermissionsMapper.rolePermissionsTreeList(firstPermissions);
        Map<String, Object> stateMap = new HashMap<>();
        stateMap.put("checked",true);//默认勾选
        stateMap.put("expanded",true);//节点默认展开
        Map<String, Object> expandedMap = new HashMap<>();
        expandedMap.put("expanded",true);//节点默认展开
        //所有一级
        for(SPermissions sPermissions : allPermissionsList){
            Map<String, Object> map = new HashMap<>();
            //查询二级菜单
            SPermissions secondPermissions = new SPermissions();
            secondPermissions.setType(2);
            secondPermissions.setParentId(sPermissions.getId());
            List<SPermissions> secondPermissionslist = sPermissionsMapper.rolePermissionsTreeList(secondPermissions);
            //所有二级
            List<Map<String, Object>> secondList = new ArrayList<>();
            for(SPermissions sp : secondPermissionslist){
                Map<String, Object> secondMap = new HashMap<>();
                //查询三级按钮
                SPermissions thirdPermissions = new SPermissions();
                thirdPermissions.setType(3);
                thirdPermissions.setParentId(sp.getId());
                List<SPermissions> thirdPermissionslist = sPermissionsMapper.rolePermissionsTreeList(thirdPermissions);
                List<Map<String, Object>> thirdList = new ArrayList<>();
                for(SPermissions thirdSp : thirdPermissionslist){
                    Map<String, Object> thirdMap = new HashMap<>();
                    thirdMap.put("id",thirdSp.getId());
                    thirdMap.put("text",thirdSp.getPermissionsName());
                    SRolePermissions thirdRolePermissions = new SRolePermissions();
                    thirdRolePermissions.setRoleId(roleId);
                    thirdRolePermissions.setPermissionsId(thirdSp.getId());
                    SRolePermissions res = sRolePermissionsMapper.selectByObj(thirdRolePermissions);
                    if(null != res) {//默认勾选
                        thirdMap.put("state",stateMap);
                    }
                    thirdList.add(thirdMap);
                }
                secondMap.put("id",sp.getId());
                secondMap.put("text",sp.getPermissionsName());
                SRolePermissions sRolePermissions = new SRolePermissions();
                sRolePermissions.setRoleId(roleId);
                sRolePermissions.setPermissionsId(sp.getId());
                SRolePermissions rolePermissions = sRolePermissionsMapper.selectByObj(sRolePermissions);
                if(null != rolePermissions) {//默认勾选
                    secondMap.put("state",stateMap);
                }else{
                    secondMap.put("state",expandedMap);//节点默认展开
                }
                secondMap.put("nodes",thirdList);
                secondList.add(secondMap);
            }
            map.put("id",sPermissions.getId());
            map.put("text",sPermissions.getPermissionsName());
            SRolePermissions sRolePermissions = new SRolePermissions();
            sRolePermissions.setRoleId(roleId);
            sRolePermissions.setPermissionsId(sPermissions.getId());
            SRolePermissions rolePermissions = sRolePermissionsMapper.selectByObj(sRolePermissions);
            if(null != rolePermissions) {
                map.put("state", stateMap);//默认勾选
            }else{
                map.put("state", expandedMap);//节点默认展开
            }
            map.put("nodes",secondList);
            listmap.add(map);
        }
        return listmap;
    }

    @Transactional
    public void saveRolePermissionsTree(Map<String,Object> paramMap) throws Exception {
        if(null == paramMap.get("roleId")) {
            throw new BOException(ResultCodeEnum.paramError.getId(),"角色id必传");
        }
        if("".equals(paramMap.get("roleId").toString())) {
            throw new BOException(ResultCodeEnum.paramError.getId(),"角色id必传");
        }
        Long roleId = Long.parseLong(paramMap.get("roleId").toString());
        //删除原有绑定角色权限
        sRolePermissionsMapper.deleteByRoleId(roleId);
        //保存
        if(null != paramMap.get("rolePermissionsList")) {
            List<Map<String,Object>> rolePermissionsList = (List<Map<String,Object>>)paramMap.get("rolePermissionsList");
            for (Map<String,Object> map : rolePermissionsList) {
                Integer id = (Integer) map.get("id");
                SRolePermissions sRolePermissions = new SRolePermissions();
                sRolePermissions.setRoleId(roleId);
                sRolePermissions.setPermissionsId(Long.parseLong(id+""));
                sRolePermissionsMapper.insertSelective(sRolePermissions);
            }
        }
    }

    @Transactional
    public void delPermissions(Map paramMap) throws Exception {
        if(null == paramMap.get("id")) {
            throw new BOException(ResultCodeEnum.paramError.getId(),"id必传");
        }
        if("".equals(paramMap.get("id").toString())) {
            throw new BOException(ResultCodeEnum.paramError.getId(),"id必传");
        }
        Long id = Long.parseLong(paramMap.get("id").toString());
        sPermissionsMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void addSystemUser(Map paramMap) throws Exception {
        SUser sUser  = (SUser) MapHandler.map2Bean(SUser.class,paramMap);
        if(null == sUser) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sUser.getUserName()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sUser.getPhone()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        //初始密码
        sUser.setPassword(MD5Utils.md5(MD5Utils.md5(sUser.getUserName().trim())));
        sUser.setStatus(UserStatusEnum.normal.getId());
        sUser.setCreateTime(new Date());
        sUserMapper.insertSelective(sUser);
        //封装token加密参数
       /* TUser user = new TUser();
        user.setId(sUser.getId());
        String token = JwtUtils.encode(user,60*1000*60);*/
        //保存token
       /* SUserToken sUserToken = new SUserToken();
        sUserToken.setUserId(user.getId());
        sUserToken.setToken(token);
        sUserToken.setCreateTime(new Date());
        sUserTokenMapper.insertSelective(sUserToken);*/
    }

    @Transactional
    public void addRole(Map paramMap) throws Exception {
        SRole sRole  = (SRole) MapHandler.map2Bean(SRole.class,paramMap);
        if(null == sRole) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sRole.getRoleName()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sRole.getDescrition()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        sRole.setCreateTime(new Date());
        sRoleMapper.insertSelective(sRole);
    }

    @Transactional
    public void updateSystemUser(Map paramMap) throws Exception {
        SUser sUser  = (SUser) MapHandler.map2Bean(SUser.class,paramMap);
        if(null == sUser) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sUser.getId()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sUser.getPassword()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        sUser.setPassword(MD5Utils.md5(MD5Utils.md5(sUser.getPassword().trim())));
        sUserMapper.updateByPrimaryKeySelective(sUser);
    }

    public List<SRole> getRoleAllList(Map paramMap) throws Exception {
        //SRole sRole  = (SRole) BeanHandler.Map2Bean(SRole.class,paramMap);
        SRole sRole = new SRole();
        List<SRole> sRoleList = sRoleMapper.getRoleList(sRole);
        return sRoleList;
    }

    @Transactional
    public void saveUserRole(Map paramMap) throws Exception {
        SUserRole sUserRole  = (SUserRole) MapHandler.map2Bean(SUserRole.class,paramMap);
        if(null == sUserRole) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sUserRole.getUserId()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        if(null == sUserRole.getRoleId()) {
            throw new BOException(ResultCodeEnum.paramError.getId(),ResultCodeEnum.paramError.getName());
        }
        //SUserRole userRole = sUserRoleMapper.selectByUserId(sUserRole.getUserId());
        sUserRoleMapper.deleteByUserId(sUserRole.getUserId());
        sUserRoleMapper.insertSelective(sUserRole);
    }

    /**
     * 根据用户名查询系统用户
     * @param userName  用户名
     * @return
     */
    public SUser getUserByUserName(String userName) {
        SUser sUser = new SUser();
        sUser.setUserName(userName.trim());
        return sUserMapper.selectSUser(sUser);
    }

    public String getRoleByUserName(String userName) {
        return "管理员";
    }

    /**
     * 查询用户的所有权限路径
     * @param userId 用户id
     * @return
     */
    public List<SPermissions> getUserPermissionsAllList(Long userId) {
        SPermissions sPermissions = new SPermissions();
        sPermissions.setUserId(userId);
        return sPermissionsMapper.getUserPermissionsAllList(sPermissions);
    }

    /**
     * 保存系统用户操作日志
     * @param sUserLog
     */
    public void saveSyetemUserOptLog(SUserLog sUserLog) {
        sUserLogRepository.save(sUserLog);
    }

    /**
     * 获取所有菜单
     * @return
     */
    public List<SPermissions> getPermissionsAll() {
        return sPermissionsMapper.getPermissionsAll();
    }
}
