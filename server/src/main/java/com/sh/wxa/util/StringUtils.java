package com.sh.wxa.util;

public class StringUtils {

    public static boolean isEmpty(String _string) {
        if (_string == null || _string.trim().length() == 0) {
            return true;
        }
        return false;
    }

}
