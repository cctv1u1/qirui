package com.example.demo.mapper.tic09;

import com.example.demo.entity.CouponInfo;
import com.example.demo.entity.t0100placeinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface tic09Mapper {


  public List<t0100placeinfo> slt0100place(Map<String, String> sqlparam);

  public void savehandshake(Map<String, String> sqlparam);

    public List<CouponInfo> queryCoupon(Map<String, String> sqlparam);

}