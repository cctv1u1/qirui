package com.example.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.Util.LogHelper;
import com.example.demo.Util.TimerHelper;
import com.example.demo.config.configproperties;
import com.example.demo.config.configyml;
import com.example.demo.entity.*;
import com.example.demo.service.Groundservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = {"/ground"})
public class Groundcontroller {

    @Autowired
    public Groundservice groundservice;
    @Autowired
    public configyml cfyml;
    @Autowired
    public configproperties cfp;
    @Autowired
    LogHelper loghelper;
    Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/Handshake", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String Handshake(HttpServletRequest request) {
        String jsonstr="";
        String para="";
        String requestdate=new TimerHelper().GetNowyyyyMMddHHmmss();
        ResultInfo result=new ResultInfo();
        result.setResultcode("1");// 握手失败
        try {
            para=request.getParameter("para");
            String ip=request.getRemoteAddr();
            new LogHelper().createLogFile("Handshake","接收参数:"+para+",记录ip:"+ip);
            t0100placeinfo place_model=   (t0100placeinfo) JSON.parseObject(para, t0100placeinfo.class);
            groundservice.savehandshake(place_model.getPlaceno(),ip);
            result.setResultcode("0");
            result.setResultmessage("成功");
            jsonstr = JSONArray.toJSON(result).toString();
        }catch(Exception e)
        {
            e.printStackTrace();
            result.setResultcode("100500");// 握手失败
            result.setResultmessage("握手异常");
            jsonstr = JSONArray.toJSON(result).toString();
        }
        new LogHelper().WriteResult("Handshake",para,jsonstr,requestdate);
        return jsonstr;
    }


    @ResponseBody
    @RequestMapping(value = "/GetCouponInfo", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String GetCouponInfo(HttpServletRequest request) {
        String jsonstr="";
        String para="";
        String requestdate=new TimerHelper().GetNowyyyyMMddHHmmss();
        ResultInfoCoupon result=new ResultInfoCoupon();
        result.setResultcode("1");// 握手失败
        try {
            para=request.getParameter("para");
            new LogHelper().createLogFile("GetCouponInfo","接收参数:"+para);
            t0100placeinfo place_model=   (t0100placeinfo) JSON.parseObject(para, t0100placeinfo.class);
            List<CouponInfo> couponInfoList=groundservice.queryCoupon(place_model.getPlaceno());
            if(couponInfoList==null||couponInfoList.size()==0)
            {
                result.setResultcode("2");
                result.setResultmessage("没有合作券");
                result.setCouponinfo(couponInfoList);
            }else {
                result.setResultcode("0");
                result.setResultmessage("成功");
                result.setCouponinfo(couponInfoList);
            }
            jsonstr = JSONArray.toJSON(result).toString();
        }catch(Exception e)
        {
            e.printStackTrace();
            result.setResultcode("100500");// 握手失败
            result.setResultmessage("获取合作券异常");
            jsonstr = JSONArray.toJSON(result).toString();
        }
        new LogHelper().WriteResult("Handshake",para,jsonstr,requestdate);
        return jsonstr;
    }




    @ResponseBody
    @RequestMapping(value = "/queryT0100", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String queryT0100() {
        String jsonstr = "";
        List<t0100placeinfo> t0100_list=groundservice.queryT0100();
        jsonstr = JSONArray.toJSON(t0100_list).toString();
        return jsonstr;
    }

    @ResponseBody
    @RequestMapping(value = "/queryT0922", method ={RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String queryT0922() {
        String jsonstr = "";
        List<t0922> t0100_list=groundservice.queryt0922();
        jsonstr = JSONArray.toJSON(t0100_list).toString();
        return jsonstr;
    }



}
