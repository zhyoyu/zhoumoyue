package com.sh.wxa.module.topic.entity;

import com.sh.wxa.module.topic.msg.po.TopicPo;
import com.sh.wxa.util.TimeUtil;
import lombok.Data;

import java.util.Date;

@Data
public class Topic {
    /**
     * 话题id
     */
    private long id;
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
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 话题信息
     */
    private TopicInfo topicInfo;

    public TopicPo toInfo() {
        TopicPo info = new TopicPo();
        info.setTopicId(this.id);
        info.setAuthorUid(this.authorUid);
        info.setAuthorNickName(this.getAuthorNickName());
        info.setAuthorIconUrl(this.authorIconUrl);
        info.setContent(this.content);
        info.setCreateTime(TimeUtil.toSeconds(this.createTime));
        return info;
    }

}
