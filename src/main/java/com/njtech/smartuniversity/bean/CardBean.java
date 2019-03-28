package com.njtech.smartuniversity.bean;

import java.util.Date;

public class CardBean {
    private Integer id;

    private String cardVid;

    private String sqId;

    private Integer authId;

    private Date applicationTime;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardVid() {
        return cardVid;
    }

    public void setCardVid(String cardVid) {
        this.cardVid = cardVid == null ? null : cardVid.trim();
    }

    public String getSqId() {
        return sqId;
    }

    public void setSqId(String sqId) {
        this.sqId = sqId == null ? null : sqId.trim();
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}