package com.pan.manager.skills.limit;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.base.handler.DataHandler;
import com.pan.model.entitys.skills.limit.CoreLimit;
import com.pan.model.entitys.skills.limit.LimitInfo;
import com.pan.model.entitys.skills.limit.LimitItem;
import com.pan.serivce.skills.limit.LimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 限制管理器
 * TODO 待优化，若是新增限制需要重启程序
 * @author pan
 * @date 2019/6/20 11:00
 */
@Slf4j
@Component
@Order(value = 3)
public class LimitManager implements CommandLineRunner {

    @Autowired
    private LimitService limitService;

    /** 所有排行模型 */
    private static Map<Long, CoreLimit> limitModelMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        try {
            load();
        } catch (Exception e) {
            log.error("初始化加载限制信息异常",null, e);
        }
    }

    /**
     * 同步DB榜单配置到内存
     */
    private void load() {
        log.info("加载限制信息");
        CoreLimit coreLimit = new CoreLimit();
        long time = System.currentTimeMillis();
        log.info("限制信息加载--当前时间"+time);
        coreLimit.setEndTime(time);
        List<CoreLimit> list = limitService.getLimitList(coreLimit);
        // 整理排行配置
        if (DataHandler.isEmpty(list)) {
            return;
        }
        Map<Long, CoreLimit> map = new HashMap<>(list.size());
        for (CoreLimit limit : list) {
            List<LimitItem> limitItemList = limitService.getLimitItemList(limit.getId());
            limit.setList(limitItemList);
            map.put(limit.getId(), limit);
        }
        // 将配置同步到内存
        limitModelMap = map;
    }

    /**
     * 从内存中获取限制信息
     * @param id 限制id
     * @return
     */
    private CoreLimit getCoreLimit(Long id) {
        if(DataHandler.isEmpty(limitModelMap)){
            load();
        }
        CoreLimit coreLimit = limitModelMap.get(id);
        if(DataHandler.isEmpty(coreLimit)){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"限制模型不存在无法累计" + id);
        }
        return coreLimit;
    }

    /**
     * 增加限制次数
     * @param limitInfo 限制信息
     */
    public LimitInfo editLimitCount(LimitInfo limitInfo) {
        CoreLimit coreLimit = getCoreLimit(limitInfo.getLimitId());
        return limitService.editLimitCount(limitInfo,coreLimit);
    }

    /**
     * 查询限制信息
     * @param limitInfo 限制信息
     * @return
     */
    public LimitInfo getLimitCountInfo(LimitInfo limitInfo) {
        CoreLimit coreLimit = getCoreLimit(limitInfo.getLimitId());
        return limitService.getLimitCountInfo(limitInfo,coreLimit);
    }
}
