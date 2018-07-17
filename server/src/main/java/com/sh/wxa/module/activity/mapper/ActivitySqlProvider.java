package com.sh.wxa.module.activity.mapper;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

public class ActivitySqlProvider {

    public String findActivityByCondition(Map<String, Object> params) {
        return builderActivityPageSql("*", params) + " ORDER BY id desc LIMIT #{index} , #{pageSize}";
    }

    public String builderActivityPageSql(String select, Map<String, Object> paras) {
        BEGIN();
        SELECT(select);
        FROM("activity");
        return SQL();
    }

}
