package com.sh.wxa.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

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

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(getYMDTimeString(date));
    }


}
