package com.sh.wxa.util;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class StringUtils {

    public static final String SPLIT_FLAG = ",";

    public static boolean isEmpty(String _string) {
        if (_string == null || _string.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static List<String> parseStringList(String str) {
        if(isEmpty(str)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(str.split(SPLIT_FLAG));
    }

    public static List<Long> parseLongList(String str) {
        List<Long> list = Lists.newArrayList();
        if(isEmpty(str)) {
            return list;
        }
        String[] strArray = str.split(SPLIT_FLAG);
        for(String s : strArray) {
            list.add(Long.parseLong(s));
        }
        return list;
    }

    public static String toStringForList(List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            String string = o.toString();
            if (!isEmpty(string)) {
                sb.append(string).append(SPLIT_FLAG);
            }
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - SPLIT_FLAG.length(), sb.length());
        }
        return sb.toString();
    }

}
