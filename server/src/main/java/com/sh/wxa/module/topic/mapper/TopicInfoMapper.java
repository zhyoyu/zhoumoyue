package com.sh.wxa.module.topic.mapper;

import com.sh.wxa.module.topic.entity.TopicInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TopicInfoMapper {

    @Insert("INSERT INTO topic_info (TOPIC_ID,COMMENT_USERS,LIKE_USERS) VALUES (#{topicId},#{commentUsers},#{likeUsers})")
    void add(TopicInfo topicInfo);

    @Update("UPDATE topic_info SET COMMENT_USERS = #{commentUsers}, LIKE_USERS = #{likeUsers} WHERE TOPIC_ID = #{topicId}")
    void update(TopicInfo topicInfo);

    @Select("SELECT COUNT(*) FROM topic_info where TOPIC_ID = #{topicId}")
    Integer countById(long topicId);

    @Select("SELECT * FROM topic_info where TOPIC_ID = #{topicId}")
    @Results({
            @Result(column = "topic_id", property = "topicId"),
            @Result(column = "comment_users", property = "commentUsers"),
            @Result(column = "like_users", property = "likeUsers")})
    TopicInfo findById(long topicId);

    @Select("SELECT LIKE_USERS FROM topic_info where TOPIC_ID = #{topicId}")
    @Results({
            @Result(column = "like_users", property = "likeUsers")})
    String findLikeUsersById(long topicId);
}
