package com.sh.wxa.module.topic.msg;

import com.sh.wxa.Message;
import lombok.Data;

@Data
public class OnOffLikeRequest implements Message {

    private long topicId;

    private int onOffLike;

}
