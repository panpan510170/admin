package com.mshd.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;


/**
 * @author Pangaofeng
 * @version crateTime：2013-10-30 下午5:41:30
 * Class Explain:JedisUtil
 */
public class JedisUtil {

    /**缓存生存时间 */
    private final int expire = 60000;

    //Redis服务器IP
    private String address;

    private int port;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private int maxActive;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private int maxIdle;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private int maxWait;

    private int timeout;

    private String auth;

    private boolean TEST_ON_BORROW = true;

    private boolean TEST_ON_RETURN = true;

    //----set/get start----


    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isTEST_ON_BORROW() {
        return TEST_ON_BORROW;
    }

    public void setTEST_ON_BORROW(boolean TEST_ON_BORROW) {
        this.TEST_ON_BORROW = TEST_ON_BORROW;
    }

    public boolean isTEST_ON_RETURN() {
        return TEST_ON_RETURN;
    }

    public void setTEST_ON_RETURN(boolean TEST_ON_RETURN) {
        this.TEST_ON_RETURN = TEST_ON_RETURN;
    }

    //----set/get end------
    private static JedisPool jedisPool = null;

    private JedisUtil() {

    }

    public void init(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(TEST_ON_BORROW);
            config.setTestOnReturn(TEST_ON_RETURN);
            if (auth != null && !auth.equals(""))
                jedisPool = new JedisPool(config, address, port, timeout, auth);
            else
                jedisPool = new JedisPool(config, address, port, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //暂不需要
    private static JedisPool getPool() {
        return jedisPool;
    }

    /**
     * 从jedis连接池中获取获取jedis对象
     * @return
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }


    private static final JedisUtil jedisUtil = new JedisUtil();


    /**
     * 获取JedisUtil实例
     * @return
     */
    public static JedisUtil getInstance() {
        return jedisUtil;
    }

    /**
     * 回收jedis
     * @param jedis
     */
    public  void returnJedis(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }


    /**
     * 设置过期时间
     * @author ruan 2013-4-11
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        returnJedis(jedis);
    }

    /**
     * 设置默认过期时间
     * @author ruan 2013-4-11
     * @param key
     */
    public void expire(String key) {
        expire(key, expire);
    }


    /**
     * 方法正常结束时调用，清空缓存值
     * @param key
     */
    public void flushKey(String key){
        del(key);
    }


    public  void set(String key, String value){
        if(isBlank(key)) return;
        Jedis jedis = getJedis();
        jedis.set(key, value);
        returnJedis(jedis);
    }



    public  void set(String key, int value){
        if(isBlank(key)) return;
        set(key, String.valueOf(value));
    }

    public  void set(String key, long value){
        if(isBlank(key)) return;
        set(key, String.valueOf(value));
    }

    public  void set(String key, float value){
        if(isBlank(key)) return;
        set(key, String.valueOf(value));
    }

    public  void set(String key, double value){
        if(isBlank(key)) return;
        set(key, String.valueOf(value));
    }

    public Float getFloat(String key){
        if(isBlank(key)) return null;
        return Float.valueOf(getStr(key));
    }

    public Double getDouble(String key){
        if(isBlank(key)) return null;
        return Double.valueOf(getStr(key));
    }

    public Long getLong(String key){
        if(isBlank(key)) return null;
        return Long.valueOf(getStr(key));
    }

    public Integer getInt(String key){
        if(isBlank(key)) return null;
        return Integer.valueOf(getStr(key));
    }

    public String getStr(String key){
        if(isBlank(key)) return null;
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        returnJedis(jedis);
        return value;
    }

    public Set<String> keys(String key){
        if(isBlank(key)) return null;
        Jedis jedis = getJedis();
        Set<String> keys = jedis.keys(key);
        return keys;
    }

    public void del(String key){
        if(isBlank(key)) return;
        Jedis jedis = getJedis();
        jedis.del(key);
        returnJedis(jedis);
    }

    /**
     * 取出并删除
     * @param key
     */
    public String getRpop(String key){
        if(isBlank(key)) return null;
        Jedis jedis = getJedis();
        String value = jedis.rpop(key);
        returnJedis(jedis);
        return value;
    }

    /**
     * 判断key是否存在
     * @param key
     */
    public Boolean existsKey(String key){
        if(isBlank(key)) return null;
        Jedis jedis = getJedis();
        returnJedis(jedis);
        return jedis.exists(key);
    }

    public void shutDown(){
        getJedis().shutdown();
    }

    public  boolean isBlank(String str){
        return str==null||"".equals(str.trim());
    }

}
