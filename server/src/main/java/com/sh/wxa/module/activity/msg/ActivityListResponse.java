package com.sh.wxa.module.activity.msg;

import com.google.common.collect.Lists;
import com.sh.wxa.JsonMessage;
import com.sh.wxa.module.activity.msg.po.ActivityInfoPo;
import lombok.Data;

import java.util.List;

@Data
public class ActivityListResponse extends JsonMessage {

    private List<ActivityInfoPo> activityInfoList = Lists.newArrayList();

}
