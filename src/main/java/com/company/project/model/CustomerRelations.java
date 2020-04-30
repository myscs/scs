package com.company.project.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_relations")
public class CustomerRelations {
    /**
     * 客户号
     */
    @Id
    @Column(name = "ci_no")
    private String ciNo;

    @Column(name = "agent_id")
    private Integer agentId;

    /**
     * 客户名称
     */
    @Column(name = "ci_name")
    private String ciName;

    /**
     * 开户日期
     */
    @Column(name = "open_date")
    private Date openDate;

    /**
     * 总累计净佣金
     */
    @Column(name = "total_commission")
    private BigDecimal totalCommission;

    /**
     * 本月佣金
     */
    @Column(name = "month_commission")
    private BigDecimal monthCommission;

    /**
     * 统计日期
     */
    @Column(name = "statistical_date")
    private String statisticalDate;

    /**
     * 是否为有效户
     */
    @Column(name = "valid_flag")
    private String validFlag;

    /**
     * 成为有效户时间
     */
    @Column(name = "valid_flag_date")
    private Date validFlagDate;

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
     * @return agent_id
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * @param agentId
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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
     * 获取总累计净佣金
     *
     * @return total_commission - 总累计净佣金
     */
    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    /**
     * 设置总累计净佣金
     *
     * @param totalCommission 总累计净佣金
     */
    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
    }

    /**
     * 获取本月佣金
     *
     * @return month_commission - 本月佣金
     */
    public BigDecimal getMonthCommission() {
        return monthCommission;
    }

    /**
     * 设置本月佣金
     *
     * @param monthCommission 本月佣金
     */
    public void setMonthCommission(BigDecimal monthCommission) {
        this.monthCommission = monthCommission;
    }

    /**
     * 获取统计日期
     *
     * @return statistical_date - 统计日期
     */
    public String getStatisticalDate() {
        return statisticalDate;
    }

    /**
     * 设置统计日期
     *
     * @param statisticalDate 统计日期
     */
    public void setStatisticalDate(String statisticalDate) {
        this.statisticalDate = statisticalDate;
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
    public Date getValidFlagDate() {
        return validFlagDate;
    }

    /**
     * 设置成为有效户时间
     *
     * @param validFlagDate 成为有效户时间
     */
    public void setValidFlagDate(Date validFlagDate) {
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