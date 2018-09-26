package com.mshd.admin;

import com.mshd.model.User;
import com.mshd.vo.RedisKeys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
}
