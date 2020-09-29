package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.Util.LogHelper;
import com.example.demo.config.configproperties;
import com.example.demo.config.configyml;
import com.example.demo.entity.*;

import com.example.demo.service.Groundservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = {"/user"})
public class Logincontroller {

    //String jsonstr="{\"username\":\"张三1\",\"sex\":\"男\",\"class\":\"一班\"}";
    @Autowired
    public Groundservice userservice;
    @Autowired
    public configyml cfyml;
    @Autowired
    public configproperties cfp;
    @Autowired
    LogHelper loghelper;
    Logger logger = LoggerFactory.getLogger(getClass());



    @RequestMapping(value = "/loginx", method = RequestMethod.GET)
    public String loginHtml() {

        return "loginsuccess";
    }


//    @RequestMapping(value = "/dologin")
//    public String dologin(HashMap<String, Object> map, HttpServletRequest request) {
//        map.put("u", request.getParameter("username"));
//        map.put("p", request.getParameter("password"));
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        t001userinfo usermodel = userservice.dologin(username, password);
//        if (usermodel != null) {
//            return "loginsuccess";
//        } else {
//            return "loginerror";
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/doUpload")
    public String doUpload(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "imagefile", required = false) MultipartFile imagefile)
            throws Exception {
        String resultjson="";
      String imagepath="D:/images/upload/";
      SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
      String newfilename= sdf.format(new Date());
        try {
            logger.info("开始保存图片");
            String type = imagefile.getOriginalFilename().substring(imagefile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
            newfilename=newfilename+type;//新文件名=随机名字+后缀
            File targetFile = new File(imagepath, newfilename);
            imagefile.transferTo(targetFile);
            logger.info("结束保存图片");
            String localfilepath=imagepath+newfilename;
            uploadResult result=new uploadResult();
            result.setLocalpath(localfilepath);
            result.setUrlpath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207512589&di=7081872b591747a2b0ed76dfc587acc5&imgtype=0&src=http%3A%2F%2Fimage.huahuibk.com%2Fuploads%2F20190228%2F22%2F1551363555-josXTSABFD.jpg");
            result.setResultcode("0");
            resultjson= JSON.toJSONString(result);

            logger.info("返回存储结果"+resultjson);
            return resultjson;
        } catch (Exception e) {
            logger.info("保存图片异常"+e.toString());
            return "保存图片异常";
        }
    }








    //    @RequestBody Map<String, Object> params
    @ResponseBody
    @RequestMapping(value = "/testjson", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String writeByBody() {
        String jsonstr = "";
        LogHelper logger=new LogHelper();
        loghelper.createLogFile("","hahawowo");

        List<testshuiguo> shuiguolist=new ArrayList<testshuiguo>();
        testshuiguo model1=new testshuiguo();
        model1.setId("0");
        model1.setShuiguoname("苹果");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207512589&di=7081872b591747a2b0ed76dfc587acc5&imgtype=0&src=http%3A%2F%2Fimage.huahuibk.com%2Fuploads%2F20190228%2F22%2F1551363555-josXTSABFD.jpg");
        model1.setPrice("20.90");
        shuiguolist.add(model1);

        model1=new testshuiguo();
        model1.setId("1");
        model1.setShuiguoname("橘子");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207552304&di=618f326f810a8e6579b3a5429598bb82&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180820%2Fb16e20fa034248a489b3d03a4d9bbe24.jpeg");
        model1.setPrice("100.00");
        shuiguolist.add(model1);

        model1=new testshuiguo();
        model1.setId("2");
        model1.setShuiguoname("香蕉");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207531641&di=128b7bdd1ba2913005f790bb46b9c409&imgtype=0&src=http%3A%2F%2Fdpic.tiankong.com%2Frh%2Fwv%2FQJ6763114805.jpg");
        model1.setPrice("20.00");
        shuiguolist.add(model1);

        model1=new testshuiguo();
        model1.setId("3");
        model1.setShuiguoname("鸭梨");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207584130&di=f2d82bdf6577d990ee5c4a103fb89976&imgtype=0&src=http%3A%2F%2Fp.shancaoxiang.com%2F2018%2F0225%2F20150922_2b2fe3d34beb9fcb6490TMnfojFdvPD9.png");
        model1.setPrice("40.00");
        shuiguolist.add(model1);

        model1=new testshuiguo();
        model1.setId("4");
        model1.setShuiguoname("鸭梨4");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207584130&di=f2d82bdf6577d990ee5c4a103fb89976&imgtype=0&src=http%3A%2F%2Fp.shancaoxiang.com%2F2018%2F0225%2F20150922_2b2fe3d34beb9fcb6490TMnfojFdvPD9.png");
        model1.setPrice("40.00");
        shuiguolist.add(model1);

        model1=new testshuiguo();
        model1.setId("5");
        model1.setShuiguoname("鸭梨5");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207584130&di=f2d82bdf6577d990ee5c4a103fb89976&imgtype=0&src=http%3A%2F%2Fp.shancaoxiang.com%2F2018%2F0225%2F20150922_2b2fe3d34beb9fcb6490TMnfojFdvPD9.png");
        model1.setPrice("50.00");
        shuiguolist.add(model1);
        jsonstr = JSONArray.toJSON(shuiguolist).toString();

        model1=new testshuiguo();
        model1.setId("6");
        model1.setShuiguoname("鸭梨6");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207584130&di=f2d82bdf6577d990ee5c4a103fb89976&imgtype=0&src=http%3A%2F%2Fp.shancaoxiang.com%2F2018%2F0225%2F20150922_2b2fe3d34beb9fcb6490TMnfojFdvPD9.png");
        model1.setPrice("60.00");
        shuiguolist.add(model1);

        model1=new testshuiguo();
        model1.setId("7");
        model1.setShuiguoname("鸭梨7");
        model1.setImageurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597207584130&di=f2d82bdf6577d990ee5c4a103fb89976&imgtype=0&src=http%3A%2F%2Fp.shancaoxiang.com%2F2018%2F0225%2F20150922_2b2fe3d34beb9fcb6490TMnfojFdvPD9.png");
        model1.setPrice("70.00");
        shuiguolist.add(model1);
        jsonstr = JSONArray.toJSON(shuiguolist).toString();
        return jsonstr;
    }

//    @ResponseBody
//    @RequestMapping(value = "/dosave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String dosave(@RequestBody Map<String, Object> params) {
//        String jsonstr = "{\"resultcode\":\"0\",\"message\":\"保存成功\"}";
//        try {
//
//            String username = params.get("username").toString();
//            String sex = params.get("sex").toString();
//            String banji = params.get("banji").toString();
//            userservice.saveuser(username, sex, banji);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            jsonstr = "{\"resultcode\":\"1\",\"message\":\"保存异常\"}";
//        }
//        return jsonstr;
//    }

//    @ResponseBody
//    @RequestMapping(value = "/getlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public String getlist(@RequestBody Map<String, Object> params) {
//        String jsonstr = "";
//        try {
//            String username = params.get("username").toString();
//            String sex = params.get("sex").toString();
//            String banji = params.get("banji").toString();
//            int pageindex = Integer.parseInt(params.get("pageindex").toString());
//            int pagesize = Integer.parseInt(params.get("pagesize").toString());
//            jsonstr = userservice.getlist(username, sex, banji, pageindex, pagesize);
//            logger.info("输出配置文件yml：" + cfyml.getForce());
//            logger.error("输出配置文件properties：" + cfp.getFirstname());
//
//
//            return jsonstr;
//        } catch (Exception ex) {
//            System.out.println("getlist异常");
//            ex.printStackTrace();
//            jsonstr = "";
//            //
//        }
//        return jsonstr;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/showuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String showuser(@RequestBody Map<String, Object> params) {
//        String jsonstr = "";
//        try {
//            String id = params.get("id").toString();
//            student studentmodel = userservice.showuser(id);
//            Object obj2 = JSONArray.toJSON(studentmodel);
//            jsonstr = obj2.toString();
//            return jsonstr;
//        } catch (Exception ex) {
//            System.out.println("showuser异常");
//            ex.printStackTrace();
//            jsonstr = "";
//            //
//        }
//        return jsonstr;
//    }

//    @ResponseBody
//    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String deleteuser(@RequestBody Map<String, Object> params) {
//        String jsonstr = "{\"resultcode\":\"0\",\"message\":\"删除成功\"}";
//        try {
//            String id = params.get("id").toString();
//            userservice.deleteuser(id);
//        } catch (Exception ex) {
//            System.out.println("deleteuser异常");
//            ex.printStackTrace();
//            jsonstr = "{\"resultcode\":\"1\",\"message\":\"保存异常\"}";
//        }
//        return jsonstr;
//    }


}
