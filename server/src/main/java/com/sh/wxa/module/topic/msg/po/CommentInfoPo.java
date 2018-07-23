package com.sh.wxa.module.topic.msg.po;

import com.sh.wxa.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentInfoPo implements Message {
    /**
     * 话题id
     */
    private long topicId;
    /**
     * 评论用户id
     */
    private String commentUserId;
    /**
     * 评论用户昵称
     */
    private String commentUserName;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复的目标用户(目标为作者的话为空字符串)
     */
    private String replyUserId;
    /**
     * 回复的目标用户名
     */
    private String replyUserName;
}
