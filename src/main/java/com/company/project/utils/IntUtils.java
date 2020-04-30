package com.company.project.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;

/*
 * Created by huangyelong on 2020/2/11.
 */
public class IntUtils {

    public static String decimalToStr(BigDecimal decimal){
        return new DecimalFormat(",##0.00").format(decimal);
    }

    public static String intToStr(int i){
        return Integer.valueOf(i).toString();
    }


}
