package com.company.project.service.impl;

import com.company.project.dao.TradingVolumeMapper;
import com.company.project.model.TradingVolume;
import com.company.project.service.TradingVolumeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/13.
 */
@Service
@Transactional
public class TradingVolumeServiceImpl extends AbstractService<TradingVolume> implements TradingVolumeService {
    @Resource
    private TradingVolumeMapper tradingVolumeMapper;

}
