package com.sh.wxa.module.topic.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class createTopicRequest implements Message {

    public String content;

    public String images;
}
