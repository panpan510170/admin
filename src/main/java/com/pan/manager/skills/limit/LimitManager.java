package com.pan.manager.skills.limit;

import com.pan.model.entitys.skills.limit.CoreLimit;
import com.pan.base.handler.DataHandler;
import com.pan.serivce.skills.limit.LimitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 限制管理器
 * @author pan
 * @date 2019/6/20 11:00
 */
@Component
@Order(value = 3)
public class LimitManager implements CommandLineRunner {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private LimitService limitService;

    /** 所有排行模型 */
    private static Map<String, CoreLimit> limitModelMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            try {
                load();
                logger.info("加载限制信息");
                Thread.sleep(1000*60*60);
            } catch (Exception e) {
                logger.error("加载限制信息异常",null, e);
            }
        }
    }

    /**
     * 同步DB榜单配置到内存
     */
    private void load() {
        CoreLimit coreLimit = new CoreLimit();
        long time = System.currentTimeMillis();
        logger.info("限制信息加载--当前时间"+time);
        coreLimit.setEndTime(time);
        List<CoreLimit> list = limitService.getLimitList(coreLimit);
        // 整理排行配置
        if (DataHandler.isEmpty(list)) {
            return;
        }
        Map<String, CoreLimit> map = new HashMap<>(list.size());
        for (CoreLimit limit : list) {
            map.put(limit.getName(), limit);
        }
        // 将配置同步到内存
        limitModelMap = map;
    }
}
