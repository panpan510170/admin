package com.pan.dao.mapper.skills.limit;

import com.pan.model.entitys.skills.limit.CoreLimit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pan
 * @date 2019/7/1 14:15
 */
@Mapper
@Repository
public interface TCoreLimitMapper {

    List<CoreLimit> getLimitList(CoreLimit coreLimit);
}
