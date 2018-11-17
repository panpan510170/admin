package com.mshd.mapper;

import com.mshd.model.TUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    Integer selectUserCount(TUser tUser);

    TUser selectUser(TUser tUser);
}