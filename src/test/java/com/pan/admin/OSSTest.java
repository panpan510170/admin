package com.pan.admin;

import com.pan.config.Propertyconfig.OSSConfig;
import com.pan.base.util.OSSFileUpLoadUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * Created by Pangaofeng on 2018/10/9
 */
public class OSSTest {
    @Autowired
    private OSSConfig ossConfig;

    @Test
    public void contextLoads() {
        try {
            File file = new File("C:\\jobSoft\\test\\123.jpg");
            String buckName = "midautumn1";
            byte[] buffer = null;
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(fis.available());
            byte[] bytes = new byte[fis.available()];
            int temp;
            while ((temp = fis.read(bytes)) != -1) {
                baos.write(bytes, 0, temp);
            }
            fis.close();
            baos.close();
            buffer = baos.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            String test1 = OSSFileUpLoadUtils.ossFileUpload(ossConfig,buckName, "test3",byteArrayInputStream);
            System.out.println(test1);
            String url = OSSFileUpLoadUtils.getUrl(ossConfig,buckName,"test3");
            System.out.println("+++++++++++++++444=========="+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
