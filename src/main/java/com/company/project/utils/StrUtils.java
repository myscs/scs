package com.company.project.utils;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by huangyelong on 2020/2/11.
 */
public class StrUtils {

    public static int strToInt(String s)  {
        return Integer.parseInt(s);
    }

    public static Date strToDate(String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static BigDecimal strToDecimal(String s)  {
        BigDecimal bd=new BigDecimal(s);

        return bd;
    }

}
