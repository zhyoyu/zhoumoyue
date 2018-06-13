package com.sh.wxa.module.user.mapper;

import com.sh.wxa.module.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 添加User
     */
    @Insert("INSERT INTO user (OPEN_ID,USER_NAME,SEX,CITY,ACTIVE_VALUE) VALUES (#{openId},#{userName},#{sex},#{city},#{activeValue})")
    void addUser(User user);
    /**
     * 查询User
     */
    @Select("SELECT * FROM user where open_id = #{openId}")
    @Results({
            @Result(column = "open_id", property = "openId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "city", property = "city"),
            @Result(column = "active_value", property = "activeValue") })
    User findUserById(String openId);
    /**
     * 查询User
     */
    @Select("SELECT COUNT(*) FROM user where open_id = #{openId}")
    Integer countUserById(String openId);
}
