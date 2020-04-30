package com.company.project.api;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class TradingVolumeDetail {
    /**
     * 客户号
     */
    private String ciNo;

    /**
     * 统计日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date statisticalDate;

    /**
     * 客户名称
     */
    private String ciName;

    /**
     * 开户日期
     */
    @JSONField(format="yyyy-MM-dd")
    private Date openDate;

    /**
     * 普通交易量
     */
    private BigDecimal tradingVolume;

    /**
     * 普通净佣金
     */
    private BigDecimal netCommission;

    /**
     * 客户经纪人id
     */
    private String agentId;

    /**
     * 客户经纪人名称
     */
    private String agentName;

    /**
     * 是否为有效户
     */
    private String validFlag;

    /**
     * 成为有效户时间
     */
    private String validFlagDate;

    /**
     * 时间戳
     */
    private Date ts;

    /**
     * 获取客户号
     *
     * @return ci_no - 客户号
     */
    public String getCiNo() {
        return ciNo;
    }

    /**
     * 设置客户号
     *
     * @param ciNo 客户号
     */
    public void setCiNo(String ciNo) {
        this.ciNo = ciNo;
    }

    /**
     * 获取统计日期
     *
     * @return statistical_date - 统计日期
     */
    public Date getStatisticalDate() {
        return statisticalDate;
    }

    /**
     * 设置统计日期
     *
     * @param statisticalDate 统计日期
     */
    public void setStatisticalDate(Date statisticalDate) {
        this.statisticalDate = statisticalDate;
    }

    /**
     * 获取客户名称
     *
     * @return ci_name - 客户名称
     */
    public String getCiName() {
        return ciName;
    }

    /**
     * 设置客户名称
     *
     * @param ciName 客户名称
     */
    public void setCiName(String ciName) {
        this.ciName = ciName;
    }

    /**
     * 获取开户日期
     *
     * @return open_date - 开户日期
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * 设置开户日期
     *
     * @param openDate 开户日期
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 获取普通交易量
     *
     * @return trading_volume - 普通交易量
     */
    public BigDecimal getTradingVolume() {
        return tradingVolume;
    }

    /**
     * 设置普通交易量
     *
     * @param tradingVolume 普通交易量
     */
    public void setTradingVolume(BigDecimal tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    /**
     * 获取普通净佣金
     *
     * @return net_commission - 普通净佣金
     */
    public BigDecimal getNetCommission() {
        return netCommission;
    }

    /**
     * 设置普通净佣金
     *
     * @param netCommission 普通净佣金
     */
    public void setNetCommission(BigDecimal netCommission) {
        this.netCommission = netCommission;
    }

    /**
     * 获取客户经纪人id
     *
     * @return agent_id - 客户经纪人id
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * 设置客户经纪人id
     *
     * @param agentId 客户经纪人id
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    /**
     * 获取客户经纪人名称
     *
     * @return agent_name - 客户经纪人名称
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * 设置客户经纪人名称
     *
     * @param agentName 客户经纪人名称
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * 获取是否为有效户
     *
     * @return valid_flag - 是否为有效户
     */
    public String getValidFlag() {
        return validFlag;
    }

    /**
     * 设置是否为有效户
     *
     * @param validFlag 是否为有效户
     */
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * 获取成为有效户时间
     *
     * @return valid_flag_date - 成为有效户时间
     */
    public String getValidFlagDate() {
        return validFlagDate;
    }

    /**
     * 设置成为有效户时间
     *
     * @param validFlagDate 成为有效户时间
     */
    public void setValidFlagDate(String validFlagDate) {
        this.validFlagDate = validFlagDate;
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