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
