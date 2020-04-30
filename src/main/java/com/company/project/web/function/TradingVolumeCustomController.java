package com.company.project.web.function;

import com.company.project.api.QryTradingVolumeByAgentIdInDto;
import com.company.project.api.QryTradingVolumeByAgentIdOutDto;
import com.company.project.core.Result;
import com.company.project.core.user.CurrentUser;
import com.company.project.model.WxUser;
import com.company.project.service.custom.TradingVolumeCustomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/03/28.
*/
@RestController
@RequestMapping("")
public class TradingVolumeCustomController {
    @Resource
    private TradingVolumeCustomService tradingVolumeCustomServiceService;


    @PostMapping("/qryTradingVolumeByAgentId")
    public Result qryTradingVolumeByAgentId(@CurrentUser WxUser wxUser,@RequestBody QryTradingVolumeByAgentIdInDto inDto) {
        QryTradingVolumeByAgentIdOutDto qryTradingVolumeByAgentIdOutDto= tradingVolumeCustomServiceService.qryTradingVolumeByAgentId(inDto);
        return qryTradingVolumeByAgentIdOutDto;
    }
}
