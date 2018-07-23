package com.sh.wxa.module.topic.msg.po;

import com.google.common.collect.Lists;
import com.sh.wxa.Message;
import lombok.Data;

import java.util.List;

@Data
public class TopicPo implements Message {
    /**
     * 话题id
     */
    private long topicId;
    /**
     * 作者唯一id
     */
    private String authorUid;
    /**
     * 作者昵称
     */
    private String authorNickName;
    /**
     * 作者头像url
     */
    private String authorIconUrl;
    /**
     * 话题内容
     */
    private String content;
    /**
     * 话题图片
     */
    private List<String> images;
    /**
     * 创建时间
     */
    private int createTime;
    /**
     * 喜欢的用户量
     */
    private int likeCount;
    /**
     * 评论数
     */
    private int commentCount;
    /**
     * 是否已点赞 0 false 1 true
     */
    private int like;
    /**
     * 评论列表
     */
    private List<CommentInfoPo> commentList = Lists.newArrayList();
}
