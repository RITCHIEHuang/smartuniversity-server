package com.njtech.smartuniversity.bean;

public class AuthorityBean {
    private Integer id;

    private String auName;

    private String auDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuName() {
        return auName;
    }

    public void setAuName(String auName) {
        this.auName = auName == null ? null : auName.trim();
    }

    public String getAuDetails() {
        return auDetails;
    }

    public void setAuDetails(String auDetails) {
        this.auDetails = auDetails == null ? null : auDetails.trim();
    }
}