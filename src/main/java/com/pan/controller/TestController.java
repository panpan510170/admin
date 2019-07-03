package com.pan.controller;

import com.alibaba.fastjson.JSONObject;
import com.pan.enums.ResultCodeEnum;
import com.pan.ex.BOException;
import com.pan.entitys.Test;
import com.pan.repository.TestRepository;
import com.pan.serivce.TestService;
import com.pan.util.RedisKey;
import com.pan.vo.JsonResult;

import com.pan.vo.testVO.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * Created by Pangaofeng on 2018/8/23
 */
@RestController
@Api(tags = {"测试"})
@RequestMapping("/test")
public class TestController extends BaseController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;



    @ApiOperation(value = "测试程序错误")
    @PostMapping("/testLoginErrorException")
    public JsonResult testLoginParamError(@ApiParam(value = "a", required = true)
                                              @RequestParam(name = "a") String a){

        Integer i = testService.testThrow(Integer.parseInt(a));
        if(i != 0){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"商品数量不足");
        }

        return this.buildErrorResult(ResultCodeEnum.performError.getId(), ResultCodeEnum.performError.getName());
    }

    @ApiOperation(value = "测试")
    @PostMapping("/testLogin")
    public JsonResult<JSONObject> testLogin(@ApiParam(value = "用户名", required = true)
                                @RequestParam(name = "userName") String userName,
                                @ApiParam(value = "密码", required = true)
                                @RequestParam(name = "password") String password, HttpServletRequest request) {
        logger.info("TestController...testloggerin...入参：用户名：[" + userName + "]，密码：[" + password + "]");

        JSONObject resMap = null;
        try {
            resMap = new JSONObject();
            resMap.put("userName", userName);
            resMap.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("TestController...testLogin...出参：" + resMap);

        return this.buildSuccessResult(resMap);
    }

    @ApiOperation(value = "测试参数错误")
    @PostMapping("/testLoginParamError")
    public JsonResult<JSONObject> testLoginError(@ApiParam(value = "用户名")
                                     @RequestParam(name = "userName") String userName,
                                     @ApiParam(value = "密码", required = true)
                                     @RequestParam(name = "password") String password, HttpServletRequest request) {
        logger.info("TestController...testLogin...入参：用户名：[" + userName + "]，密码：[" + password + "]");

        if ("".equals(userName.trim()) || userName == null ||
                "".equals(password) || password == null) {
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }
        JSONObject resMap = null;
        try {
            resMap = new JSONObject();
            resMap.put("userName", userName);
            resMap.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("TestController...testLogin...出参：" + resMap);

        return this.buildSuccessResult(resMap);
    }

    @ApiOperation(value = "测试程序错误")
    @PostMapping("/testLoginError")
    public JsonResult testLoginParamError(@ApiParam(value = "用户名", required = true)
                                          @RequestParam(name = "userName") String userName,
                                          @ApiParam(value = "密码", required = true)
                                          @RequestParam(name = "password") String password, HttpServletRequest request) {
        logger.info("TestController...testLogin...入参：用户名：[" + userName + "]，密码：[" + password + "]");


        try {
            int a = 7 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }

        logger.info("TestController...testLogin...出参：");

        return this.buildErrorResult(ResultCodeEnum.paramError);
    }

    @ApiOperation(value = "测试BaseVO")
    @PostMapping("/testBaseVO")
    public UserVO testBaseVO(@ApiParam(value = "用户名", required = true)
                                          @RequestParam(name = "userName") String userName,
                                          @ApiParam(value = "密码", required = true)
                                          @RequestParam(name = "password") String password, HttpServletRequest request) {
        logger.info("TestController...testBaseVO...入参：用户名：[" + userName + "]，密码：[" + password + "]");

        UserVO userVO = new UserVO();
        userVO.setUserName(userName);
        userVO.setUserNo("213");
        userVO.success();
        return userVO;
    }

    @ApiOperation(value = "测试redisTime")
    @PostMapping("/redisTime")
    public void redisTime(HttpServletRequest request) {
        System.out.println("123");

        redisTemplate.opsForValue().set(RedisKey.test+"@"+"1234","1",1*60, TimeUnit.SECONDS);

        System.out.println("456");
    }

    @ApiOperation(value = "测试JPA---查询全部数据")
    @PostMapping("/testJPAFindAll")
    public JsonResult testJPAFindAll() {
        List<Test> list = testRepository.findAll();
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "测试JPA---根据id查询数据")
    @PostMapping("/testJPAFindById")
    public JsonResult testJPAFindById() {
        Optional<Test> test = testRepository.findById(1L);
        return this.buildSuccessResult(test);
    }

    @ApiOperation(value = "测试JPA---保存")
    @PostMapping("/testJPASave")
    public JsonResult testJPASave() {
        Test test = new Test();
        test.setAge(20);
        test.setName("孙婧");
        Test save = testRepository.save(test);
        return this.buildSuccessResult(test);
    }


    @ApiOperation(value = "测试JPA---批量保存")
    @PostMapping("/testJPASaveAll")
    public JsonResult testJPASaveAll() {
        Test test = new Test();
        test.setAge(20);
        test.setName("孙婧");

        Test test1 = new Test();
        test1.setAge(21);
        test1.setName("孙婧婧");

        List<Test> tests = new ArrayList<>();
        tests.add(test);
        tests.add(test1);
        List<Test> tests1 = testRepository.saveAll(tests);
        return this.buildSuccessResult(tests1);
    }

    @ApiOperation(value = "测试JPA---修改1(使用保存方法更新)")
    @PostMapping("/testJPAUpdate")
    public JsonResult testJPAUpdate() {
        Test test = new Test();
        test.setAge(28);
        test.setId(1L);
        Test save = testRepository.save(test);
        return this.buildSuccessResult(save);
    }

    @ApiOperation(value = "测试JPA---修改2（使用saveAndFlush发放更新）")
    @PostMapping("/testJPAUpdateSaveAndFlush")
    public JsonResult testJPAUpdateSaveAndFlush() {
        Test test1 = new Test();
        Optional<Test> test = testRepository.findById(1L);
        Test test2 = test.get();
        test2.setAge(80);
        testRepository.saveAndFlush(test2);
        return this.buildSuccessResult(test2);
    }

    @ApiOperation(value = "测试JPA---条件查询")
    @PostMapping("/testJPAFindByExample")
    public JsonResult testJPAFindByExample() {

       /* User user = new User();
        user.setUsername("y");
        user.setAddress("sh");
        user.setPassword("admin");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                .withIgnorePaths("password")//忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("id");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        Example<User> example = Example.of(user ,matcher);*/
        Test test = new Test();
        ExampleMatcher matching = ExampleMatcher.matching();
        matching.withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Test> example = Example.of(test ,matching);
        List<Test> list = testRepository.findAll(example);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "测试JPA---分页")
    @PostMapping("/testJPAFindByPage")
    public JsonResult testJPAFindByPage() {
        //JPA---分页是从0开始的
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0,2,sort);
        Page<Test> all = testRepository.findAll(pageable);
        return this.buildSuccessResult(all);
    }

}
