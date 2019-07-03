package com.pan.skills.rank;

import com.pan.entitys.rank.CoreRank;
import com.pan.mapper.TCoreRankMapper;
import com.pan.repository.CoreRankRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 排行管理器
 * @author pan
 * @date 2019/6/20 11:00
 */
@Component
@Order(value = 2)
public class RankManager implements CommandLineRunner {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private TCoreRankMapper tCoreRankMapper;

    @Autowired
    private CoreRankRepository coreRankRepository;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            logger.info("加载榜单信息");
            try {
                Thread.sleep(60000);
                load();
            } catch (Exception e) {
                logger.error("加载排行信息异常","", e);
            }
        }
    }

    /**
     * 同步DB榜单配置到内存
     */
    private void load() {
        CoreRank coreRank = new CoreRank();
        long time = System.currentTimeMillis();
        coreRank.setStartTime(time);
        coreRank.setEndTime(time);
        List<CoreRank> list = tCoreRankMapper.getRankList(coreRank);
    }
}
