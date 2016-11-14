package com.microwei.easyweibo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Administrator on 2016-05-11 .
 */
public class FormatDateUtil {
    public static String formatDate(String date){
        if(date==null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss ZZZZ yyyy",Locale.ENGLISH);
        try {
            Date mDate = sdf.parse(date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "MM-dd HH:mm",new Locale("ENGLISH", "CHINA"));
            return simpleDateFormat.format(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
