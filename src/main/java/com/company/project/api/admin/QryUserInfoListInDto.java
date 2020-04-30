package com.company.project.api.admin;

import com.company.project.core.BaseInDto;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryUserInfoListInDto extends BaseInDto {


    private String  realName;

    private String telNum;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
