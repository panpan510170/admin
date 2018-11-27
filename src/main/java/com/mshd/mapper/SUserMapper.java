package com.mshd.mapper;

import com.mshd.model.SUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SUser record);

    int insertSelective(SUser record);

    SUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SUser record);

    int updateByPrimaryKey(SUser record);

    List<SUser> getUserList(SUser sUser);

    Integer getUserListCount(SUser sUser);

    SUser selectSUser(SUser sUser);

    List<SUser> getUserRoleList(SUser sUser);

    Integer getUserRoleListCount(SUser sUser);
}