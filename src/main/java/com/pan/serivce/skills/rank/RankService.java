package com.pan.serivce.skills.rank;

import com.pan.model.entitys.skills.rank.CoreRank;
import com.pan.model.entitys.skills.rank.RankInfo;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.base.handler.DataHandler;
import com.pan.dao.mapper.skills.rank.TCoreRankMapper;
import com.pan.dao.repository.skills.rank.CoreRankRepository;
import com.pan.dao.repository.skills.rank.RankInfoRepository;
import com.pan.model.vo.rank.RankAddResultVO;
import com.pan.model.vo.rank.RankInfoVO;
import com.pan.model.vo.rank.RankVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    private RankInfoRepository rankInfoRepository;

    @Autowired
    private TCoreRankMapper tCoreRankMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 保存排行模型
     * @param coreRank
     * @return
     */
    public void save(CoreRank coreRank) {
        if(null == coreRank.getBasics() || 0 == coreRank.getBasics()){
            coreRank.setBasics(1L);
        }
        coreRankRepository.save(coreRank);
    }

    /**
     * 获取排行模型
     * @param coreRank
     * @return
     */
    public List<CoreRank> getRankList(CoreRank coreRank) {
        return tCoreRankMapper.getRankList(coreRank);
    }

    public RankAddResultVO add(RankVO rankVO, CoreRank coreRank) {
        RankAddResultVO rankAddResultVO = new RankAddResultVO();
        //获取排名累计的RedisKey
        String key = rankKey(rankVO,coreRank);
        //获取累计前的排名和权重
        Long beforeRank = redisTemplate.opsForZSet().reverseRank(key, rankVO.getUserId());
        //判断榜单时间是否过期
        if(rankVO.getTime() >= coreRank.getEndTime()){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"排行模型已经结束" + rankVO.getName());
        }
        //累计权重
        long afterScore = redisTemplate.opsForZSet().incrementScore(key, rankVO.getUserId(),rankVO.getWeight()*coreRank.getBasics()).longValue();
        //处理时间排序
        if(coreRank.getBasics() > 1){
            //处理时间戳  若时间戳==0
            //证明是第一次便直接加上(时间最大值-now)即可
            //若不是 则 time + ((时间最大值-now)-time)
            long time = afterScore%coreRank.getBasics();
            long now = coreRank.getEndTime() - rankVO.getTime();
            //累计时间权重
            afterScore = redisTemplate.opsForZSet().incrementScore(key, rankVO.getUserId(),time == 0 ? now : now - time).longValue();
        }
        //设置榜单的过期时间,以榜单的结束时间为准
        redisTemplate.expire(key,coreRank.getEndTime(), TimeUnit.MILLISECONDS);
        //获取累计后的排名和权重
        long afterRank = redisTemplate.opsForZSet().reverseRank(key, rankVO.getUserId()).longValue();
        //封装返回
        if(null != beforeRank){
            rankAddResultVO.setBefore(new RankVO(rankVO.getName(),rankVO.getDimValue(),rankVO.getUserId(),afterScore/coreRank.getBasics()-rankVO.getWeight(),beforeRank+1,rankVO.getTime()));
        }
        rankAddResultVO.setAfter(new RankVO(rankVO.getName(),rankVO.getDimValue(),rankVO.getUserId(),afterScore/coreRank.getBasics(),afterRank+1,rankVO.getTime()));
        return rankAddResultVO;
    }

    /**
     * 获取排名累计的redisKey
     * @param rankVO
     * @return
     */
    private String rankKey(RankVO rankVO, CoreRank coreRank) {
        String key = "Rank_" + rankVO.getName();
        return 1 == coreRank.getDimensionType() ? key +"_"+ rankVO.getDimValue() : key;
    }

    public RankInfoVO addRank(RankVO rankVO, CoreRank coreRank) {
        //获取排名累计的RedisKey
        String key = rankKey(rankVO,coreRank);
        //判断榜单时间是否过期
        if(rankVO.getTime() >= coreRank.getEndTime()){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"排行模型已经结束" + rankVO.getName());
        }
        //累计权重
        long afterScore = redisTemplate.opsForZSet().incrementScore(key, rankVO.getUserId(),getScore(rankVO.getWeight(),coreRank.getBasics(),true)).longValue();
        //处理时间排序
        if(coreRank.getBasics() > 1){
            //处理时间戳  若时间戳==0
            //证明是第一次便直接加上(时间最大值-now)即可
            //若不是 则 time + ((时间最大值-now)-time)
            long time = afterScore%coreRank.getBasics();
            long now = coreRank.getEndTime() - rankVO.getTime();
            //累计时间权重
            redisTemplate.opsForZSet().incrementScore(key, rankVO.getUserId(),time == 0 ? now : now - time).longValue();
        }
        //设置榜单的过期时间,以榜单的结束时间为准
        redisTemplate.expire(key,coreRank.getEndTime(), TimeUnit.MILLISECONDS);
        //获取累计后的排名和权重
        redisTemplate.opsForZSet().reverseRank(key, rankVO.getUserId()).longValue();
        //封装返回
        RankInfoVO rankInfoVO = getRank(rankVO,coreRank);
        return rankInfoVO;
    }

    /**
     * 获取排名信息
     * @return 返回上一名和自己和下一名的排名信息
     */
    private RankInfoVO getRank(RankVO rankVO, CoreRank coreRank) {
        RankInfoVO rankInfoVO = new RankInfoVO();
        String key = rankKey(rankVO,coreRank);
        //判断榜单是否存在
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        long userId = rankVO.getUserId();
        //查询自己的排名情况
        Long selfRank = redisTemplate.opsForZSet().reverseRank(key, userId);
        if(null == selfRank){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"该用户没有排名" + userId);
        }
        long selfScore = redisTemplate.opsForZSet().score(key, userId).longValue();
        rankInfoVO.setSelf(new RankVO(rankVO.getName(),rankVO.getDimValue(),userId,getScore(selfScore,coreRank.getBasics(),false),selfRank+1,rankVO.getTime()));
        //判断是否是唯一上榜
        int count = redisTemplate.opsForZSet().zCard(key).intValue();
        if(1 >= count){
            return rankInfoVO;
        }
        //获取上一名的信息,如果现在是第一名就不用获取了
        if(0 != selfRank){
            Set<DefaultTypedTuple> formerSet = redisTemplate.opsForZSet().reverseRangeWithScores(key, selfRank - 1, selfRank - 1);
            if(DataHandler.isNotEmpty(formerSet)) {
                for(DefaultTypedTuple t : formerSet) {
                    rankInfoVO.setFormer(new RankVO(rankVO.getName(),rankVO.getDimValue(),DataHandler.getLong(t.getValue()),getScore(DataHandler.getLong(t.getScore()),coreRank.getBasics(),false),selfRank,rankVO.getTime()));
                }
            }
        }
        //获取下一名的信息
        Set<DefaultTypedTuple> latterSet = redisTemplate.opsForZSet().reverseRangeWithScores(key,selfRank+1, selfRank+1);
        if(DataHandler.isNotEmpty(latterSet)) {
            for(DefaultTypedTuple t : latterSet) {
                rankInfoVO.setLatter(new RankVO(rankVO.getName(),rankVO.getDimValue(),DataHandler.getLong(t.getValue()),getScore(DataHandler.getLong(t.getScore()),coreRank.getBasics(),false),selfRank+2,rankVO.getTime()));
            }
        }
        return rankInfoVO;
    }

    /**
     * 计算score
     * @param score   分值
     * @param basics  权重
     * @param flag   true 乘 false 除
     * @return
     */
    private long getScore(long score, long basics,boolean flag) {
        if(flag){
            return score*(basics == 0 ? 1 : basics);
        }else{
            return score/(basics == 0 ? 1 : basics);
        }
    }

    /**
     * 扣除权重累计值
     * @return 返回上一名和自己和下一名的排名信息
     */
    public RankInfoVO delScore(RankVO rankVO, CoreRank coreRank) {
        //获取排名累计的RedisKey
        String key = rankKey(rankVO,coreRank);
        //判断榜单时间是否过期
        if(rankVO.getTime() >= coreRank.getEndTime()){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"排行模型已经结束" + rankVO.getName());
        }
        //先查询分值
        Double weight = redisTemplate.opsForZSet().score(key, rankVO.getUserId());
        if(null == weight){
            return null;
        }
        long score = getScore(weight.longValue(),coreRank.getBasics(),false);
        //累计权重
        redisTemplate.opsForZSet().incrementScore(key, rankVO.getUserId(),getScore(rankVO.getWeight()>=score?score:rankVO.getWeight(),coreRank.getBasics(),true)*-1).longValue();
        //设置榜单的过期时间,以榜单的结束时间为准
        redisTemplate.expire(key,coreRank.getEndTime(), TimeUnit.MILLISECONDS);
        //获取累计后的排名和权重
        redisTemplate.opsForZSet().reverseRank(key, rankVO.getUserId()).longValue();
        //封装返回
        RankInfoVO rankInfoVO = getRank(rankVO,coreRank);
        return rankInfoVO;
    }

    public List<RankVO> list(RankVO rankVO, CoreRank coreRank, long size) {
        String key = rankKey(rankVO,coreRank);
        Set<DefaultTypedTuple> set = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, size-1);
        if(DataHandler.isEmpty(set)){
            return new ArrayList<>(0);
        }
        List<RankVO> rankList = new ArrayList<RankVO>(set.size());
        // 整理排行榜返回数据
        int i = 1;
        long time = System.currentTimeMillis();
        for (DefaultTypedTuple t : set) {
            rankList.add(new RankVO(rankVO.getName(), rankVO.getDimValue(), DataHandler.getLong(t.getValue()),getScore(DataHandler.getLong(t.getScore()),coreRank.getBasics(),false),i,time));
            i++;
        }
        return rankList;
    }

    public boolean del(RankVO rankVO, CoreRank coreRank) {
        String key = rankKey(rankVO,coreRank);
        return redisTemplate.delete(key).booleanValue();
    }

    public RankVO get(RankVO rankVO, CoreRank coreRank) {
        String key = rankKey(rankVO,coreRank);
        //判断榜单是否存在
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        long userId = rankVO.getUserId();
        //查询自己的排名情况
        Double weight = redisTemplate.opsForZSet().score(key, userId);
        if(null == weight){
            return null;
        }
        long score = weight.longValue();
        long rank = redisTemplate.opsForZSet().reverseRank(key, userId).longValue();
        return new RankVO(rankVO.getName(),rankVO.getDimValue(),userId,getScore(score,coreRank.getBasics(),false),rank+1,rankVO.getTime());
    }

    public RankVO first(RankVO rankVO, CoreRank coreRank) {
        String key = rankKey(rankVO,coreRank);
        //判断榜单是否存在
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        Set<DefaultTypedTuple> set = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 0);
        if(DataHandler.isNotEmpty(set)) {
            for(DefaultTypedTuple t : set) {
                return new RankVO(rankVO.getName(), rankVO.getDimValue(), DataHandler.getLong(t.getValue()), getScore(DataHandler.getLong(t.getScore()), coreRank.getBasics(), false), 1, rankVO.getTime());
            }
        }
        return null;
    }

    public int rem(RankVO rankVO, CoreRank coreRank) {
        String key = rankKey(rankVO,coreRank);
        return redisTemplate.opsForZSet().remove(key,rankVO.getUserId()).intValue();
    }

    public int remList(RankVO rankVO, CoreRank coreRank, List<String> list) {
        String key = rankKey(rankVO,coreRank);
        return redisTemplate.opsForZSet().remove(key,list.toArray(new String[list.size()])).intValue();
    }

    public void saveLog(RankInfo rankInfo) {
        rankInfoRepository.save(rankInfo);
    }
}
