package com.sh.wxa.module.user.mapper;

import com.sh.wxa.module.user.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {
    /**
     * 添加User
     */
    @Insert("INSERT INTO user_info (OPEN_ID,ACTIVITY_DYNAMIC,MY_ACTIVITY,TOPIC_DYNAMIC,MY_TOPIC,CONCERN_USERS,FANS) VALUES (#{openId},#{activityDynamic},#{myActivity},#{topicDynamic},#{myTopic},#{concern_users},#{fans})")
    void add(UserInfo userInfo);
    /**
     * 更新User
     */
    @Update("UPDATE user_info SET ACTIVITY_DYNAMIC = #{activityDynamic}, MY_ACTIVITY = #{myActivity}, TOPIC_DYNAMIC = #{topicDynamic}, MY_TOPIC = #{myTopic}, CONCERN_USERS = #{concernUsers}, FANS = #{fans} WHERE OPEN_ID = #{openId}")
    void update(UserInfo userInfo);
    /**
     * 查询User
     */
    @Select("SELECT * FROM user_info where open_id = #{openId}")
    @Results({
            @Result(column = "open_id", property = "openId"),
            @Result(column = "activity_dynamic", property = "activityDynamic"),
            @Result(column = "my_activity", property = "myActivity"),
            @Result(column = "topic_dynamic", property = "topicDynamic"),
            @Result(column = "my_topic", property = "myTopic"),
            @Result(column = "concern_users", property = "concernUsers"),
            @Result(column = "fans", property = "fans")})
    UserInfo findById(String openId);
}
