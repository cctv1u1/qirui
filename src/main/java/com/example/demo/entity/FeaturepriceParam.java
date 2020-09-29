package com.example.demo.entity;

import java.util.List;

public class FeaturepriceParam {
    private String opflag ;// 1 开售，3 停售，2复售  4 修改语言 制式，保护价 5 注销
    private String verifyinfo;
    private String cinemano;
    private List<CinemaFeatureVo> features ;


    public String getOpflag() {
        return opflag;
    }

    public void setOpflag(String opflag) {
        this.opflag = opflag;
    }

    public String getVerifyinfo() {
        return verifyinfo;
    }

    public void setVerifyinfo(String verifyinfo) {
        this.verifyinfo = verifyinfo;
    }

    public String getCinemano() {
        return cinemano;
    }

    public void setCinemano(String cinemano) {
        this.cinemano = cinemano;
    }

    public List<CinemaFeatureVo> getFeatures() {
        return features;
    }

    public void setFeatures(List<CinemaFeatureVo> features) {
        this.features = features;
    }
}
