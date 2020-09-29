package com.example.demo.TEST;

import com.alibaba.fastjson.JSON;
import com.example.demo.Util.HttpRequest;
import com.example.demo.Util.HttpRespons;
import com.example.demo.entity.CinemaFeatureVo;
import com.example.demo.entity.CinemaSeatKindInfo;
import com.example.demo.entity.FeaturepriceParam;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cinemaPhFeatureTEST {
    public static void main(String[] args) throws IOException {

        String funName = "cinemaPhFeature";
        // TODO Auto-generated method stub
        URL url = null;
        HttpURLConnection connection = null;
        String postParm=KaishouPara();


        Map<String, String> map = new HashMap<String, String>();
        map.put("para",postParm);
        HttpRequest httpRequest= new HttpRequest();
        HttpRespons hr = null;
        try {
            hr = httpRequest.sendPost(TESTURL.URL+funName,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(hr.getContent());

    }

    public static String KaishouPara()
    {
        FeaturepriceParam featurepriceParam=new FeaturepriceParam();
        featurepriceParam.setCinemano("77777777");
        featurepriceParam.setOpflag("1");// 开售
        List<CinemaFeatureVo> cinemaFeatureVoList=new ArrayList<CinemaFeatureVo>();
        CinemaFeatureVo model=new CinemaFeatureVo();
        model.setFeatureno("001");
        model.setCinemano("77777777");
        model.setCopylang("02");
        model.setCopylang1("03");
        model.setCopyno("03");
        model.setCopyprice("100");//标准价
        model.setFeaturedate("2020-09-24");
        model.setFeaturetime("20:00");
        model.setFilmno("10001");
        model.setFilmname("测试影片名称A");
        model.setHallno("02");
        model.setHallname("测试厅2");
        model.setPricekindno("01");
        model.setSeatcount("100");
        model.setSefeatureno("");//传空,联场不生成排期应用
        model.setSelfprice("10");
        model.setSetclose("1");
        model.setTotaltime("100");
        List<CinemaSeatKindInfo> seatkindlist=new ArrayList<CinemaSeatKindInfo>();
        CinemaSeatKindInfo seatmodel=new CinemaSeatKindInfo();
        seatmodel.setPrice("101");
        seatmodel.setSeatkindname("座类01");
        seatmodel.setSeatkindno("01");
        seatkindlist.add(seatmodel);
        seatmodel=new CinemaSeatKindInfo();
        seatmodel.setPrice("102");
        seatmodel.setSeatkindname("座类02");
        seatmodel.setSeatkindno("02");
        seatkindlist.add(seatmodel);
        model.setSeatkindlist(seatkindlist);
        cinemaFeatureVoList.add(model);

        model=new CinemaFeatureVo();
        model.setCinemano("77777777");
        model.setFeatureno("002");
        model.setCopylang("03");
        model.setCopylang1("04");
        model.setCopyno("04");
        model.setCopyprice("200");//标准价
        model.setFeaturedate("2020-09-25");
        model.setFeaturetime("21:00");
        model.setFilmno("10002");
        model.setFilmname("测试影片名称B");
        model.setHallno("03");
        model.setHallname("测试厅3");
        model.setPricekindno("02");
        model.setSeatcount("200");
        model.setSefeatureno("");//传空,联场不生成排期应用
        model.setSelfprice("20");
        model.setSetclose("1");
        model.setTotaltime("120");
       seatkindlist=new ArrayList<CinemaSeatKindInfo>();
       seatmodel=new CinemaSeatKindInfo();
        seatmodel.setPrice("103");
        seatmodel.setSeatkindname("座类03");
        seatmodel.setSeatkindno("03");
        seatkindlist.add(seatmodel);
        seatmodel=new CinemaSeatKindInfo();
        seatmodel.setPrice("104");
        seatmodel.setSeatkindname("座类04");
        seatmodel.setSeatkindno("04");
        seatkindlist.add(seatmodel);
        model.setSeatkindlist(seatkindlist);
        cinemaFeatureVoList.add(model);

        featurepriceParam.setFeatures(cinemaFeatureVoList);
        String postParm = JSON.toJSONString(featurepriceParam);
        System.out.println(postParm);
        return postParm;
    }


}
