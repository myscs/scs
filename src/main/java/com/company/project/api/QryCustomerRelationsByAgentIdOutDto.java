package com.company.project.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.company.project.core.Result;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryCustomerRelationsByAgentIdOutDto extends Result {

    public class QryCustomerRelationsByAgentIdDetail{
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
        @JSONField(format = "yyyy-MM-dd")
        @Column(name = "open_date")
        private Date openDate;

        /**
         * 总累计净佣金
         */

        private String totalCommission;

        /**
         * 本月佣金
         */
        private String monthCommission;

        /**
         * 是否为有效户
         */
        @Column(name = "valid_flag")
        private String validFlag;

        /**
         * 成为有效户时间
         */
        private String validFlagDate;

        public String getCiNo() {
            return ciNo;
        }

        public void setCiNo(String ciNo) {
            this.ciNo = ciNo;
        }

        public Integer getAgentId() {
            return agentId;
        }

        public void setAgentId(Integer agentId) {
            this.agentId = agentId;
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

        public String getTotalCommission() {
            return totalCommission;
        }

        public void setTotalCommission(String totalCommission) {
            this.totalCommission = totalCommission;
        }

        public String getMonthCommission() {
            return monthCommission;
        }

        public void setMonthCommission(String monthCommission) {
            this.monthCommission = monthCommission;
        }

        public String getValidFlag() {
            return validFlag;
        }

        public void setValidFlag(String validFlag) {
            this.validFlag = validFlag;
        }

        public String getValidFlagDate() {
            return validFlagDate;
        }

        public void setValidFlagDate(String validFlagDate) {
            this.validFlagDate = validFlagDate;
        }

        @Override
        public String toString() {
            return "QryCustomerRelationsByAgentIdDetail{" +
                    "ciNo='" + ciNo + '\'' +
                    ", agentId=" + agentId +
                    ", ciName='" + ciName + '\'' +
                    ", openDate=" + openDate +
                    ", totalCommission='" + totalCommission + '\'' +
                    ", monthCommission='" + monthCommission + '\'' +
                    ", validFlag='" + validFlag + '\'' +
                    ", validFlagDate='" + validFlagDate + '\'' +
                    '}';
        }
    }
}
