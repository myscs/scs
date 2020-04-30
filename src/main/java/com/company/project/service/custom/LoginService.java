package com.company.project.service.custom;


import com.company.project.api.LoginByPhoneInDto;
import com.company.project.api.LoginInDto;
import com.company.project.api.LoginOutDto;

public interface LoginService {
    LoginOutDto login(LoginInDto inDto);
    LoginOutDto loginByPhone(LoginByPhoneInDto inDto);


}
