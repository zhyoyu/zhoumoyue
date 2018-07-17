package com.sh.wxa.module.topic.mapper;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;

public class TopicSqlProvider {

    public String findTopicByCondition(Map<String, Object> params) {
        return builderTopicPageSql("*", params) + " ORDER BY id desc LIMIT #{index} , #{pageSize}";
    }

    public String builderTopicPageSql(String select, Map<String, Object> paras) {
        BEGIN();
        SELECT(select);
        FROM("topic");
        return SQL();
    }

}
