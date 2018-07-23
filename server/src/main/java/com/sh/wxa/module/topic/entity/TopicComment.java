package com.sh.wxa.module.topic.entity;

import com.sh.wxa.module.topic.msg.po.CommentInfoPo;
import com.sh.wxa.module.topic.msg.po.TopicPo;
import com.sh.wxa.util.StringUtils;
import com.sh.wxa.util.TimeUtil;
import lombok.Data;

import java.util.Date;

@Data
public class TopicComment {
    /**
     * 自增id
     */
    private int id;
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
     * 回复的目标用户（作者为空字符串）
     */
    private String replyUserId;
    /**
     * 回复的目标用户名
     */
    private String replyUserName;
    /**
     * 回复时间
     */
    private Date replyTime;

    public CommentInfoPo toInfo() {
        CommentInfoPo info = new CommentInfoPo();
        info.setTopicId(this.id);
        info.setCommentUserId(this.commentUserId);
        info.setCommentUserName(this.commentUserName);
        info.setContent(this.content);
        info.setReplyUserId(this.replyUserId);
        info.setReplyUserName(this.replyUserName);
        return info;
    }
}
