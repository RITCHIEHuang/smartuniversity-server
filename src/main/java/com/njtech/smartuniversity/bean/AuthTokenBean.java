package com.njtech.smartuniversity.bean;

/**
 * Created by ritchie on 6/26/18
 */
public class AuthTokenBean {

    private String token;


    public AuthTokenBean() {
    }

    public AuthTokenBean(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
