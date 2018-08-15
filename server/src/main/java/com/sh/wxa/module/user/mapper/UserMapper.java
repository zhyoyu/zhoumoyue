package com.sh.wxa.module.user.mapper;

import com.sh.wxa.module.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    /**
     * 添加User
     */
    @Insert("INSERT INTO user (OPEN_ID,NICK_NAME,ICON_URL,SEX,CITY,ACTIVE_VALUE,REGISTER_TIME,LAST_LOGIN_TIME) VALUES (#{openId},#{nickName},#{iconUrl},#{sex},#{city},#{activeValue},#{registerTime},#{lastLoginTime})")
    void add(User user);
    /**
     * 更新User
     */
    @Update("UPDATE user SET NICK_NAME = #{nickName}, ICON_URL = #{iconUrl}, SEX = #{sex}, CITY = #{city}, ACTIVE_VALUE = #{activeValue}, LAST_LOGIN_TIME = #{lastLoginTime} WHERE OPEN_ID = #{openId}")
    void update(User user);
    /**
     * 查询User
     */
    @Select("SELECT * FROM user where open_id = #{openId}")
    @Results({
            @Result(column = "open_id", property = "openId"),
            @Result(column = "nick_name", property = "nickName"),
            @Result(column = "icon_url", property = "iconUrl"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "city", property = "city"),
            @Result(column = "active_value", property = "activeValue"),
            @Result(column = "register_time", property = "registerTime"),
            @Result(column = "last_login_time", property = "lastLoginTime")})
    User findById(String openId);

    @SelectProvider(type = UserSqlProvider.class, method = "findUsersByCondition")
    @Results({
            @Result(column = "open_id", property = "openId"),
            @Result(column = "nick_name", property = "nickName"),
            @Result(column = "icon_url", property = "iconUrl"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "city", property = "city"),
            @Result(column = "active_value", property = "activeValue"),
            @Result(column = "register_time", property = "registerTime"),
            @Result(column = "last_login_time", property = "lastLoginTime")})
    List<User> findUserByIds(
            @Param("userIds") String userIds);
}
