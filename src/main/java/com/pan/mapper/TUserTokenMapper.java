package com.pan.mapper;

import com.pan.model.TUserToken;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TUserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUserToken record);

    int insertSelective(TUserToken record);

    TUserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUserToken record);

    int updateByPrimaryKey(TUserToken record);

    TUserToken selectTokenByUserId(Long userId);

    void updateByUserId(TUserToken userToken);
}