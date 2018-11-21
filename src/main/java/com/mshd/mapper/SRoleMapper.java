package com.mshd.mapper;

import com.mshd.model.SRole;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SRole record);

    int insertSelective(SRole record);

    SRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SRole record);

    int updateByPrimaryKey(SRole record);
}