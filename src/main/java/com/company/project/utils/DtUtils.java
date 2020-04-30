package com.company.project.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by huangyelong on 2020/2/11.
 */
public class DtUtils {

    public static String dateToStr(Date d,String format)  {
         SimpleDateFormat formatter = new SimpleDateFormat(format);
          String dateString = formatter.format(d);
           return dateString;
    }

    public static String getDateYYMMDDHHMMSSS()  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmSSS");
        String dateString = formatter.format(new Date());
        return dateString;
    }
    public static String getDateYYMMDD()  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    public static void main(String[] args) {
        System.out.println(getDateYYMMDD());
    }

}
