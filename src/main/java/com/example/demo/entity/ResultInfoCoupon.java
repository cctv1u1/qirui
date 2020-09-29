package com.example.demo.entity;

import java.util.List;

public class ResultInfoCoupon {
    public String resultcode;
    public String resultmessage;

    public List<CouponInfo> couponinfo;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public List<CouponInfo> getCouponinfo() {
        return couponinfo;
    }

    public void setCouponinfo(List<CouponInfo> couponinfo) {
        this.couponinfo = couponinfo;
    }
}
