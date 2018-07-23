package com.sh.wxa.module.activity.msg;

import com.sh.wxa.JsonMessage;
import com.sh.wxa.module.activity.msg.po.ActivitySpecificInfoPo;
import lombok.Data;

@Data
public class ActivityInfoResponse extends JsonMessage {

    private ActivitySpecificInfoPo activityInfo;

}
