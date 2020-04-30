package com.company.project.api;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class LoginByPhoneInDto {


    private String phone;
    private String passWord;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
