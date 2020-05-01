package com.company.project.service.custom.impl.admin;

import com.company.project.api.admin.*;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;
import com.company.project.model.CustomerRelations;
import com.company.project.model.TradingVolume;
import com.company.project.model.WxUser;
import com.company.project.service.CustomerRelationsService;
import com.company.project.service.TradingVolumeService;
import com.company.project.service.WxUserService;
import com.company.project.service.custom.admin.TradingVolumeManageService;
import com.company.project.service.custom.admin.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/22.
 */
@Service
@Transactional
public class TradingVolumeManageServiceImpl implements TradingVolumeManageService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private TradingVolumeService tradingVolumeService;

    @Resource
    private CustomerRelationsService customerRelationsService;

    @Resource
    private WxUserService wxUserService;


    @Override
    public Result qryTradingVolumeList(QryTradingVolumeListInDto inDto) {
        PageHelper.startPage(inDto.getPageNum(), inDto.getPageSize());
        Condition condition= new Condition(TradingVolume.class);
        Example.Criteria criteria = condition.createCriteria();
        if(StringUtils.isNotBlank(inDto.getCiName())){
            criteria.andEqualTo("ciName",inDto.getCiName());
        }
        if(StringUtils.isNotBlank(inDto.getStatus())){
            criteria.andEqualTo("status",inDto.getStatus());
        }
        List<TradingVolume> tradingVolumeList=tradingVolumeService.findByCondition(condition);
        setAgentName(tradingVolumeList);

        PageInfo pageInfo = new PageInfo(tradingVolumeList);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result addCustomerRelations(AddCustomerRelationsInDto inDto) {

        checkAddCustomerRelationsInput(inDto);
        CustomerRelations customerRelations=new CustomerRelations();
        WxUser wxUser=wxUserService.findBy("realName",inDto.getAgentName());
        if(wxUser !=null){
            customerRelations=new CustomerRelations();
            customerRelations.setCiNo(inDto.getCiNo());
            customerRelations.setAgentId(wxUser.getId());
            customerRelations.setCiName(inDto.getCiName());
            customerRelations.setOpenDate(inDto.getOpenDate());

            BigDecimal monthCommission=inDto.getNetCommission().multiply(wxUser.getCommissionRate());
            BigDecimal totalCommission=monthCommission;
            customerRelations.setTotalCommission(monthCommission);
            customerRelations.setMonthCommission(totalCommission);
            customerRelations.setValidFlag("N");
            customerRelations.setStatisticalDate(inDto.getStatisticalDate());
            customerRelationsService.save(customerRelations);

            TradingVolume tradingVolume=new TradingVolume();
            tradingVolume.setId(inDto.getId());
            tradingVolume.setStatus("N");
            tradingVolume.setTs(new Date());
            tradingVolumeService.update(tradingVolume);
        }else{
            throw new ServiceException("经纪人不存在，请先新增");
        }
        return new Result();
    }

    private void checkAddCustomerRelationsInput(AddCustomerRelationsInDto inDto) {
        TradingVolume tradingVolume =tradingVolumeService.findById(inDto.getId());
        if(tradingVolume == null){
            throw new ServiceException("记录不存在");
        }
        if(!StringUtils.equals(tradingVolume.getStatus() ,"W")){
            throw new ServiceException("记录状态不为W");
        }

        CustomerRelations customerRelations= customerRelationsService.findBy("ciNo",inDto.getCiNo());
        if(customerRelations  != null){
            throw new ServiceException("客户已被归属");
        }
        if(StringUtils.isBlank(inDto.getAgentName())){
            throw new ServiceException("客户经纪人必须输入");
        }

    }

    private void setAgentName(List<TradingVolume> tradingVolumeList) {
        Map<Integer,WxUser> wxUserMap=new HashMap<>();
        for (TradingVolume tradingVolume:tradingVolumeList){
            if(StringUtils.isBlank(tradingVolume.getAgentName())){
               CustomerRelations customerRelations= customerRelationsService.findBy("ciNo",tradingVolume.getCiNo());
               if(customerRelations != null){
                   WxUser wxUser=wxUserMap.get(customerRelations.getAgentId());
                   if(wxUser !=null){
                       tradingVolume.setAgentName(wxUser.getRealName());
                   }else{
                       wxUser=  wxUserService.findById(customerRelations.getAgentId());
                       if(wxUser !=null){
                           wxUserMap.put(wxUser.getId(),wxUser);
                           tradingVolume.setAgentName(wxUser.getRealName());
                       }
                   }

               }
            }
        }
    }


}
