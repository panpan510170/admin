package com.mshd.util;

/**
 * 管理redisKey
 */
public  class RedisKey {
    /**
     * 团购活动
     */
    public static final String ACTIVITY="activity_";
    public static final String ACTIVITY_PEOPLE_NUM="activityPeopleNum_";
    public static final String ACTIVITY_GOODS_NUMS = "activityGoodsNum_";//活动商品库存key
    public static final String ACTIVITY_REGIMENT_NUM = "regiment_";//成团数
    public static final String ACTIVITY_BUY = "restActivityGoodBuy_";
    public static final String OPENREGIMENT= "openRegiment_"; // 开团

    public static final String UNFULL ="unFull_";//未成团记录

    /*
    * 预购
     */
    public static final String PurchaseRestriction="PurchaseRestriction_";
    /**
     * 分享消消乐
     */
    public static String shareUserBag = "shareUserBag";

    /**
     * 礼包
     */
    public static String giftBag1 = "giftBag1_";
    public static String giftBag2 = "giftBag2_";

    /**
     * 商品
     */
    public static String goods = "goods_";

    /*
    * lock 锁
     */
    public static final String LOCK_ACTIVITYRECORD_KEY = "_activityRecord_";
    public static final String LOCK_ACTIVITYRECORDORDER_KEY = "_recordOrder_";

    /*
    * 表名
     */
    public static final String ACTIVITY_RECORD ="t_activity_record"; //参团活动表
    public static final String PURCHASE_RESTRICT_RECORD="t_purchase_restrict_record";//预购表
    /**
     * 奖池
     */
    public static String luckPool = "LP";
    public static String luckPoolGoods = "LPG";
    public static String drawPeople = "DP";
    public static String winDrawPeople = "WDP";

    /**
     * test过期key定义
     */
    public static final String TEST ="test";

    /**
    *过期key定义
     */
    public static final String RECORD ="activityRecord"; //参团记录key
    public static final String ORDER ="order"; //参团记录key

    /*
    订单详情 切割符号
     */
    public static final String symbol="@";

    /*
    消款业务
     */
    public static final String removeArrears="removeArrears";

    /*
    订单过期时间
     */
    public static final long EXPIRE_TIME=15*60;
}
