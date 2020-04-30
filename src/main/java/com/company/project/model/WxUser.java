package com.company.project.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wx_user")
public class WxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 小程序用户openid
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @Column(name = "pass_word")
    private String passWord;

    /**
     * 用户头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 性别   0 男  1  女  2 人妖
     */
    private Integer gender;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 手机号码
     */
    @Column(name = "tel_num")
    private String telNum;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 佣金费率
     */
    @Column(name = "commission_rate")
    private BigDecimal commissionRate;

    /**
     * 有效户提成
     */
    @Column(name = "valid_amt")
    private BigDecimal validAmt;

    /**
     * 时间戳
     */
    private Date ts;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取小程序用户openid
     *
     * @return open_id - 小程序用户openid
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置小程序用户openid
     *
     * @param openId 小程序用户openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户密码
     *
     * @return pass_word - 用户密码
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置用户密码
     *
     * @param passWord 用户密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取用户头像
     *
     * @return avatar_url - 用户头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置用户头像
     *
     * @param avatarUrl 用户头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取性别   0 男  1  女  2 人妖
     *
     * @return gender - 性别   0 男  1  女  2 人妖
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别   0 男  1  女  2 人妖
     *
     * @param gender 性别   0 男  1  女  2 人妖
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取所在国家
     *
     * @return country - 所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置所在国家
     *
     * @param country 所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取手机号码
     *
     * @return tel_num - 手机号码
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * 设置手机号码
     *
     * @param telNum 手机号码
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取佣金费率
     *
     * @return commission_rate - 佣金费率
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置佣金费率
     *
     * @param commissionRate 佣金费率
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 获取有效户提成
     *
     * @return valid_amt - 有效户提成
     */
    public BigDecimal getValidAmt() {
        return validAmt;
    }

    /**
     * 设置有效户提成
     *
     * @param validAmt 有效户提成
     */
    public void setValidAmt(BigDecimal validAmt) {
        this.validAmt = validAmt;
    }

    /**
     * 获取时间戳
     *
     * @return ts - 时间戳
     */
    public Date getTs() {
        return ts;
    }

    /**
     * 设置时间戳
     *
     * @param ts 时间戳
     */
    public void setTs(Date ts) {
        this.ts = ts;
    }
}