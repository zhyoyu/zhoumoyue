package com.sh.wxa.module.activity.mapper;

import com.sh.wxa.module.activity.entity.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ActivityMapper {

    @Insert("INSERT INTO activity (CREATE_USER_ID,CREATE_USER_NAME,CREATE_USER_ICON,TITLE,IMAGE_URL,ADDRESS,MEMO,ACTIVITY_TIME,NUM_LIMIT,JOIN_USERS,CREATE_TIME) VALUES (#{createUserId},#{createUserName},#{createUserIcon},#{title},#{imageUrl},#{address},#{memo},#{activityTime},#{numLimit},#{joinUsers},#{createTime})")
    void add(Activity activity);

    @Update("UPDATE activity SET CREATE_USER_ID = #{createUserId}, CREATE_USER_NAME = #{createUserName}, CREATE_USER_ICON = #{createUserIcon}, TITLE = #{title}, ADDRESS = #{address}, MEMO = #{memo}, ACTIVITY_TIME =#{activityTime}, NUM_LIMIT =#{numLimit}, JOIN_USERS = #{joinUsers} WHERE ID = #{id}")
    void update(Activity activity);

    @Delete("DELETE FROM activity WHERE ID = #{activityId}")
    void delete(Integer activityId);

    @Select("SELECT * FROM activity WHERE ID = #{activityId}")
    Activity findById(Integer activityId);
    /**
     *  获取分页活动
     * @param index 页数（1开始）
     * @param pageSize 分页大小
     */
    @SelectProvider(type = ActivitySqlProvider.class, method = "findActivityByCondition")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "create_user_id", property = "createUserId"),
            @Result(column = "create_user_name", property = "createUserName"),
            @Result(column = "create_user_icon", property = "createUserIcon"),
            @Result(column = "title", property = "title"),
            @Result(column = "address", property = "address"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "activity_time", property = "activityTime"),
            @Result(column = "num_limit", property = "numLimit"),
            @Result(column = "join_users", property = "joinUsers"),
            @Result(column = "create_time", property = "createTime") })
    List<Activity> findByCondition(
            @Param("index") Integer index,
            @Param("pageSize") Integer pageSize
    );

}
