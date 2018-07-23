package com.sh.wxa.util;

public class EnumUtils {
    public static <T extends IndexEnum> T getByIndex(int index, T[] values) {
        for(T e : values) {
            if(e.getIndex() == index) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("类型 %s 找不到序号为 %d 的元素", values[0].getClass().getName(), index));
    }
}
