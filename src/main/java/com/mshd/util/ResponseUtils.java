package com.mshd.util;

import com.alibaba.fastjson.JSON;
import com.mshd.controller.BaseController;
import com.mshd.enums.ResultCodeEnum;
import com.mshd.vo.JsonResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 处理response
 *
 * Created by Pangaofeng on 2018/9/11
 */
public class ResponseUtils extends BaseController {

    public static void doReturn(ResultCodeEnum resultCodeEnum, HttpServletResponse response) throws IOException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(resultCodeEnum.getId());
        jsonResult.setMessage(resultCodeEnum.getName());
        String json = JSON.toJSONString(jsonResult);
        ServletOutputStream os = response.getOutputStream(); //获取输出流
        os.write((json.getBytes(Charset.forName("utf-8")))); //将json数据写入流中
        os.flush();
        os.close();
    }
}
