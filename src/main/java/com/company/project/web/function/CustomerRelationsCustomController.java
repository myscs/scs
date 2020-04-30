package com.company.project.web.function;

import com.company.project.api.QryAgentCommissionInDto;
import com.company.project.api.QryCustomerRelationsByAgentIdInDto;
import com.company.project.api.QryCustomerRelationsByAgentIdOutDto;
import com.company.project.core.Result;
import com.company.project.core.user.CurrentUser;
import com.company.project.model.WxUser;
import com.company.project.service.custom.CustomerRelationsCustomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/03/29.
*/
@RestController
@RequestMapping("")
public class CustomerRelationsCustomController {
    @Resource
    private CustomerRelationsCustomService customerRelationsCustomServiceService;


    @PostMapping("/qryCustomerRelationsByAgentId")
    public Result qryCustomerRelationsByAgentId(@CurrentUser WxUser wxUser, @RequestBody QryCustomerRelationsByAgentIdInDto inDto) {
        inDto.setAgentId(wxUser.getId());
        QryCustomerRelationsByAgentIdOutDto qryCustomerRelationsByAgentIdOutDto= customerRelationsCustomServiceService.qryCustomerRelationsByAgentId(inDto);
        return qryCustomerRelationsByAgentIdOutDto;
    }

    @PostMapping("/qryAgentCommission")
    public Result qryAgentCommission(@CurrentUser WxUser wxUser, @RequestBody QryAgentCommissionInDto inDto) {
        inDto.setAgentId(wxUser.getId());
        return customerRelationsCustomServiceService.qryAgentCommission(inDto);
    }



}
