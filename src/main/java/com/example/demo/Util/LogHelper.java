package com.example.demo.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public  class LogHelper {

    public String logpath="d://springboot";//设置日志存放路径

    Logger logger = LoggerFactory.getLogger(getClass());
    public void createLogFile(String key, String logstr) {

        String keypath="";
        if(!key.equals(""))
        {
            keypath=key+"/";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = sdf2.format(new Date());
        logger.info("请求时间:"+nowdate+"|"+logstr);
        StringBuffer fileName = new StringBuffer();
//        String tomcatPath = System.getProperty("catalina.home");
        String tomcatPath=logpath;
        fileName.append(tomcatPath + "/logfile/").append(keypath).append(sdf.format(new Date())).append(".txt");
        CreateFile(fileName.toString());
        StringBuffer strAll = new StringBuffer("--------请求时间:"+nowdate).append(" \r\n");
        strAll.append(logstr).append("\r\n");
        try {
            FileWriter writer = new FileWriter(fileName.toString(), true);
            writer.write(strAll.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void WriteResult(String key, String requeststr,String returnstr,String requesttime) {
        try {
        String keypath="";
        if(!key.equals(""))
        {
            keypath=key+"/";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = sdf2.format(new Date());
        logger.info("请求参数:"+requeststr+"请求时间"+requesttime+"返回结果:"+returnstr+"结束时间:"+nowdate);
        StringBuffer fileName = new StringBuffer();
//        String tomcatPath = System.getProperty("catalina.home");
        String tomcatPath=logpath;
        fileName.append(tomcatPath + "/logfile/").append(keypath).append(sdf.format(new Date())).append(".txt");
        CreateFile(fileName.toString());
        StringBuffer strAll = new StringBuffer("--请求时间:"+requesttime+"--结束时间:"+nowdate).append(" \r\n");
        strAll.append("请求参数"+requeststr).append("\r\n");
        strAll.append("返回结果"+returnstr).append("\r\n");
        strAll.append("-------------------------------------------").append("\r\n");

            FileWriter writer = new FileWriter(fileName.toString(), true);
            writer.write(strAll.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean CreateFile(String strFileName) {
        File file = new File(strFileName);
        if (file.exists()) {
            // log4j.info("文件" + strFileName + "已存在！");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // log4j.info("目标文件所在路径不存在，准备创建");
            if (!file.getParentFile().mkdirs()) {
                return false;
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
