package com.pan.base.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Pangaofeng
 * Date:2018/7/27
 */
public class FileUtils {

    private static Logger logger = LogManager.getLogger(FileUtils.class);

    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath,boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(),isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    /**
     * 获取指定文件夹下所有文件的文件名
     * @param filepath 指定路径
     * @return
     */
    public static List<String> readZipFile(String filepath) {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(filepath);
            for (File f : file.listFiles()){
                System.out.println(f.getName());
                logger.info(f.getName());
                list.add(f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取指定文件夹下所有文件的文件路径
     * @param filepath 指定路径
     * @return
     */
    public static List<String> readfile(String filepath) throws FileNotFoundException, IOException {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                logger.info("文件");
                logger.info("path=" + file.getPath());
                logger.info("absolutepath=" + file.getAbsolutePath());
                logger.info("name=" + file.getName());
            } else if (file.isDirectory()) {
                logger.info("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        logger.info("path=" + readfile.getPath());
                        logger.info("absolutepath=" + readfile.getAbsolutePath());
                        logger.info("name=" + readfile.getName());
                        list.add(readfile.getAbsolutePath());
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.info("readfile()   Exception:" + e.getMessage());
        }
        return list;
    }
    /**
     * 删除指定文件夹下所有文件   不包括对账文件
     * @param path 指定路径
     * @return
     */
    public static void delAllFile(String path,String str) {

        File file = new File(path);
        if (!file.exists()) {
            return ;
        }
        if (!file.isDirectory()) {
            return ;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            System.out.println(temp);
            Boolean f = StringUtils.isContains(tempList[i],str);
            if (temp.isFile() && !f) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i],str);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i],str);//再删除空文件夹
            }
        }
    }

    //删除文件夹
    public static void delFolder(String folderPath,String str) {
        try {
            delAllFile(folderPath,str); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析所有对账信息
     * @param depositeFilePathToday
     * @return
     */
    public static Map<String,List<List<String>>> depositeParseAllFile(String depositeFilePathToday){
        Map<String,List<List<String>>> paramsListMap = new HashMap<String,List<List<String>>>();
        List<List<String>> paramsList = new ArrayList<List<String>>();
        try {
            File file = new File(depositeFilePathToday);
            for (File f : file.listFiles()){
                if (f.isFile()){
                    //仅处理文件
                    paramsList = depositeParse(f);
                }

                //key=文件名 value=文件内容，不含字段行-即第一行
                if (!CollectionUtils.isEmpty(paramsList)){
                    paramsListMap.put(f.getName(),paramsList);
                    //paramsList = new ArrayList<List<String>>();
                }
            }
            return paramsListMap ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 解析制定对账文件的信息
     * @param file
     * @return
     */
    public static List<List<String>> depositeParse(File file){
        List<List<String>> paramsList = new ArrayList<List<String>>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null){
                count ++ ;
                if (count == 1){
                    continue;
                }

                String[] strArr = line.split(",");
                List<String> rowParamsList = new ArrayList<String>();
                for (String param : strArr){
                    rowParamsList.add((null==param)?"":param.toString());
                }
                /*String[] strArr = line.split(",",line.length());
                List<String> rowParamsList = new ArrayList<String>();
                for (int x=0;x<strArr.length;x++){
                    rowParamsList.add((null==strArr[x])?"":strArr[x].toString());
                }*/
                paramsList.add(rowParamsList);
            }

            return paramsList ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
