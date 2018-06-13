package com.sh.wxa.module.activity.message;

import com.sh.wxa.Message;
import com.sh.wxa.module.activity.message.pojo.ActivityInfo;
import lombok.Data;

@Data
public class CreateActivityRequest implements Message {

    private ActivityInfo activityInfo;

}
