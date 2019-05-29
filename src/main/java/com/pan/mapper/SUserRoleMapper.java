package com.pan.mapper;

import com.pan.model.SUserRole;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SUserRole record);

    int insertSelective(SUserRole record);

    SUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SUserRole record);

    int updateByPrimaryKey(SUserRole record);

    SUserRole selectByUserId(Long userId);

    void deleteByUserId(Long userId);
}