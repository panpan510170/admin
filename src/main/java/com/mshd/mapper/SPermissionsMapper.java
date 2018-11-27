package com.mshd.mapper;

import com.mshd.model.SPermissions;
import com.mshd.model.SRolePermissions;
import com.mshd.vo.PermissionsVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SPermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SPermissions record);

    int insertSelective(SPermissions record);

    SPermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SPermissions record);

    int updateByPrimaryKey(SPermissions record);

    List<SPermissions> getPermissionsList(SPermissions sPermissions);

    Integer getPermissionsListCount(SPermissions sPermissions);

    Integer getMaxPermissions(Integer type);

    List<SPermissions> userPermissionsList(SPermissions sPermissions);

    List<SPermissions> rolePermissionsTreeList(SPermissions firstPermissions);

}