package com.mshd.admin;

import com.mshd.model.User;
import com.mshd.vo.RedisKeys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 缓存测试类
 *
 * Created by Pangaofeng on 2018/9/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试set
     * */
    @Test
    public void testSet() {
        String str = "test";
        Long add = redisTemplate.opsForSet().add(str, "test");
        System.out.println(add);

        Set members = redisTemplate.opsForSet().members(str);
        System.out.println(members);
    }

    /**
     * 测试zset
     * */
    @Test
    public void testZSet() {
        String strZSet = "testZSet";
        /*redisTemplate.opsForZSet().add(strZSet, "test-2", 1.1d);
        redisTemplate.opsForZSet().add(strZSet, "test-7", 1.11d);
        redisTemplate.opsForZSet().add(strZSet, "test-3", 2d);
        redisTemplate.opsForZSet().add(strZSet, "test-4", 2.2d);
        redisTemplate.opsForZSet().add(strZSet, "test-5", 3d);*/
        Boolean add = redisTemplate.opsForZSet().add(strZSet, "test-6", 3.8d);
        System.out.println(add);

       /* ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));*/

        //range  只是值  没有标记
        System.out.println(redisTemplate.opsForZSet().range(strZSet, 0, -1));
        //rangeWithScores  数量
        Set<DefaultTypedTuple> set = redisTemplate.opsForZSet().rangeWithScores(strZSet, 0L, 4L);
        for (DefaultTypedTuple str : set) {
            System.out.println("set==="+str.getScore()+"==="+str.getValue());
        }
    }

    @Test
    public void selectRedisUser() {
        //List<User> list = (List<User>)redisTemplate.opsForList().range(RedisKeys.userTable, 0, -1);
        //System.out.println(list);
        System.out.println(redisTemplate.opsForList().size(RedisKeys.userTable));
        User admin111111 = (User)redisTemplate.opsForValue().get("admin1111111");
        System.out.println(admin111111);
    }
    @Test
    public void testRedis() {
        /*UserParamVO userParamVO = new UserParamVO();

        userParamVO.setId(1);
        userParamVO.setName("张三");

        List<UserParamVO> list = new ArrayList<UserParamVO>();
        list.add(userParamVO);

        redisTemplate.opsForList().leftPushAll("key12345",list);
        //BuyVo sss=(BuyVo) redisTemplate.opsForList().rightPop("key12345");
        //System.out.println(sss);
        System.out.println(redisTemplate.opsForList().size("key12345"));
        List<UserParamVO> list3 = (List<UserParamVO>)redisTemplate.opsForList().range("key12345", 0, 8);

        redisTemplate.opsForList().range("key12345", 0, 8);

        System.out.println(list3);*/
        Long key12345999 = redisTemplate.opsForList().size("key12345999");
        System.out.println(key12345999);
    }

    @Test
    public void setIfAbsent() {
        stringRedisTemplate.opsForValue().set("123","1233");

        String s = stringRedisTemplate.opsForValue().get("123");

        System.out.println("o===="+s);

    }

    @Test
    public void testjishuqi() {
        //stringRedisTemplate.opsForValue().set("123","789");
        //stringRedisTemplate.delete("123");
        Long increment = stringRedisTemplate.opsForValue().increment("123456", 1);

        String s = stringRedisTemplate.opsForValue().get("123456");

        //System.out.println("increment==="+increment);
        System.out.println("s==="+s);

    }
}
