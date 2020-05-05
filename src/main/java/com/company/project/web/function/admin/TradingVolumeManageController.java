package com.company.project.web.function.admin;
import com.company.project.api.admin.AddCustomerRelationsInDto;
import com.company.project.api.admin.QryTradingVolumeListInDto;
import com.company.project.api.admin.QryUserInfoListInDto;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;
import com.company.project.core.user.CurrentUser;
import com.company.project.model.TradingVolume;
import com.company.project.model.WxUser;
import com.company.project.scheduled.ExportTradingVolume;
import com.company.project.scheduled.ProcessCustomerRelations;
import com.company.project.service.TradingVolumeService;
import com.company.project.service.custom.admin.TradingVolumeManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
* Created by CodeGenerator on 2020/04/13.
*/
@RestController
@RequestMapping("")
public class TradingVolumeManageController {

    @Value("${excel.path.source}")
    private String excelPathSource;

    @Resource
    private TradingVolumeManageService tradingVolumeManageService;

    @Resource
   private ProcessCustomerRelations processCustomerRelations;

    @Resource
    private ExportTradingVolume exportTradingVolume;

    @PostMapping("/qryTradingVolumeList")
    public Result qryTradingVolumeList(@CurrentUser WxUser wxUser, @RequestBody QryTradingVolumeListInDto inDto) {
       return tradingVolumeManageService.qryTradingVolumeList(inDto);
    }

    @PostMapping("/addCustomerRelations")
    public Result addCustomerRelations(@CurrentUser WxUser wxUser, @RequestBody AddCustomerRelationsInDto inDto) {
        return tradingVolumeManageService.addCustomerRelations(inDto);
    }

    @PostMapping("/uploadExcel")
    public Result uploadExcel(@RequestParam(value = "file") MultipartFile file) throws Exception{


        String fileName = file.getOriginalFilename();
        File dest = new File(new File(excelPathSource).getAbsolutePath()+ "/" + fileName);
        if(dest.exists()){
            throw new ServiceException("已存在相同文件");
        }

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
        } catch (Exception e) {
           throw new ServiceException("文件处理失败");
        }
        exportTradingVolume.exportTradingVolume();
        processCustomerRelations.processCustomerRelations();
        return new Result();
    }

}
