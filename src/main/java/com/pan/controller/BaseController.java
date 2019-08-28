package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.base.util.QueryResult;
import com.pan.base.util.RequestUtils;
import com.pan.model.entitys.system.SUser;
import com.pan.model.vo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * 控制层基类
 *
 * @author Pangaofeng
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class BaseController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /**
     * 获取页面参数
     *
     * @param request
     * @return JsonResult
     */
    public Map<String, Object> getQueryParams(HttpServletRequest request) {
        Map<String, Object> pageMap = RequestUtils.getQueryParams(request);
        return pageMap;
    }

    /**
     * 成功  不返回数据
     *
     * @return JsonResult
     */
    public <T> JsonResult buildSuccessResult() {
        return new JsonResult( ResultCodeEnum.success.getId(), ResultCodeEnum.success.getName());
    }

    /**
     * 失败  不返回结果
     *
     * @return JsonResult
     */
    public <T> JsonResult buildErrorResult() {
        return new JsonResult(ResultCodeEnum.error.getId(), ResultCodeEnum.error.getName());
    }

    /**
     * 成功  返回数据
     *
     * @param obj 结果对象
     * @return JsonResult
     */
    public <T> JsonResult buildSuccessResult(T obj) {
        return new JsonResult(obj, ResultCodeEnum.success.getId(), ResultCodeEnum.success.getName());
    }

    /**
     * 失败  返回数据
     *
     * @param obj 结果对象
     * @return JsonResult
     */
    public <T> JsonResult buildErrorResult(T obj) {
        return new JsonResult(obj, ResultCodeEnum.error.getId(), ResultCodeEnum.error.getName());
    }

    /**
     * 成功结果  返回数据和结果
     *
     * @param obj 结果对象
     * @param resultCodeEnum 业务码
     * @return JsonResult
     */
    public <T> JsonResult buildSuccessResult(T obj, ResultCodeEnum resultCodeEnum) {
        return new JsonResult(obj, resultCodeEnum.getId(), resultCodeEnum.getName());
    }

    /**
     * 失败结果  返回数据和结果
     *
     * @param resultCodeEnum  业务码
     * @return JsonResult
     */
    public <T> JsonResult buildErrorResult(T obj,ResultCodeEnum resultCodeEnum) {
        return new JsonResult(obj,resultCodeEnum.getId(), resultCodeEnum.getName());
    }

    /**
     * 失败  返回数据
     *
     * @param resultCodeEnum  业务码
     * @return JsonResult
     */
    public <T> JsonResult buildErrorResult(ResultCodeEnum resultCodeEnum) {
        return new JsonResult(null, resultCodeEnum.getId(), resultCodeEnum.getName());
    }

    /**
     * 成功  返回数据
     *
     * @param resultCodeEnum 业务码
     * @return JsonResult
     */
    public <T> JsonResult buildSuccessResult(ResultCodeEnum resultCodeEnum) {
        return new JsonResult(null, resultCodeEnum.getId(), resultCodeEnum.getName());
    }


    /**
     * 失败结果  返回异常信息
     *
     * @return JsonResult
     */
    public <T> JsonResult buildErrorResult(Integer code, String msg) {
        return new JsonResult(code, msg);
    }

    /**
     * list 封装结果集
     * @return JsonResult
     */
    public <T> QueryResult buildQueryResult(QueryResult queryResult){
        return new QueryResult(queryResult.getRows(),queryResult.getTotal());
    }


    /**
     * 处理抛出到父类的异常信息
     * @Author pangaofeng
     * @Date 2018/8/30 11:26
     */
    @ExceptionHandler
    @ResponseBody
    public JsonResult exp(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if(e instanceof BOException){
            BOException ex = (BOException)e;
            Integer code = ex.get_code();
            String message = ex.getMessage();
            return this.buildErrorResult(code, message);
        }else if(e instanceof IOException){
            IOException ex = (IOException)e;
            return this.buildErrorResult(ResultCodeEnum.IOError);
        }else{
            e.printStackTrace();
            return this.buildErrorResult(ResultCodeEnum.performError);
        }
    }

    /**
     * 处理权限
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public SUser getCurrentUser() {
        return (SUser) getSubject().getPrincipal();
    }

    public Session getSession() {
        return getSubject().getSession();
    }

    public Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    public void login(AuthenticationToken token) {
        getSubject().login(token);
    }
}
