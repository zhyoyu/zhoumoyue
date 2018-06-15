package com.sh.wxa.module.activity.msg;

import com.google.common.collect.Lists;
import com.sh.wxa.JsonMessage;
import com.sh.wxa.module.activity.msg.po.ActivityInfo;
import lombok.Data;

import java.util.List;

@Data
public class ActivityListResponse extends JsonMessage {

    private List<ActivityInfo> activityInfoList = Lists.newArrayList();

}
