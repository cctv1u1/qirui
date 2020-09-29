package com.example.demo.entity;

import java.util.List;

public class CinemaFeatureVo {
    private String cinemano;
    private String copyno;
    private String featuretime;
    private String hallname;
    private String pricekindno ;
    private String copyprice ;
    private String copylang1 ;
    private String filmname;
    private String totaltime;
    private String featuredate ;
    private String featureno;
    private String seatcount;
    private String filmno;
    private String copylang ;
    private String hallno ;
    private String selfprice ;
    private String sefeatureno ;
    private String setclose;
    public String  hallfee;

    public List<CinemaSeatKindInfo> seatkindlist;// 座类票价

    public String getHallfee() {
        return hallfee;
    }

    public void setHallfee(String hallfee) {
        this.hallfee = hallfee;
    }

    public String getCinemano() {
        return cinemano;
    }

    public void setCinemano(String cinemano) {
        this.cinemano = cinemano;
    }

    public String getCopyno() {
        return copyno;
    }

    public void setCopyno(String copyno) {
        this.copyno = copyno;
    }

    public String getFeaturetime() {
        return featuretime;
    }

    public void setFeaturetime(String featuretime) {
        this.featuretime = featuretime;
    }

    public String getHallname() {
        return hallname;
    }

    public void setHallname(String hallname) {
        this.hallname = hallname;
    }

    public String getPricekindno() {
        return pricekindno;
    }

    public void setPricekindno(String pricekindno) {
        this.pricekindno = pricekindno;
    }

    public String getCopyprice() {
        return copyprice;
    }

    public void setCopyprice(String copyprice) {
        this.copyprice = copyprice;
    }

    public String getCopylang1() {
        return copylang1;
    }

    public void setCopylang1(String copylang1) {
        this.copylang1 = copylang1;
    }

    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public String getFeaturedate() {
        return featuredate;
    }

    public void setFeaturedate(String featuredate) {
        this.featuredate = featuredate;
    }

    public String getFeatureno() {
        return featureno;
    }

    public void setFeatureno(String featureno) {
        this.featureno = featureno;
    }

    public String getSeatcount() {
        return seatcount;
    }

    public void setSeatcount(String seatcount) {
        this.seatcount = seatcount;
    }

    public String getFilmno() {
        return filmno;
    }

    public void setFilmno(String filmno) {
        this.filmno = filmno;
    }

    public String getCopylang() {
        return copylang;
    }

    public void setCopylang(String copylang) {
        this.copylang = copylang;
    }

    public String getHallno() {
        return hallno;
    }

    public void setHallno(String hallno) {
        this.hallno = hallno;
    }

    public String getSelfprice() {
        return selfprice;
    }

    public void setSelfprice(String selfprice) {
        this.selfprice = selfprice;
    }

    public String getSefeatureno() {
        return sefeatureno;
    }

    public void setSefeatureno(String sefeatureno) {
        this.sefeatureno = sefeatureno;
    }

    public String getSetclose() {
        return setclose;
    }

    public void setSetclose(String setclose) {
        this.setclose = setclose;
    }

    public List<CinemaSeatKindInfo> getSeatkindlist() {
        return seatkindlist;
    }

    public void setSeatkindlist(List<CinemaSeatKindInfo> seatkindlist) {
        this.seatkindlist = seatkindlist;
    }
}
