package com.company.project.api.admin;

import com.company.project.core.BaseInDto;
import com.company.project.core.Result;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryTradingVolumeListOutDto extends Result {
    public class QryTradingVolumeListDetail{

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
    }
}
