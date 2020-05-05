package com.company.project.service.custom.impl.admin;

import com.company.project.api.admin.*;
import com.company.project.api.commom.BaseErrorMsg;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;
import com.company.project.model.CustomerRelations;
import com.company.project.model.TradingVolume;
import com.company.project.model.WxUser;
import com.company.project.service.CustomerRelationsService;
import com.company.project.service.TradingVolumeService;
import com.company.project.service.WxUserService;
import com.company.project.service.custom.admin.CustomerRelationsManageService;
import com.company.project.service.custom.admin.TradingVolumeManageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/22.
 */
@Service
@Transactional
public class CustomerRelationsManageServiceImpl implements CustomerRelationsManageService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private TradingVolumeService tradingVolumeService;

    @Resource
    private CustomerRelationsService customerRelationsService;

    @Resource
    private WxUserService wxUserService;


    @Override
    public QryCustomerRelationsListOutDto qryCustomerRelationsList(QryCustomerRelationsListInDto inDto) {
        Condition condition= new Condition(CustomerRelations.class);
        Example.Criteria criteria = condition.createCriteria();
        if(StringUtils.isNotBlank(inDto.getCiName())){
            criteria.andEqualTo("ciName",inDto.getCiName());
        }
        if(StringUtils.isNotBlank(inDto.getAgentName())){
            Integer agentId=qryWxUserByAgentName(inDto.getAgentName()).getId();
            criteria.andEqualTo("agentId",agentId);
        }
        PageHelper.startPage(inDto.getPageNum(), inDto.getPageSize());
        List<CustomerRelations> customerRelationsList=customerRelationsService.findByCondition(condition);
        Page page = (Page)customerRelationsList;


        List<QryCustomerRelationsListOutDto.QryCustomerRelationsListDetail> qryCustomerRelationsByAgentIdDetails=dbOut(customerRelationsList);

        PageInfo pageInfo = new PageInfo(qryCustomerRelationsByAgentIdDetails);

        pageInfo.setPageNum(inDto.getPageNum());
        pageInfo.setPageSize(inDto.getPageSize());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPages(page.getPages());

        QryCustomerRelationsListOutDto outDto=new QryCustomerRelationsListOutDto();
        outDto.setSuccessResult(pageInfo);
        return outDto;

    }

    @Override
    public Result modCustomerRelations(ModCustomerRelationsInDto inDto) {
        WxUser wxUser=qryWxUserByAgentName(inDto.getAgentName());
        CustomerRelations customerRelations=new CustomerRelations();
        customerRelations.setCiNo(inDto.getCiNo());
        customerRelations.setAgentId(wxUser.getId());
        customerRelations.setTs(new Date());
        customerRelationsService.update(customerRelations);
        return new Result();
    }

    private List<QryCustomerRelationsListOutDto.QryCustomerRelationsListDetail> dbOut(List<CustomerRelations> customerRelationsList) {
        Map<Integer,WxUser> wxUserMap=new HashMap<>();

        List<QryCustomerRelationsListOutDto.QryCustomerRelationsListDetail> qryCustomerRelationsByAgentIdDetailList= new Page<>();
        QryCustomerRelationsListOutDto qryCustomerRelationsByAgentIdOutDto=new QryCustomerRelationsListOutDto();
        for (CustomerRelations customerRelations:customerRelationsList) {
            QryCustomerRelationsListOutDto.QryCustomerRelationsListDetail qryCustomerRelationsListDetail=qryCustomerRelationsByAgentIdOutDto.new QryCustomerRelationsListDetail();
            qryCustomerRelationsListDetail.setCiNo(customerRelations.getCiNo());
            qryCustomerRelationsListDetail.setCiName(customerRelations.getCiName());
            qryCustomerRelationsListDetail.setTotalCommission(customerRelations.getTotalCommission());
            qryCustomerRelationsListDetail.setMonthCommission(customerRelations.getMonthCommission());
            qryCustomerRelationsListDetail.setOpenDate(customerRelations.getOpenDate());
            qryCustomerRelationsListDetail.setAgentName(qryWxUserById(customerRelations.getAgentId(),wxUserMap).getRealName());
            qryCustomerRelationsListDetail.setStatisticalDate(customerRelations.getStatisticalDate());
            qryCustomerRelationsListDetail.setValidFlag(customerRelations.getValidFlag());
            qryCustomerRelationsListDetail.setValidFlagDate(customerRelations.getValidFlagDate());
            qryCustomerRelationsByAgentIdDetailList.add(qryCustomerRelationsListDetail);
        }
        return qryCustomerRelationsByAgentIdDetailList;
    }

    private WxUser qryWxUserById(Integer agentId, Map<Integer, WxUser> wxUserMap) {
        //使用缓存，不用每次都去查
        if(wxUserMap.get(agentId) !=null){
            return wxUserMap.get(agentId);
        }

        WxUser wxUser=wxUserService.findById(agentId);
        if(wxUser==null){
            throw new ServiceException(BaseErrorMsg.Db.NON_EXIST+"经纪人信息");
        }
        wxUserMap.put(agentId,wxUser);
        return  wxUser;
    }

    private WxUser qryWxUserByAgentName(String agentName) {
        WxUser wxUser=wxUserService.findBy("realName",agentName);
        if(wxUser==null){
            throw new ServiceException(BaseErrorMsg.Db.NON_EXIST+"经纪人信息");
        }
        return  wxUser;

    }

}
