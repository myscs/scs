package com.company.project.api.admin;

import com.company.project.core.BaseInDto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by huangyelong on 2020/3/24.
 */
public class DelUserInfoInDto extends BaseInDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
