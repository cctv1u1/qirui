package com.example.demo.service;


import com.example.demo.entity.CouponInfo;
import com.example.demo.entity.t0100placeinfo;
import com.example.demo.entity.t0922;
import com.example.demo.mapper.ground.groundMapper;
import com.example.demo.mapper.tic09.tic09Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Groundservice {


    @Autowired
    private groundMapper groundMapper;

    @Autowired
    private tic09Mapper tic09Mapper;

    public void savehandshake(String placeno,String ip)
    {
            Map<String,String> sqlmap=new HashMap<>();
            sqlmap.put("placeno",placeno);
            sqlmap.put("ip",ip);
            tic09Mapper.savehandshake(sqlmap);
    }

    public List<CouponInfo> queryCoupon(String placeno)
    {
        Map<String,String> sqlmap=new HashMap<>();
        sqlmap.put("placeno",placeno);
        List<CouponInfo>  couponlist= tic09Mapper.queryCoupon(sqlmap);
        return couponlist;
    }




    public List<t0100placeinfo> queryT0100()
    {
        Map<String,String> sqlmap=new HashMap<>();
        List<t0100placeinfo> slt=tic09Mapper.slt0100place(sqlmap);
        return slt;
    }

    public List<t0922> queryt0922()
    {
        Map<String,String> sqlmap=new HashMap<>();
        List<t0922> slt=groundMapper.query0992(sqlmap);
        return slt;
    }








}
