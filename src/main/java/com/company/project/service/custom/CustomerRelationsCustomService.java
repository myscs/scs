package com.company.project.service.custom;

import com.company.project.api.QryAgentCommissionInDto;
import com.company.project.api.QryAgentCommissionOutDto;
import com.company.project.api.QryCustomerRelationsByAgentIdInDto;
import com.company.project.api.QryCustomerRelationsByAgentIdOutDto;

/**
 * Created by CodeGenerator on 2020/03/28.
 */
public interface CustomerRelationsCustomService {
    QryCustomerRelationsByAgentIdOutDto qryCustomerRelationsByAgentId(QryCustomerRelationsByAgentIdInDto inDto);
    QryAgentCommissionOutDto qryAgentCommission(QryAgentCommissionInDto inDto);
}
