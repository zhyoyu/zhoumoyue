package com.sh.wxa.constants;

import com.sh.wxa.util.EnumUtils;
import com.sh.wxa.util.IndexEnum;

public enum UpLoadType implements IndexEnum {
    TOPIC(1, "topic"),
    ACTIVITY(2, "activity"),
    ;

    private int type;

    private String fileName;

    UpLoadType(int type, String fileName) {
        this.type = type;
        this.fileName = fileName;
    }

    public static UpLoadType valueOf(int type) {
        return EnumUtils.getByIndex(type, UpLoadType.values());
    }

    @Override
    public int getIndex() {
        return this.type;
    }

    public String getFileName() {
        return fileName;
    }
}
