package com.sh.wxa.module.topic.msg.po;

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
     * 是否已点赞 0 false 1 true
     */
    private int like;
}
