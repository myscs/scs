package com.company.project.service.custom.impl.admin;

import com.company.project.api.admin.*;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;
import com.company.project.model.WxUser;
import com.company.project.service.WxUserService;
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
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/22.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private WxUserService wxUserService;


    @Override
    public Result qryUserInfoList(QryUserInfoListInDto inDto) {
        PageHelper.startPage(inDto.getPageNum(), inDto.getPageSize());
        Condition condition= new Condition(WxUser.class);
        Example.Criteria criteria = condition.createCriteria();
        if(StringUtils.isNotBlank(inDto.getRealName())){
            criteria.andEqualTo("realName",inDto.getRealName());
        }
        if(StringUtils.isNotBlank(inDto.getTelNum())){
            criteria.andEqualTo("telNum",inDto.getTelNum());
        }
        List<WxUser> wxUserList=wxUserService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(wxUserList);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public ModUserInfoOutDto modUserInfo(ModUserInfoInDto inDto) {

        WxUser wxUser=new WxUser();
        BeanUtils.copyProperties(inDto,wxUser);
        wxUserService.update(wxUser);
        ModUserInfoOutDto outDto =new ModUserInfoOutDto();
        return outDto;
    }

    @Override
    public AddUserInfoOutDto addUserInfo(AddUserInfoInDto inDto) {
        if(StringUtils.isBlank(inDto.getRealName())){
            throw new ServiceException("名字必须输入");
        }
        if(StringUtils.isBlank(inDto.getRealName())){
            throw new ServiceException("电话号码必须输入");
        }

        if(StringUtils.isBlank(inDto.getPassWord())){
            throw new ServiceException("密码必须输入");
        }
        WxUser wxUserExist=wxUserService.findBy("telNum",inDto.getTelNum());
        if(wxUserExist !=null){
            throw new ServiceException("已经存在相同号码的用户信息，请删除后再添加！");

        }

        WxUser wxUser=new WxUser();
        BeanUtils.copyProperties(inDto,wxUser);
        wxUser.setOpenId(inDto.getTelNum());
        wxUser.setNickName(inDto.getRealName());
        if(StringUtils.isBlank(inDto.getAvatarUrl())){
            wxUser.setAvatarUrl("defalut");
        }
        wxUserService.save(wxUser);
        AddUserInfoOutDto outDto=new AddUserInfoOutDto();
        return outDto;
    }

    @Override
    public Result delUserInfo(DelUserInfoInDto inDto) {
        wxUserService.deleteById(inDto.getId());
        return ResultGenerator.genSuccessResult();
    }


}
