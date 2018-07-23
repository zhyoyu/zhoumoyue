package com.sh.wxa.module.topic.mapper;

import com.sh.wxa.module.topic.entity.Topic;
import com.sh.wxa.module.topic.entity.TopicComment;
import com.sh.wxa.module.topic.entity.TopicInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface TopicCommentMapper {

    @Insert("INSERT INTO topic_comment (TOPIC_ID,COMMENT_USER_ID,COMMENT_USER_NAME,CONTENT,REPLY_USER_ID,REPLY_USER_NAME,REPLY_TIME) VALUES (#{topicId},#{commentUserId},#{commentUserName},#{content},#{replyUserId},#{replyUserName},#{replyTime})")
    void add(TopicComment topicComment);

    /**
     *  获取分页活动
     * @param index 页数（1开始）
     * @param pageSize 分页大小
     */
//    @SelectProvider(type = TopicCommentSqlProvider.class, method = "findTopicCommentByCondition")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "topic_id", property = "topicId"),
//            @Result(column = "comment_user_id", property = "commentUserId"),
//            @Result(column = "comment_user_name", property = "commentUserName"),
//            @Result(column = "content", property = "content"),
//            @Result(column = "reply_user_id", property = "replyUserId"),
//            @Result(column = "reply_user_name", property = "replyUserName"),
//            @Result(column = "reply_time", property = "replyTime"),
//     })
//    List<Topic> findByCondition(
//            @Param("index") Long index,
//            @Param("pageSize") Integer pageSize
//    );

    /**
     * 根据话题id查找评论
     */
    @Select("SELECT * FROM topic_comment where TOPIC_ID = #{topicId}")
    @Results({
            @Result(column = "topic_id", property = "topicId"),
            @Result(column = "comment_user_id", property = "commentUserId"),
            @Result(column = "comment_user_name", property = "commentUserName"),
            @Result(column = "content", property = "content"),
            @Result(column = "reply_user_id", property = "replyUserId"),
            @Result(column = "reply_user_name", property = "replyUserName"),
            @Result(column = "reply_time", property = "replyTime")
    })
    List<TopicComment> findByTopicId(Long topicId);
}
