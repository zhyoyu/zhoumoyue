package com.sh.wxa.module.activity.mapper;

import com.sh.wxa.module.activity.entity.Activity;
import org.apache.ibatis.annotations.Insert;

public interface ActivityMapper {

    @Insert("INSERT INTO activity (CREATE_USER,TITLE,ADDRESS,DESCRIBE,ACTIVITY_TIME,NUM_LIMIT,JOIN_USERS,CREATE_TIME) VALUES (#{createUser},#{title},#{address},#{describe},#{activityTime},#{numLimit},#{joinUsers},#{createTime})")
    void addActivity(Activity activity);

}
