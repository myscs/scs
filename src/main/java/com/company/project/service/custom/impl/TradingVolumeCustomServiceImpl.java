package com.company.project.service.custom.impl;

import com.company.project.api.QryTradingVolumeByAgentIdInDto;
import com.company.project.api.QryTradingVolumeByAgentIdOutDto;
import com.company.project.core.AbstractService;
import com.company.project.model.TradingVolume;
import com.company.project.service.TradingVolumeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/28.
 */
@Service
@Transactional
public class TradingVolumeCustomServiceImpl extends AbstractService<TradingVolume> implements com.company.project.service.custom.TradingVolumeCustomService{
    @Resource
    private TradingVolumeService tradingVolumeService;


    @Override
    public QryTradingVolumeByAgentIdOutDto qryTradingVolumeByAgentId(QryTradingVolumeByAgentIdInDto inDto) {
        PageHelper.startPage(inDto.getPageNum(), inDto.getPageSize());
        Condition condition= new Condition(TradingVolume.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("id",inDto.getAgentId());
        List<TradingVolume> tradingVolumeList=tradingVolumeService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(tradingVolumeList);
        QryTradingVolumeByAgentIdOutDto outDto=new QryTradingVolumeByAgentIdOutDto();
        outDto.setSuccessResult(pageInfo);
        return outDto;
    }
}
