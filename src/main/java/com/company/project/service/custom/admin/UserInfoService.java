package com.company.project.service.custom.admin;


import com.company.project.api.admin.*;
import com.company.project.core.Result;

public interface UserInfoService {


    Result qryUserInfoList(QryUserInfoListInDto inDto);

    ModUserInfoOutDto modUserInfo(ModUserInfoInDto inDto);

    AddUserInfoOutDto addUserInfo(AddUserInfoInDto inDto);

    Result delUserInfo(DelUserInfoInDto id);
}
