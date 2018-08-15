package com.sh.wxa.module.activity.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class ActivityIdRequest implements Message {

    private Long activityId;

}
