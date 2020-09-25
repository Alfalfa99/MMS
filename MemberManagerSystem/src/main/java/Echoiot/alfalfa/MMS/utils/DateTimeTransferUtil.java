package Echoiot.alfalfa.MMS.utils;


import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 13:54
 * @Version 1.0
 * 获取当前时间戳 && 获取当前标准时间
 */
@Component
public class DateTimeTransferUtil {
    public static String getNowTimeStamp(){
        long time = System.currentTimeMillis();
        time/=1000;
        return String.valueOf(time);
    }

    public static String getFormatTime(){
        String format = "yyyy-MM-dd HH:mm:ss";
        String date = new SimpleDateFormat(format, Locale.CHINA).format(new Date());
        return date;
    }
}
