package com.sh.wxa.module.topic.msg;

import com.google.common.collect.Lists;
import com.sh.wxa.JsonMessage;
import com.sh.wxa.module.topic.msg.po.TopicPo;
import lombok.Data;

import java.util.List;

@Data
public class TopicListResponse extends JsonMessage {

    private List<TopicPo> topicInfosList = Lists.newArrayList();

}
