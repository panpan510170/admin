package com.mshd.mapper;

import com.mshd.model.SUserToken;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SUserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SUserToken record);

    int insertSelective(SUserToken record);

    SUserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SUserToken record);

    int updateByPrimaryKey(SUserToken record);

    void updateByUserId(SUserToken sUserToken);

    SUserToken selectTokenByUserId(Long userId);
}