package com.sh.wxa.module.activity.mapper;

import com.sh.wxa.constants.RefreshDir;

import java.util.Map;

public class ActivitySqlProvider {

    public String findActivityByCondition(Map<String, Object> params) {
        return builderActivityPageSql("*", params);
    }

    private String builderActivityPageSql(String selectParam, Map<String, Object> paras) {
        Long activityId = (Long) paras.get("activityId");
        Integer dir = (Integer) paras.get("dir");
        StringBuilder sqlBuilder = new StringBuilder(" select ");
        sqlBuilder.append(selectParam)
                .append(" from activity ");

        if(activityId > 0) {
            sqlBuilder.append(" where ");
            if (dir == RefreshDir.UP.getIndex()) {
                sqlBuilder.append(" id > #{activityId} ");
                sqlBuilder.append(" ORDER BY id asc LIMIT #{pageSize} ");
            }
            if (dir == RefreshDir.DOWN.getIndex()) {
                sqlBuilder.append(" id < #{activityId} ");
                sqlBuilder.append(" ORDER BY id desc LIMIT #{pageSize} ");
            }
        } else {
            sqlBuilder.append(" ORDER BY id desc LIMIT #{pageSize} ");
        }
        return sqlBuilder.toString();
    }

}
