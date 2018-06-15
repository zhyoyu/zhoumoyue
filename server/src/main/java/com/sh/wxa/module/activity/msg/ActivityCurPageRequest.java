package com.sh.wxa.module.activity.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class ActivityCurPageRequest implements Message {

    private int curPage;

}
