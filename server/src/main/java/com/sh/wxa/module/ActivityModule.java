package com.sh.wxa.module;

import com.sh.wxa.module.activity.msg.ActivityIdRequest;
import com.sh.wxa.module.activity.msg.ActivityInfoResponse;
import com.sh.wxa.module.activity.msg.ActivityListRequest;
import com.sh.wxa.module.activity.msg.ActivityListResponse;
import com.sh.wxa.module.activity.msg.CreateActivityRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

public interface ActivityModule {

    /** 获取活动列表 */
    ActivityListResponse findActivityList(ActivityListRequest request, Session session);

    /** 创建新活动 */
    OkResponse createActivity(CreateActivityRequest request, Session session);

    /** 删除活动 */
    OkResponse deleteActivity(ActivityIdRequest request, Session session);

    /** 加入活动 */
    OkResponse joinActivity(ActivityIdRequest request, Session session);

    /** 取消加入活动 */
    OkResponse cancelActivity(ActivityIdRequest request, Session session);

    /** 活动具体信息 */
    ActivityInfoResponse getActivityInfo(ActivityIdRequest request, Session session);

}
