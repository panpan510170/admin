package com.pan.dao.repository.skills.limit;

import com.pan.model.entitys.skills.limit.LimitItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pan
 * @date 2019/7/3 14:35
 */
public interface LimitItemRepository extends JpaRepository<LimitItem,Long>{
     List<LimitItem> LimitIdEquals(Long id);
}
