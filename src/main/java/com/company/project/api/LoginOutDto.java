package com.company.project.api;

import com.company.project.model.WxUser;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class LoginOutDto {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public WxUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxUser userInfo) {
        this.userInfo = userInfo;
    }

    private WxUser userInfo;
}
