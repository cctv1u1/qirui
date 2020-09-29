package com.example.demo.WeiXin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Util.HttpRequest;
import com.example.demo.Util.HttpRespons;
import com.example.demo.entity.SENDWX;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxHelper {
    /**
     * 获取AccessToken
     *
     * @return
     */
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String APPID = "wxa0b74775d2d4e574";
    private static final String APPSECRET = "a4b88210ed2f7ee389cb85996c87391f";
    // 日志操作类
    private static Logger logger = Logger.getLogger(WxHelper.class);


    /**
     * 获取ACCESSTOKEN
     *
     * @return
     */
    public String getAccessTokenTask() {
        String accessTokenUrl = token_url.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        System.out.println("URL for getting accessToken accessTokenUrl=" + accessTokenUrl);

        String accessToken = "";
        try {
            URL url = new URL(accessTokenUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();

            //获取返回的字符
            InputStream inputStream = connection.getInputStream();
            int size = inputStream.available();
            byte[] bs = new byte[size];
            inputStream.read(bs);
            String message = new String(bs, "UTF-8");

            //获取access_token
            JSONObject jsonObject = JSONObject.parseObject(message);
            accessToken = jsonObject.getString("access_token");
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    // 这个是用来获取OPENID的接口
    // 这里需要特别注意你通过小程序前台获取的code的那个小程序必须是开发者账号的appid的小程序
    // 比如你小程序的appid是A，那么如果你开发工具的appid是B，则B获取到的code无效，因为他并非是获取A的CODE
    public String code2Session(String jscode) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        url = url.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("JSCODE", jscode);
        logger.info(url);
        HttpRequest httpRequest = new HttpRequest();
        HttpRespons hr = null;
        try {
            hr = httpRequest.sendGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(hr.getContent());
        return hr.getContent();
    }


    /**
     * 推送微信消息模板（前提是用户必须订阅，5分钟之内只可以推送一次）
     *
     * @return
     */
    public String send(String access_token, String touser, String template_id, String hpName, String hpUnit, Long hpCount, Long hpWarnCount) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + access_token;
        Map<String, String> map = new HashMap<String, String>();
        SENDWX sendmodel = new SENDWX();// 构建微信后台的发送消息的模板的实体
//        sendmodel.setTemplate_id(template_id);
//        sendmodel.setTouser(touser);
//        // 设置SENDWX的data开始-----------------------
//        Data datamodel = new Data();
//        Thing1 thing1 = new Thing1();
//        thing1.setValue("货品" + hpName + "-库存量" + hpCount + hpUnit + "-警告存量" + hpWarnCount);
//        Number3 n2 = new Number3();
//        n2.setValue(hpCount.toString());
//        Number2 n1 = new Number2();
//        n1.setValue(hpWarnCount.toString());
//        datamodel.setThing1(thing1);
//        datamodel.setNumber3(n2);
//        datamodel.setNumber2(n1);
////		datamodel.setName01(n1);
//        sendmodel.setData(datamodel);
        // 设置SENDWX的data结束-----------------------

        String reqbodystr = JSON.toJSONString(sendmodel);
        logger.info("reqbodystr:" + reqbodystr);

        HttpRequest httpRequest = new HttpRequest();
        HttpRespons hr = null;
        try {
            hr = httpRequest.sendPost(url, reqbodystr, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("发送WeiXin模板返回原文：" + hr.getContent());
        String message = hr.getContent();
        JSONObject jsonObject = JSONObject.parseObject(message);
        String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");
        logger.info("==errcode==" + errcode);
        logger.info("==errmsg==" + errmsg);
        return errcode;
    }

}
