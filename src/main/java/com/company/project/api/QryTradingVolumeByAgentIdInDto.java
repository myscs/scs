package com.company.project.api;

import com.company.project.core.BaseInDto;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryTradingVolumeByAgentIdInDto extends BaseInDto {

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    private String agentId;

}
