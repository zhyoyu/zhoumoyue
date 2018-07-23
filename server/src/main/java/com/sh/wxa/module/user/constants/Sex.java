package com.sh.wxa.module.user.constants;

import com.sh.wxa.util.EnumUtils;
import com.sh.wxa.util.IndexEnum;

public enum Sex implements IndexEnum {

    MALE(1),
    FEMALE(2),
    ;
    private int sex;

    Sex(int sex) {
        this.sex = sex;
    }

    public static Sex valueOf(int sex) {
        return EnumUtils.getByIndex(sex, Sex.values());
    }

    @Override
    public int getIndex() {
        return this.sex;
    }
}
