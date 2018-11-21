package com.mshd.mapper;

import com.mshd.model.SPermissions;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SPermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SPermissions record);

    int insertSelective(SPermissions record);

    SPermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SPermissions record);

    int updateByPrimaryKey(SPermissions record);
}