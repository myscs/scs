package com.company.project.api;

import com.company.project.core.BaseInDto;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryCustomerRelationsByAgentIdInDto extends BaseInDto {

    @Required
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    private Integer agentId;

}
