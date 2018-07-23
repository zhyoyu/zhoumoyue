package com.sh.wxa.module.activity.msg;

import com.sh.wxa.Message;
import com.sh.wxa.module.activity.msg.po.ActivityInfoPo;
import lombok.Data;

@Data
public class CreateActivityRequest implements Message {

    private ActivityInfoPo activityInfo;

}
