package com.sh.wxa.module.activity.msg;

import com.sh.wxa.JsonMessage;
import com.sh.wxa.module.activity.msg.po.ActivitySpecificPo;
import lombok.Data;

@Data
public class ActivityInfoResponse extends JsonMessage {

    private ActivitySpecificPo info;

}
