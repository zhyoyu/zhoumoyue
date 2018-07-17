package com.sh.wxa.module.topic;

import com.sh.wxa.module.topic.msg.TopicListRequest;
import com.sh.wxa.module.topic.msg.TopicListResponse;
import com.sh.wxa.module.topic.msg.createTopicRequest;
import com.sh.wxa.onlinemanager.Session;

public interface TopicService {

    void commentTopic(Session session, createTopicRequest request);

    TopicListResponse findTopicList(Session session, TopicListRequest request);

    void onOffLikeTopic(Session session, long topicId, int onOffLike);
}
