package com.company.project.service.custom.admin;

import com.company.project.api.QryAgentCommissionInDto;
import com.company.project.api.QryAgentCommissionOutDto;
import com.company.project.api.QryCustomerRelationsByAgentIdInDto;
import com.company.project.api.QryCustomerRelationsByAgentIdOutDto;
import com.company.project.api.admin.ModCustomerRelationsInDto;
import com.company.project.api.admin.QryCustomerRelationsListInDto;
import com.company.project.api.admin.QryCustomerRelationsListOutDto;
import com.company.project.core.Result;

/**
 * Created by CodeGenerator on 2020/03/28.
 */
public interface CustomerRelationsManageService {
    QryCustomerRelationsListOutDto qryCustomerRelationsList(QryCustomerRelationsListInDto inDto);
    Result modCustomerRelations(ModCustomerRelationsInDto inDto);
}
