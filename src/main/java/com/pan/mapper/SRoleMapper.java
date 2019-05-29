package com.pan.mapper;

import com.pan.model.SRole;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SRole record);

    int insertSelective(SRole record);

    SRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SRole record);

    int updateByPrimaryKey(SRole record);

    List<SRole> getRoleList(SRole sRole);

    Integer getRoleListCount(SRole sRole);
}