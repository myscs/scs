package com.company.project.web.function;

import com.company.project.api.LoginByPhoneInDto;
import com.company.project.api.LoginInDto;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.service.custom.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2020/03/22.
*/
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;



    @PostMapping("/login")
    public Result login( @RequestBody LoginInDto inDto) {

        return ResultGenerator.genSuccessResult(loginService.login(inDto));
    }

    @PostMapping("/loginByPhone")
    public Result loginByPhone(@RequestBody LoginByPhoneInDto inDto) {
        return ResultGenerator.genSuccessResult(loginService.loginByPhone(inDto));
    }

}
