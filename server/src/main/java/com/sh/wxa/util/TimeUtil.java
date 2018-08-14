package com.sh.wxa.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeUtil {

    public static final String DEFUAL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final long MILLI_SECOND = TimeUnit.MILLISECONDS.toMillis(1L);

    public static final long SECOND = TimeUnit.SECONDS.toMillis(1L);

    public static final long MINUTE = TimeUnit.MINUTES.toMillis(1L);

    public static final long HOUR = TimeUnit.HOURS.toMillis(1L);

    public static final long DAY = TimeUnit.DAYS.toMillis(1L);

    public static int toSeconds(Date date) {
        return (int) (date.getTime() / SECOND);
    }

    public static Date secondsToDate(int seconds) {
        return new Date(seconds * SECOND);
    }

    public static String getYMDTimeString_(Date date) {
        SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd");
        return _format.format(date);
    }

    public static String getYMDTimeString(Date date) {
        SimpleDateFormat _format = new SimpleDateFormat("yyyyMMdd");
        return _format.format(date);
    }

    public static String getYMDHMSTimeString(Date date) {
        SimpleDateFormat _format = new SimpleDateFormat(DEFUAL_FORMAT);
        return _format.format(date);
    }

    /**
     * 时间字符串转换成日期
     */
    public static Date formatStr(String dateStr) {
        DateFormat fmt = new SimpleDateFormat(DEFUAL_FORMAT);
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
           log.error("time string format error timeStr:{}", dateStr);
        }
        return date;
    }

    public static void main(String[] args) {
        Date date = formatStr("2016-05-06 12:10:00");
        System.out.println(date);
    }


}
