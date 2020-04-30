package com.company.project.scheduled;


import com.company.project.model.CustomerRelations;
import com.company.project.model.TradingVolume;
import com.company.project.model.WxUser;
import com.company.project.service.CustomerRelationsService;
import com.company.project.service.TradingVolumeService;
import com.company.project.service.WxUserService;
import com.company.project.utils.DtUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

;

/**
 * Created by huangyelong on 2020/3/30.
 */
@Component
public class ProcessCustomerRelations {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public final static long ONE_Minute =  60 * 1000;
    public final static long half_of_hour=  1800 * 1000;


    @Resource
    private WxUserService wxUserService;


    @Resource
    private CustomerRelationsService customerRelationsService;


    @Resource
    private TradingVolumeService tradingVolumeService;

    @Scheduled(fixedDelay=half_of_hour)
    public void processCustomerRelations() throws Exception{
        logger.info(DtUtils.getDateYYMMDDHHMMSSS()+" >>>>>>>>>>>>processCustomerRelations执行开始....");

        List<TradingVolume> tradingVolumeList=getTradingVolumeOfWait();
        if(CollectionUtils.isEmpty(tradingVolumeList)){
            logger.warn("no data");
            return;
        }
        for (TradingVolume tradingVolume:tradingVolumeList) {
           CustomerRelations customerRelations= customerRelationsService.findBy("ciNo",tradingVolume.getCiNo());
           if(customerRelations==null){
               logger.warn(tradingVolume.getCiNo()+" is not exist");
               if(StringUtils.isNotBlank(tradingVolume.getAgentName())){
                   WxUser wxUser=wxUserService.findBy("realName",tradingVolume.getAgentName());
                   if(wxUser !=null){
                       customerRelations=new CustomerRelations();
                       customerRelations.setCiNo(tradingVolume.getCiNo());
                       customerRelations.setAgentId(wxUser.getId());
                       customerRelations.setCiName(tradingVolume.getCiName());
                       customerRelations.setOpenDate(tradingVolume.getOpenDate());
                       customerRelations.setTotalCommission(new BigDecimal(0.00));
                       customerRelations.setMonthCommission(new BigDecimal(0.00));
                       customerRelations.setValidFlag("N");
                       customerRelationsService.save(customerRelations);
                   }else{
                       continue;
                   }
               }else{
                   continue;
               }

           }

           WxUser wxUser=wxUserService.findById(customerRelations.getAgentId());
           if(wxUser == null){
               logger.error("没有客户经理信息！");
               continue;
           }
           updateCustomerRelations(tradingVolume,customerRelations,wxUser);

           tradingVolume.setStatus("N");
           tradingVolume.setTs(new Date());
           tradingVolumeService.update(tradingVolume);

        }

        logger.info(DtUtils.getDateYYMMDDHHMMSSS()+" >>>>>>>>>>>>processCustomerRelations执行结束....");
    }

    private void updateCustomerRelations(TradingVolume tradingVolume, CustomerRelations customerRelations, WxUser wxUser) {
        CustomerRelations customerRelationsUpdate=new CustomerRelations();
        BeanUtils.copyProperties(customerRelations,customerRelationsUpdate);
        BigDecimal monthCommission=tradingVolume.getNetCommission().multiply(wxUser.getCommissionRate());
        BigDecimal totalCommission=customerRelations.getTotalCommission().add(monthCommission);
        customerRelationsUpdate.setTotalCommission(totalCommission);
        customerRelationsUpdate.setTs(new Date());
        if(StringUtils.compare(customerRelations.getStatisticalDate(),tradingVolume.getStatisticalDate())<=0){
            customerRelationsUpdate.setStatisticalDate(tradingVolume.getStatisticalDate());
            customerRelationsUpdate.setMonthCommission(monthCommission);
        }
        customerRelationsService.update(customerRelationsUpdate);
    }


    private List<TradingVolume> getTradingVolumeOfWait() {
        Condition condition= new Condition(TradingVolume.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("status","W");
        List<TradingVolume> tradingVolumeList= tradingVolumeService.findByCondition(condition);
        return tradingVolumeList;
    }


//    @Scheduled(cron="0 15 3 * * ?")
//    public void cronJob(){
//        System.out.println(DtUtils.getDateYYMMDDHHMMSSS()+" >>cron执行....");
//    }
}
