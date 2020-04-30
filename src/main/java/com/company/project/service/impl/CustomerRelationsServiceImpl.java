package com.company.project.service.impl;

import com.company.project.dao.CustomerRelationsMapper;
import com.company.project.model.CustomerRelations;
import com.company.project.service.CustomerRelationsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/14.
 */
@Service
@Transactional
public class CustomerRelationsServiceImpl extends AbstractService<CustomerRelations> implements CustomerRelationsService {
    @Resource
    private CustomerRelationsMapper customerRelationsMapper;

}
