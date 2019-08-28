package com.pan.manager.skills.rank;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.base.handler.DataHandler;
import com.pan.model.entitys.skills.rank.CoreRank;
import com.pan.model.vo.rank.RankAddResultVO;
import com.pan.model.vo.rank.RankInfoVO;
import com.pan.model.vo.rank.RankVO;
import com.pan.serivce.skills.rank.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 排行管理器
 * TODO 待优化，若是新增排名需要重启程序
 * @author pan
 * @date 2019/6/20 11:00
 */
@Slf4j
@Component
@Order(value = 2)
public class RankManager implements CommandLineRunner {
    @Autowired
    private RankService rankService;

    /** 所有排行模型 */
    private static Map<String, CoreRank> rankModelMap = new HashMap<>();

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run(String... args){
        try {
            load();
        } catch (Exception e) {
            log.error("初始化加载排行信息异常",null, e);
        }
    }

    /**
     * 同步DB榜单配置到内存
     */
    private void load() {
        log.info("加载榜单信息");
        CoreRank coreRank = new CoreRank();
        long time = System.currentTimeMillis();
        log.info("榜单信息加载--当前时间"+time);
        coreRank.setStartTime(time);
        coreRank.setEndTime(time);
        List<CoreRank> list = rankService.getRankList(coreRank);
        // 整理排行配置
        if (DataHandler.isEmpty(list)) {
            return;
        }
        Map<String, CoreRank> map = new HashMap<>(list.size());
        for (CoreRank rank : list) {
            map.put(rank.getName(), rank);
        }
        // 将配置同步到内存
        rankModelMap = map;
    }

    private CoreRank getCoreRank(RankVO rankVO) {
        //获取排行模型
        CoreRank coreRank = rank(rankVO.getName());
        //排行模型不存在
        if (DataHandler.isEmpty(coreRank)){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"排行模型不存在无法累计" + rankVO.getName());
        }
        return coreRank;
    }

    /**
     * 获取排行模型
     *
     * @param name 排行名称
     * @return 返回排行信息
     */
    private CoreRank rank(String name) {
        if(DataHandler.isEmpty(rankModelMap)){
            load();
        }
        return rankModelMap.get(name);
    }

    /**
     * 累计权重值
     * @param rankVO  排名信息
     * @return 返回累计之前的排名信息和累计之后的排名信息
     */
    public RankAddResultVO add(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.add(rankVO,coreRank);
    }

    /**
     * 累计权重值
     * @param rankVO  排名信息
     * @return 返回上一名和自己和下一名的排名信息
     */
    public RankInfoVO addRank(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.addRank(rankVO,coreRank);
    }

    public RankInfoVO delScore(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.delScore(rankVO,coreRank);
    }

    public List<RankVO> list(RankVO rankVO, long size) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.list(rankVO,coreRank,size);
    }

    public boolean del(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.del(rankVO,coreRank);
    }

    public RankVO get(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.get(rankVO,coreRank);
    }

    public RankVO first(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.first(rankVO,coreRank);
    }

    public int rem(RankVO rankVO) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.rem(rankVO,coreRank);
    }

    public int remList(RankVO rankVO, List<String> list) {
        CoreRank coreRank = getCoreRank(rankVO);
        return rankService.remList(rankVO,coreRank,list);
    }
}
