package com.pan.serivce.skills.limit;

import com.pan.dao.mapper.skills.limit.TCoreLimitMapper;
import com.pan.dao.repository.skills.limit.CoreLimitRepository;
import com.pan.dao.repository.skills.limit.LimitItemRepository;
import com.pan.model.entitys.skills.limit.CoreLimit;
import com.pan.model.entitys.skills.limit.LimitInfo;
import com.pan.model.entitys.skills.limit.LimitItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限制事物层
 * @author pan
 * @date 2019/7/3 18:21
 */
@Service
public class LimitService {

    @Autowired
    private CoreLimitRepository coreLimitRepository;

    @Autowired
    private LimitItemRepository limitItemRepository;

    @Autowired
    private TCoreLimitMapper tCoreLimitMapper;

    /**
     * 查询有效限制信息
     * @param coreLimit
     * @return
     */
    public List<CoreLimit> getLimitList(CoreLimit coreLimit) {
        return tCoreLimitMapper.getLimitList(coreLimit);
    }

    /**
     * 保存限制信息
     * @param coreLimit 限制信息
     */
    public CoreLimit save(CoreLimit coreLimit) {
        return coreLimitRepository.save(coreLimit);
    }

    /**
     * 保存限制项
     * @param limitItemList 限制项信息列表
     * @param id 限制id
     */
    public void saveLimitItem(List<LimitItem> limitItemList, Long id) {
        try {
            limitItemRepository.saveAll(limitItemList);
        } catch (Exception e) {
            e.printStackTrace();
            //若是保存限制项出错,则删除限制信息
            coreLimitRepository.deleteById(id);
        }
    }

    /**
     * 根据id查询限制项
     * @param id 限制id
     * @return
     */
    public List<LimitItem> getLimitItemList(Long id) {
        List<LimitItem> all = limitItemRepository.LimitIdEquals(id);
        return all;
    }

    public LimitInfo editLimitCount(LimitInfo limitInfo, CoreLimit coreLimit) {
        //判断限制信息是否还能添加
        return null;
    }


    public LimitInfo getLimitCountInfo(LimitInfo limitInfo, CoreLimit coreLimit) {
        //查询限制信息
        return null;
    }
}
