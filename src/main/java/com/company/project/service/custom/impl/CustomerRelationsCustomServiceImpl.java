package com.company.project.service.custom.impl;

import com.company.project.api.QryAgentCommissionInDto;
import com.company.project.api.QryAgentCommissionOutDto;
import com.company.project.api.QryCustomerRelationsByAgentIdInDto;
import com.company.project.api.QryCustomerRelationsByAgentIdOutDto;
import com.company.project.core.AbstractService;
import com.company.project.model.CustomerRelations;
import com.company.project.service.CustomerRelationsService;
import com.company.project.utils.DtUtils;
import com.company.project.utils.IntUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/28.
 */
@Service
@Transactional
public class CustomerRelationsCustomServiceImpl extends AbstractService<CustomerRelations> implements com.company.project.service.custom.CustomerRelationsCustomService{
    @Resource
    private CustomerRelationsService customerRelationsService;


    @Override
    public QryCustomerRelationsByAgentIdOutDto qryCustomerRelationsByAgentId(QryCustomerRelationsByAgentIdInDto inDto) {
        PageHelper.startPage(inDto.getPageNum(), inDto.getPageSize());
        Condition condition= new Condition(CustomerRelations.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("agentId",inDto.getAgentId());
        condition.setOrderByClause("total_commission desc ,ts  desc");
        List<CustomerRelations> customerRelationsList= customerRelationsService.findByCondition(condition);
        Page page = (Page)customerRelationsList;

        List<QryCustomerRelationsByAgentIdOutDto.QryCustomerRelationsByAgentIdDetail> qryCustomerRelationsByAgentIdDetails=dbOut(customerRelationsList);

        PageInfo pageInfo = new PageInfo(qryCustomerRelationsByAgentIdDetails);

        pageInfo.setPageNum(inDto.getPageNum());
        pageInfo.setPageSize(inDto.getPageSize());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());

        QryCustomerRelationsByAgentIdOutDto outDto=new QryCustomerRelationsByAgentIdOutDto();
        outDto.setSuccessResult(pageInfo);
        return outDto;
    }

    @Override
    public QryAgentCommissionOutDto qryAgentCommission(QryAgentCommissionInDto inDto) {
        Condition condition= new Condition(CustomerRelations.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("agentId",inDto.getAgentId());
        List<CustomerRelations> customerRelationsList= customerRelationsService.findByCondition(condition);
        BigDecimal allTotalCommission=new BigDecimal(0.00);
        BigDecimal allMonthCommission=new BigDecimal(0.00);
        String allMonthDate="";
        for (CustomerRelations customerRelations: customerRelationsList) {
            allTotalCommission=allTotalCommission.add(customerRelations.getTotalCommission());
            allMonthCommission=allMonthCommission.add(customerRelations.getMonthCommission());
            allMonthDate=customerRelations.getStatisticalDate();
        }
        QryAgentCommissionOutDto outDto=new QryAgentCommissionOutDto();
        outDto.setAllTotalCommission(IntUtils.decimalToStr(allTotalCommission));
        outDto.setAllMonthCommission(IntUtils.decimalToStr(allMonthCommission));
        outDto.setAllMonthDate(allMonthDate);
        outDto.setTotalCustomer(IntUtils.intToStr(customerRelationsList.size()));
        return outDto;
    }

    private List<QryCustomerRelationsByAgentIdOutDto.QryCustomerRelationsByAgentIdDetail> dbOut(List<CustomerRelations> customerRelationsList) {
        List<QryCustomerRelationsByAgentIdOutDto.QryCustomerRelationsByAgentIdDetail> qryCustomerRelationsByAgentIdDetailList= new Page<>();
        QryCustomerRelationsByAgentIdOutDto qryCustomerRelationsByAgentIdOutDto=new QryCustomerRelationsByAgentIdOutDto();
        for (CustomerRelations customerRelations:customerRelationsList) {
            QryCustomerRelationsByAgentIdOutDto.QryCustomerRelationsByAgentIdDetail qryCustomerRelationsByAgentIdDetail=qryCustomerRelationsByAgentIdOutDto.new QryCustomerRelationsByAgentIdDetail();
            qryCustomerRelationsByAgentIdDetail.setCiName(customerRelations.getCiName());
            qryCustomerRelationsByAgentIdDetail.setTotalCommission(new DecimalFormat(",##0.00").format(customerRelations.getTotalCommission()));
            qryCustomerRelationsByAgentIdDetail.setMonthCommission(new DecimalFormat(",##0.00").format(customerRelations.getMonthCommission()));
            setValidData(qryCustomerRelationsByAgentIdDetail,customerRelations);
            qryCustomerRelationsByAgentIdDetail.setOpenDate(customerRelations.getOpenDate());
            qryCustomerRelationsByAgentIdDetailList.add(qryCustomerRelationsByAgentIdDetail);
        }
        return qryCustomerRelationsByAgentIdDetailList;
    }

    private void setValidData(QryCustomerRelationsByAgentIdOutDto.QryCustomerRelationsByAgentIdDetail qryCustomerRelationsByAgentIdDetail, CustomerRelations customerRelations) {

        if(StringUtils.equals(customerRelations.getValidFlag(),"Y")){
            qryCustomerRelationsByAgentIdDetail.setValidFlag("是");
            qryCustomerRelationsByAgentIdDetail.setValidFlagDate(DtUtils.dateToStr(customerRelations.getValidFlagDate(),"yyyy-MM-dd"));
        }else{
            qryCustomerRelationsByAgentIdDetail.setValidFlag("否");
            qryCustomerRelationsByAgentIdDetail.setValidFlagDate("");

        }
    }
}
