package com.sh.wxa.module.topic.entity;

import lombok.Data;

@Data
public class TopicInfo {
    /**
     * 话题id
     */
    private long topicId;
    /**
     * 评论的用户 user1,user2,user3
     */
    private String commentUsers;
    /**
     * 喜欢的用户 user1,user2,user3
     */
    private String likeUsers;

}
