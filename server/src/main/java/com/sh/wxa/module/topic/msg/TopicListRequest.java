package com.sh.wxa.module.topic.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class TopicListRequest implements Message {

    private long beginIndex;

}
