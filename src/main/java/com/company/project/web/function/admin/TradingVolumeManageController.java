package com.company.project.web.function.admin;
import com.company.project.api.admin.QryTradingVolumeListInDto;
import com.company.project.api.admin.QryUserInfoListInDto;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.user.CurrentUser;
import com.company.project.model.TradingVolume;
import com.company.project.model.WxUser;
import com.company.project.service.TradingVolumeService;
import com.company.project.service.custom.admin.TradingVolumeManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/04/13.
*/
@RestController
@RequestMapping("")
public class TradingVolumeManageController {
    @Resource
    private TradingVolumeManageService tradingVolumeManageService;

    @PostMapping("/qryTradingVolumeList")
    public Result qryTradingVolumeList(@CurrentUser WxUser wxUser, @RequestBody QryTradingVolumeListInDto inDto) {
       return tradingVolumeManageService.qryTradingVolumeList(inDto);
    }
}
