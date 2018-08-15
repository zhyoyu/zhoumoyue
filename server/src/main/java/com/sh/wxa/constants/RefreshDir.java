package com.sh.wxa.constants;

import com.sh.wxa.util.EnumUtils;
import com.sh.wxa.util.IndexEnum;

public enum RefreshDir implements IndexEnum {

    UP(1),
    DOWN(2),;

    private int dir;

    RefreshDir(int dir) {
        this.dir = dir;
    }

    @Override
    public int getIndex() {
        return this.dir;
    }

    public static RefreshDir valueOf(int dir) {
        return EnumUtils.getByIndex(dir, RefreshDir.values());
    }
}
