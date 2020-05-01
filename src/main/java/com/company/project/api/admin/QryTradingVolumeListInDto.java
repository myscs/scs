package com.company.project.api.admin;

import com.company.project.core.BaseInDto;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class QryTradingVolumeListInDto extends BaseInDto {


    private String  ciName;

    private String status;

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
