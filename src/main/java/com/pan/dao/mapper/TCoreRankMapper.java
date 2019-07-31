package com.pan.dao.mapper;

import com.pan.model.entitys.skills.rank.CoreRank;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pan
 * @date 2019/7/1 14:15
 */
@Mapper
@Repository
public interface TCoreRankMapper{

    List<CoreRank> getRankList(CoreRank coreRank);
}
