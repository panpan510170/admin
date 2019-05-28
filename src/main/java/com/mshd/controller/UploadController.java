package com.mshd.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.mshd.enums.ResultCodeEnum;
import com.mshd.serivce.UserService;
import com.mshd.util.OSSFileUpLoadUtils;
import com.mshd.vo.JsonResult;
import com.mshd.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by Pangaofeng on 2018/9/6
 */
@RestController
@Api(tags = {"上传"})
@RequestMapping("/upload")
public class UploadController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "上传图片")
    @PostMapping("/uploadImg")
    public JsonResult<String> uploadImg(@RequestParam(value="myFileName") MultipartFile mf,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{

        logger.info("UploadController...uploadImg...入参:"+mf);

        String realPath = request.getSession().getServletContext().getRealPath("upload");

        //获取源文件
        String fileName = mf.getOriginalFilename();
        String[] names=fileName.split("\\.");//
        String tempNum=(int)(Math.random()*100000)+"";
        String uploadFileName=tempNum +System.currentTimeMillis()+"."+names[names.length-1];
        File targetFile = new File(realPath,uploadFileName);//目标文件

        String accessKeyId = "LTAI84wPnctxL3kw";
        String accessKeySecret= "lQY1z0ppuajWe6J23mLtbAisvjWZCz";
        String endpoint= "oss-cn-beijing.aliyuncs.com";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        //上传阿里云
        String buckName = "convenience";
        ossClient.putObject(buckName, fileName, mf.getInputStream());
        String url = "https://"+buckName+"."+endpoint+"/"+fileName;
        System.out.println(fileName+"的文件路径："+url);

        return this.buildSuccessResult("https://"+buckName+"."+endpoint+"/"+fileName);
    }

    @ApiOperation(value = "上传文本")
    @PostMapping("/uploadText")
    public JsonResult<String> uploadImg(@RequestParam(value="texts") String texts) throws Exception{

        System.out.println("first:"+texts.trim());

        return this.buildSuccessResult();
    }
}