package com.pan.repository;

import com.pan.entitys.rank.CoreRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring data-jpa 映射使用
 * @date 2019/6/20 18:19
 */
public interface RankRepository extends JpaRepository<CoreRank,Long> {

    @Query(value = "SELECT t.id,t.basics,t.clear_time,t.create_time,t.demension_type,t.end_time,t.name,t.start_time FROM t_core_rank t WHERE t.start_time <= :coreRank.startTime and t.end_time >= :coreRank.endTime")
    List<CoreRank> getRankList(CoreRank coreRank);
}
