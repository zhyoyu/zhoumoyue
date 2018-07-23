package com.sh.wxa.module.topic.msg;

import com.sh.wxa.Message;
import com.sh.wxa.module.topic.msg.po.CommentInfoPo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class commentTopicRequest implements Message {
    /**
     * 评论话题
     */
    private CommentInfoPo commentInfo;
}
