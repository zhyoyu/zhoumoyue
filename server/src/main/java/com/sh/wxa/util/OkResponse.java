package com.sh.wxa.util;

import com.sh.wxa.JsonMessage;

public class OkResponse extends JsonMessage {

    public final static OkResponse OK_RESPONSE = new OkResponse();
    /** 只有成功时返回，失败时会发错误信息 */
    private int ok = 1;

}
