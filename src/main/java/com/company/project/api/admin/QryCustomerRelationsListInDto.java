package com.company.project.api.admin;

import com.company.project.core.BaseInDto;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryCustomerRelationsListInDto extends BaseInDto {


    private String  agentName;

    private String ciName;

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
}
