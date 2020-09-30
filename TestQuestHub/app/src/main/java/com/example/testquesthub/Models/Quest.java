package com.example.testquesthub.Models;

public class Quest {
    private String ad, info, codeword, userId, name;

    public Quest() {
    }

    public Quest(String name, String ad, String info, String codeword) {
        this.ad = ad;
        this.info = info;
        this.codeword = codeword;
        this.name = name;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCodeword() {
        return codeword;
    }

    public void setCodeword(String codeword) {
        this.codeword = codeword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
