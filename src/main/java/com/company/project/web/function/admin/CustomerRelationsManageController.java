package com.company.project.web.function.admin;

import com.company.project.api.admin.*;
import com.company.project.core.Result;
import com.company.project.core.user.CurrentUser;
import com.company.project.model.WxUser;
import com.company.project.service.custom.admin.CustomerRelationsManageService;
import com.company.project.service.custom.admin.TradingVolumeManageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/04/13.
*/
@RestController
@RequestMapping("")
public class CustomerRelationsManageController {
    @Resource
    private CustomerRelationsManageService customerRelationsManageService;

    @PostMapping("/qryCustomerRelationsList")
    public QryCustomerRelationsListOutDto qryCustomerRelationsList(@CurrentUser WxUser wxUser, @RequestBody QryCustomerRelationsListInDto inDto) {
       return customerRelationsManageService.qryCustomerRelationsList(inDto);
    }

    @PostMapping("/modCustomerRelations")
    public Result modCustomerRelations(@CurrentUser WxUser wxUser, @RequestBody ModCustomerRelationsInDto inDto) {
        return customerRelationsManageService.modCustomerRelations(inDto);
    }

}
