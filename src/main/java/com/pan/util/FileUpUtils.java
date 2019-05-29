package com.pan.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by pan on 2016/12/21.
 */
public class FileUpUtils {

    public static String fileUpUtils(MultipartFile file){
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();

            //.创建新的文件名称
            String newFileName = null;
            if(file!=null && fileName!=null && fileName.length()>0){
                UUID uuid = UUID.randomUUID();
                String w = uuid.toString().replaceAll("-", "");
                newFileName =  w + fileName.substring(fileName.lastIndexOf("."));
            }

            //创建文件夹路径
            String storageDirectory = "E:\\JAVA\\site\\";

            //.判断文件夹是否存在，若不存在，创建文件夹
            File dirFile = new File(storageDirectory);
            if(!dirFile.exists()){
                dirFile.mkdir();
            }

            //在服务器中，为将要上传的文件开辟空间
            File newFile = new File(storageDirectory+"\\"+newFileName);

            //将用户上传的文件，复制到服务器中开辟好的空间内
            FileCopyUtils.copy(file.getBytes(), newFile);

            //上传文件的绝对路径
            String path = storageDirectory+newFileName;

            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加上传S3方法
     * @param containerReference
     * @param source
     * @param fileName
     * @throws Exception
     */
    /*public static void uploadFile(String containerReference, File source, String fileName) throws Exception {
        String bucketName = ConfigCenterFactory.getConfigCenter().getConfigConfigMap().get("bucketName");
        AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
        Region usWest2 = Region.getRegion(Regions.CN_NORTH_1);
        s3Client.setRegion(usWest2);
        s3Client.putObject(new PutObjectRequest(bucketName, containerReference+fileName, source));
        // containerReference+"/"+fileName;
    }

    public static String uploadFile(String containerReference, String desFilePath,String fileName) throws Exception {
        File source = new File(desFilePath);
        String bucketName = ConfigCenterFactory.getConfigCenter().getConfigConfigMap().get("bucketName");
        AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
        Region usWest2 = Region.getRegion(Regions.CN_NORTH_1);
        s3Client.setRegion(usWest2);
        s3Client.putObject(new PutObjectRequest(bucketName, containerReference+fileName, source));
        return containerReference+fileName;
    }*/

}
