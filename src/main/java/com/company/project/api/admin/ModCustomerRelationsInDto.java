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
public class ModCustomerRelationsInDto extends BaseInDto {



    private String ciNo;

    private String agentName;

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
}
