package com.sh.wxa.module.activity.mapper;

import com.sh.wxa.module.activity.entity.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ActivityMapper {

    @Insert("INSERT INTO activity (CREATE_USER,TITLE,ADDRESS,DESCRIBE,ACTIVITY_TIME,NUM_LIMIT,JOIN_USERS,CREATE_TIME) VALUES (#{createUser},#{title},#{address},#{describe},#{activityTime},#{numLimit},#{joinUsers},#{createTime})")
    void add(Activity activity);

    /**
     *  获取分页活动
     * @param index 页数（1开始）
     * @param pageSize 分页大小
     */
    @SelectProvider(type = ActivitySqlProvider.class, method = "findActivityByCondition")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "create_user", property = "createUser"),
            @Result(column = "title", property = "title"),
            @Result(column = "address", property = "address"),
            @Result(column = "describe", property = "describe"),
            @Result(column = "activity_time", property = "activityTime"),
            @Result(column = "num_limit", property = "numLimit"),
            @Result(column = "join_users", property = "joinUsers"),
            @Result(column = "date", property = "date") })
    List<Activity> findByCondition(
            @Param("index") Integer index,
            @Param("pageSize") Integer pageSize
    );

}
