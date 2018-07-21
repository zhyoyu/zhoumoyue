package com.sh.wxa.module.topic.entity;

import com.sh.wxa.module.topic.msg.po.TopicPo;
import com.sh.wxa.util.StringUtils;
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
     * 图片
     */
    private String images;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 话题额外信息（评论、点赞等）
     */
    private TopicInfo topicInfo;

    public TopicPo toInfo() {
        TopicPo info = new TopicPo();
        info.setTopicId(this.id);
        info.setAuthorUid(this.authorUid);
        info.setAuthorNickName(this.getAuthorNickName());
        info.setAuthorIconUrl(this.authorIconUrl);
        info.setContent(this.content);
        info.setImages(StringUtils.parseStringList(this.images));
        info.setCreateTime(TimeUtil.toSeconds(this.createTime));
        return info;
    }

}
