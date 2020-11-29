package dev.kvan93.esportsnews;

import android.content.Intent;

public class esport {
    private String name;
    private Integer appId;
    private Integer backgroundurl;

    esport(String name, Integer appId, Integer backgroundurl){
        this.name = name;
        this.appId = appId;
        this.backgroundurl = backgroundurl;
    }

    public String getName() {
        return name;
    }

    public Integer getAppId() {
        return appId;
    }

    public Integer getBackgroundurl() {return backgroundurl; }
}
