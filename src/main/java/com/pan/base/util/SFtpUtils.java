package com.pan.base.util;

import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Pangaofeng on 2016/11/8.
 */
public class SFtpUtils {

    private static Logger logger = LogManager.getLogger(SFtpUtils.class);

    private static ChannelSftp sftp;

    private static Session session;

    public static void connect(){
        try {
            String path = "";
            String addr = "";
            String port = "";
            String username = "";
            String password = "";
            String url = "";

            JSch jsch = new JSch();

            session = jsch.getSession(username, addr, Integer.parseInt(port));

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    private static void disConnect() throws Exception {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
    /**
     * 获取文件夹中所有文件名称
     * @param contantUrl
     */
    public static List<String> readFile(String contantUrl) throws Exception {
        List<String> list = new ArrayList<String>();
        try {
            connect();
            Vector vv = sftp.ls(contantUrl);
            for(Object object : vv){
                ChannelSftp.LsEntry entry=(ChannelSftp.LsEntry)object;
                String filename=entry.getFilename();
                list.add(contantUrl+"/"+filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            disConnect();
        }
        return list;

    }


    /**
     * 下载文件
     * @param directory
     * @param downloadFile
     * @param saveFile
     */
    public static void download(String directory, String downloadFile,String saveFile) throws Exception {
        try {
            connect();
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            disConnect();
        }
    }

    /**
     * 下载文件
     * @param directory
     * @param downloadFileUrl
     * @param saveUrl
     */
    public static Boolean downloadList(String directory, List<String> downloadFileUrl,String saveUrl) throws Exception {
        try {
            connect();
            sftp.cd(directory);
            int index = 0;
            for(String url : downloadFileUrl){
                String fileName = url.substring(url.lastIndexOf("/")+1, url.length());
                File file = new File(saveUrl+fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                sftp.get(url, fileOutputStream);
                fileOutputStream.close();
                index ++;
            }

            if(index != downloadFileUrl.size()){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            disConnect();
        }
        return true;
    }

    /**
     * 删除文件
     * @param directory
     * @param deleteFile
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) throws Exception {
        try {
            connect();
            sftp.cd(directory);
            sftp.rm(deleteFile);
            System.out.println("删除成功");
        } catch (Exception e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }finally {
            disConnect();
        }
    }

    /**
     * 批量删除文件
     * @param directory
     * @param fileUrl
     */
    public static void delAllFile(String directory, List<String> fileUrl) throws Exception{
        try {
            connect();
            sftp.cd(directory);
            for(String url : fileUrl){
                if(!url.equals("/data/zh-weidai/..") && !url.equals("/data/zh-weidai/.gnome2") &&
                        !url.equals("/data/zh-weidai/.") && !url.equals("/data/zh-weidai/.bashrc") &&
                        !url.equals("/data/zh-weidai/.bash_profile")){
                    System.out.println(url);
                    sftp.rm(url);
                }
            }
        } catch (Exception e) {
            logger.error("删除失败",e);
            e.printStackTrace();
        }finally {
            disConnect();
        }
    }
}
