package com.company.project.api.admin;

import com.company.project.core.BaseInDto;
import com.company.project.core.Result;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryCustomerRelationsListOutDto extends Result {

    public class QryCustomerRelationsListDetail {
        /**
         * 客户号
         */
        @Id
        @Column(name = "ci_no")
        private String ciNo;

        @Column(name = "agent_name")
        private String agentName;

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


        public String getCiNo() {
            return ciNo;
        }

        public void setCiNo(String ciNo) {
            this.ciNo = ciNo;
        }

        public String getAgentName() {
            return agentName;
        }

        public void setAgentName(String agentName) {
            this.agentName = agentName;
        }

        public String getCiName() {
            return ciName;
        }

        public void setCiName(String ciName) {
            this.ciName = ciName;
        }

        public Date getOpenDate() {
            return openDate;
        }

        public void setOpenDate(Date openDate) {
            this.openDate = openDate;
        }

        public BigDecimal getTotalCommission() {
            return totalCommission;
        }

        public void setTotalCommission(BigDecimal totalCommission) {
            this.totalCommission = totalCommission;
        }

        public BigDecimal getMonthCommission() {
            return monthCommission;
        }

        public void setMonthCommission(BigDecimal monthCommission) {
            this.monthCommission = monthCommission;
        }

        public String getStatisticalDate() {
            return statisticalDate;
        }

        public void setStatisticalDate(String statisticalDate) {
            this.statisticalDate = statisticalDate;
        }

        public String getValidFlag() {
            return validFlag;
        }

        public void setValidFlag(String validFlag) {
            this.validFlag = validFlag;
        }

        public Date getValidFlagDate() {
            return validFlagDate;
        }

        public void setValidFlagDate(Date validFlagDate) {
            this.validFlagDate = validFlagDate;
        }
    }

}
