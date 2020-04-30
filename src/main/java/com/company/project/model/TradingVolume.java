package com.company.project.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "trading_volume")
public class TradingVolume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户号
     */
    @Column(name = "ci_no")
    private String ciNo;

    /**
     * 统计日期
     */
    @Column(name = "statistical_date")
    private String statisticalDate;

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
     * 交易量
     */
    @Column(name = "trading_volume")
    private BigDecimal tradingVolume;

    /**
     * 资产总额
     */
    @Column(name = "total_assets")
    private BigDecimal totalAssets;

    /**
     * 股票市值
     */
    @Column(name = "stock_value")
    private BigDecimal stockValue;

    /**
     * 资金余额
     */
    @Column(name = "fund_balance")
    private BigDecimal fundBalance;

    /**
     * 净佣金
     */
    @Column(name = "net_commission")
    private BigDecimal netCommission;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 客户经理名称
     */
    @Column(name = "agent_name")
    private String agentName;

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
     * 获取交易量
     *
     * @return trading_volume - 交易量
     */
    public BigDecimal getTradingVolume() {
        return tradingVolume;
    }

    /**
     * 设置交易量
     *
     * @param tradingVolume 交易量
     */
    public void setTradingVolume(BigDecimal tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    /**
     * 获取资产总额
     *
     * @return total_assets - 资产总额
     */
    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    /**
     * 设置资产总额
     *
     * @param totalAssets 资产总额
     */
    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    /**
     * 获取股票市值
     *
     * @return stock_value - 股票市值
     */
    public BigDecimal getStockValue() {
        return stockValue;
    }

    /**
     * 设置股票市值
     *
     * @param stockValue 股票市值
     */
    public void setStockValue(BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    /**
     * 获取资金余额
     *
     * @return fund_balance - 资金余额
     */
    public BigDecimal getFundBalance() {
        return fundBalance;
    }

    /**
     * 设置资金余额
     *
     * @param fundBalance 资金余额
     */
    public void setFundBalance(BigDecimal fundBalance) {
        this.fundBalance = fundBalance;
    }

    /**
     * 获取净佣金
     *
     * @return net_commission - 净佣金
     */
    public BigDecimal getNetCommission() {
        return netCommission;
    }

    /**
     * 设置净佣金
     *
     * @param netCommission 净佣金
     */
    public void setNetCommission(BigDecimal netCommission) {
        this.netCommission = netCommission;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取客户经理名称
     *
     * @return agent_name - 客户经理名称
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * 设置客户经理名称
     *
     * @param agentName 客户经理名称
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
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