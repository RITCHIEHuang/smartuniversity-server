package com.njtech.smartuniversity.bean;

public class GoodsBean {
    private Integer id;

    private String gName;

    private String gPrice;

    private Integer gQuantity;

    private String gLy;

    private String gVid;

    private String gImg;

    private String gSimg;

    private String gLabel;

    private String gDetails;

    private String gState;

    private String sIUuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public String getgPrice() {
        return gPrice;
    }

    public void setgPrice(String gPrice) {
        this.gPrice = gPrice == null ? null : gPrice.trim();
    }

    public Integer getgQuantity() {
        return gQuantity;
    }

    public void setgQuantity(Integer gQuantity) {
        this.gQuantity = gQuantity;
    }

    public String getgLy() {
        return gLy;
    }

    public void setgLy(String gLy) {
        this.gLy = gLy == null ? null : gLy.trim();
    }

    public String getgVid() {
        return gVid;
    }

    public void setgVid(String gVid) {
        this.gVid = gVid == null ? null : gVid.trim();
    }

    public String getgImg() {
        return gImg;
    }

    public void setgImg(String gImg) {
        this.gImg = gImg == null ? null : gImg.trim();
    }

    public String getgSimg() {
        return gSimg;
    }

    public void setgSimg(String gSimg) {
        this.gSimg = gSimg == null ? null : gSimg.trim();
    }

    public String getgLabel() {
        return gLabel;
    }

    public void setgLabel(String gLabel) {
        this.gLabel = gLabel == null ? null : gLabel.trim();
    }

    public String getgDetails() {
        return gDetails;
    }

    public void setgDetails(String gDetails) {
        this.gDetails = gDetails == null ? null : gDetails.trim();
    }

    public String getgState() {
        return gState;
    }

    public void setgState(String gState) {
        this.gState = gState == null ? null : gState.trim();
    }

    public String getsIUuid() {
        return sIUuid;
    }

    public void setsIUuid(String sIUuid) {
        this.sIUuid = sIUuid == null ? null : sIUuid.trim();
    }
}