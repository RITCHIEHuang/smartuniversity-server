package com.njtech.smartuniversity.bean;

/**
 * Created by ritchie on 7/3/18
 */
public class LoginBean extends AuthTokenBean {

    private int userType;

    private String userUUId;

    private String userID;

    public String getUserUUId() {
        return userUUId;
    }

    public void setUserUUId(String userUUId) {
        this.userUUId = userUUId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
