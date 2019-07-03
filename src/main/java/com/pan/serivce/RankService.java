package com.pan.serivce;

import com.pan.entitys.rank.CoreRank;
import com.pan.mapper.TCoreRankMapper;
import com.pan.repository.CoreRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 排行事物层
 * @author pan
 * @date 2019/7/3 18:21
 */
@Service
public class RankService {
    @Autowired
    private CoreRankRepository coreRankRepository;
    @Autowired
    private TCoreRankMapper tCoreRankMapper;

    public void save(CoreRank coreRank) {
        coreRankRepository.save(coreRank);
    }

    public List<CoreRank> getRankList(CoreRank coreRank) {
        return tCoreRankMapper.getRankList(coreRank);
    }
}
