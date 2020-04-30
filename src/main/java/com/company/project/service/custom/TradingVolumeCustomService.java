package com.company.project.service.custom;

import com.company.project.api.QryTradingVolumeByAgentIdInDto;
import com.company.project.api.QryTradingVolumeByAgentIdOutDto;

/**
 * Created by CodeGenerator on 2020/03/28.
 */
public interface TradingVolumeCustomService  {
    QryTradingVolumeByAgentIdOutDto qryTradingVolumeByAgentId(QryTradingVolumeByAgentIdInDto inDto );

}
