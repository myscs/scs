package com.company.project.api;

import com.company.project.core.Result;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryAgentCommissionOutDto extends Result {

        /**
         * 全部创收
         */
        private String allTotalCommission;

        /**
         * 本月佣金
         */
        private String allMonthCommission;

    /**
     *  本月佣金的月份
     */

    private String totalCustomer;


    private String allMonthDate;

    public String getAllTotalCommission() {
        return allTotalCommission;
    }

    public void setAllTotalCommission(String allTotalCommission) {
        this.allTotalCommission = allTotalCommission;
    }

    public String getAllMonthCommission() {
        return allMonthCommission;
    }

    public void setAllMonthCommission(String allMonthCommission) {
        this.allMonthCommission = allMonthCommission;
    }

    public String getAllMonthDate() {
        return allMonthDate;
    }

    public void setAllMonthDate(String allMonthDate) {
        this.allMonthDate = allMonthDate;
    }

    public String getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(String totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
}
