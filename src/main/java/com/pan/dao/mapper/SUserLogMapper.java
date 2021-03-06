package com.pan.dao.mapper;

import com.pan.model.entitys.system.SUserLog;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SUserLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SUserLog record);

    int insertSelective(SUserLog record);

    SUserLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SUserLog record);

    int updateByPrimaryKey(SUserLog record);
}