package com.company.project.service.custom.admin;


import com.company.project.api.admin.*;
import com.company.project.core.Result;

public interface TradingVolumeManageService {

    Result qryTradingVolumeList(QryTradingVolumeListInDto inDto);
}
