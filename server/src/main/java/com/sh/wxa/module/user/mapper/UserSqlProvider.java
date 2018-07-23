package com.sh.wxa.module.user.mapper;

import com.sh.wxa.util.StringUtils;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

public class UserSqlProvider {

    public String findUsersByCondition(Map<String, Object> params) {
        return builderUserSql("*", params);
    }

    public String builderUserSql(String select, Map<String, Object> paras) {
        String userIds = (String) paras.get("userIds");
        BEGIN();
        SELECT(select);
        FROM("user");
        WHERE("open_id in (" + userIds + ")");
        return SQL();
    }

}
