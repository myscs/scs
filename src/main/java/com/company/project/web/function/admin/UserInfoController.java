package com.company.project.web.function.admin;

import com.company.project.api.admin.AddUserInfoInDto;
import com.company.project.api.admin.DelUserInfoInDto;
import com.company.project.api.admin.ModUserInfoInDto;
import com.company.project.api.admin.QryUserInfoListInDto;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.user.CurrentUser;
import com.company.project.model.WxUser;
import com.company.project.service.custom.admin.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/03/29.
*/
@RestController
@RequestMapping("userinfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;



    @PostMapping("/qryUserInfo")
    public Result qryUserInfoList(@CurrentUser WxUser wxUser, @RequestBody QryUserInfoListInDto inDto) {
        return userInfoService.qryUserInfoList(inDto);
    }

    @PostMapping("/modUserInfo")
    public Result modUserInfo(@CurrentUser WxUser wxUser, @RequestBody ModUserInfoInDto inDto) {
        return ResultGenerator.genSuccessResult(userInfoService.modUserInfo(inDto));
    }

    @PostMapping("/addUserInfo")
    public Result addUserInfo(@CurrentUser WxUser wxUser, @RequestBody AddUserInfoInDto inDto) {
        return ResultGenerator.genSuccessResult(userInfoService.addUserInfo(inDto));
    }

    @PostMapping("/delUserInfo")
    public Result delUserInfo(@CurrentUser WxUser wxUser, @RequestBody DelUserInfoInDto inDto) {
        return ResultGenerator.genSuccessResult(userInfoService.delUserInfo(inDto));
    }




}
