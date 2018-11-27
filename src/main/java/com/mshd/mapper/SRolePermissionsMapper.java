package com.mshd.mapper;

import com.mshd.model.SRolePermissions;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SRolePermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SRolePermissions record);

    int insertSelective(SRolePermissions record);

    SRolePermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SRolePermissions record);

    int updateByPrimaryKey(SRolePermissions record);

    SRolePermissions selectByObj(SRolePermissions sRolePermissions);

    void deleteByRoleId(Long roleId);
}