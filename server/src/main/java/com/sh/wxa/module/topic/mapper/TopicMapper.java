package com.sh.wxa.module.topic.mapper;

import com.sh.wxa.module.topic.entity.Topic;
import com.sh.wxa.module.topic.entity.TopicComment;
import com.sh.wxa.module.topic.entity.TopicInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface TopicMapper {

    @Insert("INSERT INTO topic (AUTHOR_UID,AUTHOR_NICK_NAME,AUTHOR_ICON_URL,CONTENT,IMAGES,CREATE_TIME) VALUES (#{authorUid},#{authorNickName},#{authorIconUrl},#{content},#{images},#{createTime})")
    void add(Topic topic);

    /**
     *  获取分页活动
     * @param index 页数（1开始）
     * @param pageSize 分页大小
     */
    @SelectProvider(type = TopicSqlProvider.class, method = "findTopicByCondition")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "author_uid", property = "authorUid"),
            @Result(column = "author_nick_name", property = "authorNickName"),
            @Result(column = "author_icon_url", property = "authorIconUrl"),
            @Result(column = "content", property = "content"),
            @Result(column = "images", property = "images"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "id", property = "topicInfo", one = @One(select = "com.sh.wxa.module.topic.mapper.TopicInfoMapper.findById", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "commentList", many = @Many(select = "com.sh.wxa.module.topic.mapper.TopicCommentMapper.findByTopicId", fetchType = FetchType.LAZY))
    })
    List<Topic> findByCondition(
            @Param("index") Long index,
            @Param("pageSize") Integer pageSize
    );

}
