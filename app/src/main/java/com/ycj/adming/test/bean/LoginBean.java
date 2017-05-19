package com.ycj.adming.test.bean;

/**
 * Created by adming on 2017/5/10.
 */

public class LoginBean {
    private Long indexs;
    private long sid;
    private String authflag;
    private String countycode;
    private String fullname;
    private String headpicture;
    private String idcard;
    private String logintoken;
    private String mobilephone;
    private String regterminal;
    private String token;

    public LoginBean() {
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "indexs=" + indexs +
                ", sid=" + sid +
                ", authflag='" + authflag + '\'' +
                ", countycode='" + countycode + '\'' +
                ", fullname='" + fullname + '\'' +
                ", headpicture='" + headpicture + '\'' +
                ", idcard='" + idcard + '\'' +
                ", logintoken='" + logintoken + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", regterminal='" + regterminal + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Long getIndexs() {
        return indexs;
    }

    public void setIndexs(Long indexs) {
        this.indexs = indexs;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getAuthflag() {
        return authflag;
    }

    public void setAuthflag(String authflag) {
        this.authflag = authflag;
    }

    public String getCountycode() {
        return countycode;
    }

    public void setCountycode(String countycode) {
        this.countycode = countycode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHeadpicture() {
        return headpicture;
    }

    public void setHeadpicture(String headpicture) {
        this.headpicture = headpicture;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getLogintoken() {
        return logintoken;
    }

    public void setLogintoken(String logintoken) {
        this.logintoken = logintoken;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getRegterminal() {
        return regterminal;
    }

    public void setRegterminal(String regterminal) {
        this.regterminal = regterminal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
