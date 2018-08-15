package com.sh.wxa.util;

import com.google.common.collect.Lists;
import com.sh.wxa.constants.Symbol;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class StringUtils {
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
        return Lists.newArrayList(str.split(Symbol.DOU_HAO));
    }

    public static List<Integer> parseIntegerList(String str) {
        List<Integer> list = Lists.newArrayList();
        if(isEmpty(str)) {
            return list;
        }
        String[] strArray = str.split(Symbol.DOU_HAO);
        for(String s : strArray) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    public static List<Long> parseLongList(String str) {
        List<Long> list = Lists.newArrayList();
        if(isEmpty(str)) {
            return list;
        }
        String[] strArray = str.split(Symbol.DOU_HAO);
        for(String s : strArray) {
            list.add(Long.parseLong(s));
        }
        return list;
    }

    public static String toStringForList(List<?> list) {
        return toStringForList(list, Symbol.DOU_HAO);
    }

    public static String toStringForList(List<?> list, String splitFlag) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            String string = o.toString();
            if (!isEmpty(string)) {
                sb.append(string).append(splitFlag);
            }
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - splitFlag.length(), sb.length());
        }
        return sb.toString();
    }

    public static String toQueryStringForList(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("'");
        for (Object o : list) {
            String string = o.toString();
            if (!isEmpty(string)) {
                sb.append(string).append("'").append(Symbol.DOU_HAO);
            }
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - Symbol.DOU_HAO.length(), sb.length());
        }
        return sb.toString();
    }

}
