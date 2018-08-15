package com.sh.wxa.module.topic.mapper;

import com.sh.wxa.constants.RefreshDir;

import java.util.Map;

public class TopicSqlProvider {

    public String findTopicByCondition(Map<String, Object> params) {
        return builderTopicPageSql("*", params);
    }

    private String builderTopicPageSql(String selectParam, Map<String, Object> paras) {
        Long topicId = (Long) paras.get("topicId");
        Integer dir = (Integer) paras.get("dir");
        StringBuilder sqlBuilder = new StringBuilder(" select ");
        sqlBuilder.append(selectParam)
                .append(" from topic ");

        if(topicId > 0) {
            sqlBuilder.append(" where ");
            if (dir == RefreshDir.UP.getIndex()) {
                sqlBuilder.append(" id > #{topicId} ");
                sqlBuilder.append(" ORDER BY id asc LIMIT #{pageSize} ");
            }
            if (dir == RefreshDir.DOWN.getIndex()) {
                sqlBuilder.append(" id < #{topicId} ");
                sqlBuilder.append(" ORDER BY id desc LIMIT #{pageSize} ");
            }
        } else {
            sqlBuilder.append(" ORDER BY id desc LIMIT #{pageSize} ");
        }
        return sqlBuilder.toString();
    }

}
