package com.pan.repository;

import com.pan.entitys.Test;
import com.pan.entitys.rank.CoreRank;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data-jpa 映射使用
 * @date 2019/6/20 18:19
 */
public interface RankRepository extends JpaRepository<CoreRank,Long> {
}
