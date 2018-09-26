package com.mshd.util;

import com.mshd.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 解压
 * Created by snow on 2016/10/31.
 */
public class DecoUtil {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 解压缩
     * @param sZipPathFile 要解压的文件
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<File> unzip(File sZipPathFile,String temp) {
        System.out.println("解压"+sZipPathFile.getAbsoluteFile());
        ArrayList<File> allFileName = new ArrayList<File>();
        try {
            // 先指定压缩档的位置和档名，建立FileInputStream对象
            FileInputStream fins = new FileInputStream(sZipPathFile);


            // 将fins传入ZipInputStream中
            ZipInputStream zins = new ZipInputStream(fins);
            ZipEntry ze = null;
            byte[] ch = new byte[1024];
            while ((ze = zins.getNextEntry()) != null) {
                File zfile = new File(temp + ze.getName());
                File fpath = new File(zfile.getParentFile().getPath());
                if (ze.isDirectory()) {
                    if (!zfile.exists()){
                        zfile.mkdirs();
                    }
                    zins.closeEntry();
                } else {
                    if (!fpath.exists())
                        fpath.mkdirs();
                    FileOutputStream fouts = new FileOutputStream(zfile);
                    int i;
                    while ((i = zins.read(ch)) != -1)
                        fouts.write(ch, 0, i);
                    zins.closeEntry();
                    fouts.close();
                }
                //假如list中
                allFileName.add(zfile);
                zins.closeEntry();
            }
            fins.close();
            zins.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("unzip 解压出错:" , e);
        }
        return allFileName;
    }


    /**
     * 解压缩
     * @param sZipPathFile 要解压的文件
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<String> unzip(File sZipPathFile) {
        System.out.println(sZipPathFile.getAbsoluteFile());
        ArrayList<String> allFileName = new ArrayList<String>();
        try {
            // 先指定压缩档的位置和档名，建立FileInputStream对象
            FileInputStream fins = new FileInputStream(sZipPathFile);
            // 将fins传入ZipInputStream中
            ZipInputStream zins = new ZipInputStream(fins);
            ZipEntry ze = null;
            while ((ze = zins.getNextEntry()) != null) {
                allFileName.add(ze.getName());
                zins.closeEntry();
            }
            fins.close();
            zins.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("unzip 解压出错" ,e);
        }
        return allFileName;
    }


    public static void deleteTempFile(String tempPath){

        File file =new File(tempPath);
        deleteAll(file);

    }
    public static void deleteAll(File file){

        if(file.isFile() || file.list().length ==0) {
            file.delete();
        }else{
            File[] files = file.listFiles();
            for (File f : files) {
                deleteAll(f);
                f.delete();
            }

            if(file.exists())         //如果文件本身就是目录 ，就要删除目录
                file.delete();
        }

    }



    public static void main(String[] args) throws IOException {
//        ArrayList<String> a = DecoUtil.unzip("C:\\a.zip"); // 返回解压缩出来的文件列表
//        for(String s : a){
//            System.out.println(s);
//        }
//
        DecoUtil d = new DecoUtil();
        String tempPath = "F:/some/";//d.getClass().getResource("").getPath();

        File file = new File("E:\\test\\demo.zip");
        List<File> a = unzip(file,tempPath);
        for(File s : a){
            System.out.println(file.getAbsoluteFile());
//            System.out.println(s.getPath()+"===="+s.getName()+"===="+s.getAbsolutePath());
            FileInputStream f = new FileInputStream(s);
            System.out.println("=="+f.read());
            f.close();
//            UploadUtils.uploadFile("image/ebank/upload/pro_detail/"+uploadPath,f,fname);
        }
        deleteTempFile(tempPath+"demo");
//        ArrayList<String> a = ectract("E:\\test\\demo.zip", "E:\\test\\"); // 返回解压缩出来的文件列表
//        for(String s : a){
//            System.out.println(s);
//        }

    }



    /**
     * 解压缩
     * @param sZipPathFile 要解压的文件
     * @param sDestPath 解压到某文件夹
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList ectract(File sZipPathFile, String sDestPath) {
        ArrayList<String> allFileName = new ArrayList<String>();
        try {
            // 先指定压缩档的位置和档名，建立FileInputStream对象
            FileInputStream fins = new FileInputStream(sZipPathFile);
            // 将fins传入ZipInputStream中
            ZipInputStream zins = new ZipInputStream(fins);
            ZipEntry ze = null;
            byte[] ch = new byte[256];
            while ((ze = zins.getNextEntry()) != null) {
                File zfile = new File(sDestPath + ze.getName());
                File fpath = new File(zfile.getParentFile().getPath());
                if (ze.isDirectory()) {
                    if (!zfile.exists())
                        zfile.mkdirs();
                    zins.closeEntry();
                } else {
                    if (!fpath.exists())
                        fpath.mkdirs();
                    FileOutputStream fouts = new FileOutputStream(zfile);
                    int i;
                    allFileName.add(zfile.getAbsolutePath());
                    while ((i = zins.read(ch)) != -1)
                        fouts.write(ch, 0, i);
                    zins.closeEntry();
                    fouts.close();
                }
            }
            fins.close();
            zins.close();
        } catch (Exception e) {
            System.err.println("Extract error:" + e.getMessage());
        }
        return allFileName;
    }



}
