package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.Util.LogHelper;
import com.example.demo.Util.TimerHelper;
import com.example.demo.config.configproperties;
import com.example.demo.config.configyml;
import com.example.demo.entity.*;
import com.example.demo.service.Groundservice;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = {"/excel"})
public class Excelcontroller {

    @Autowired
    public Groundservice groundservice;
    @Autowired
    public configyml cfyml;
    @Autowired
    public configproperties cfp;
    @Autowired
    LogHelper loghelper;
    Logger logger = LoggerFactory.getLogger(getClass());



    private static final String UPLOAD_PATH = "WEB-INF\\upload\\img";

    @ResponseBody
    @RequestMapping(value = "/readExcel", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String readExcel(@RequestParam("filename1") MultipartFile filename1,
                            HttpServletRequest request, HttpServletResponse response) {
        String returnjson = "";
        try {
            // 保存EXCEL15
            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            System.out.println("path:"+path.getAbsolutePath());
            //如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(),"static/images/upload/");
            if(!upload.exists()) upload.mkdirs();
            System.out.println("upload url:"+upload.getAbsolutePath());
            String wuliPath = upload.getAbsolutePath();
            String fileName = filename1.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String uploadContentType = filename1.getContentType();
            String expandedName = "";
            DateFormat df = new SimpleDateFormat(new TimerHelper().GetTimerKey());
            Date now= new Date();
            fileName = df.format(now) + expandedName;
            String wulifilepath = wuliPath + "\\" + fileName + "." + suffix;
            filename1.transferTo(new File(wulifilepath));
            String resultcode="ok";
            String key= df.format(now);
            resultcode=readexcel1(wulifilepath,key);
        } catch (Exception e) {
            e.printStackTrace();
            return "1";
        }
        return "0";
    }

    @ResponseBody
    @RequestMapping(value = "/writeExcel", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void writeExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();                        // 创建工作簿对象
            HSSFSheet sheet = workbook.createSheet("SHEET1");                     // 创建工作表
            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);
            cellTiltle.setCellValue("这是内容");
            String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
            String headStr = "attachment; filename=\"" + fileName + "\"";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            OutputStream out1 = response.getOutputStream();
            workbook.write(out1);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public String readexcel1(String wulifilepath, String key) {
        String resultcode="1";
        try {
            Workbook workbook = null;
            try {
                workbook = new XSSFWorkbook(new File(wulifilepath));
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(new FileInputStream(new File(
                        wulifilepath)));
            }
            Sheet sheet = workbook.getSheet("TEST");
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String idcard="";
                if(row.getCell(1)!=null)
                {
                    idcard= row.getCell(1).toString();
                    System.out.println(idcard);
                }
                if(row.getCell(2)!=null)
                {
                    idcard= row.getCell(2).toString();
                    System.out.println(idcard);
                }
            }
        } catch (Exception e) {
            resultcode="2";
        }
        return resultcode;
    }





}
