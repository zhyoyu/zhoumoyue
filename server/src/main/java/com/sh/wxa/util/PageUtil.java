package com.sh.wxa.util;

public class PageUtil {

    public static final int DEFAULT_PAGE_SIZE = 10;

    public static int getIndex(int curPage, int pageSize) {
        curPage = Math.max(1, curPage);
        return (curPage - 1) * pageSize;
    }

}
