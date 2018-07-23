package com.sh.wxa.module;

import com.sh.wxa.module.topic.msg.OnOffLikeRequest;
import com.sh.wxa.module.topic.msg.TopicListRequest;
import com.sh.wxa.module.topic.msg.TopicListResponse;
import com.sh.wxa.module.topic.msg.commentTopicRequest;
import com.sh.wxa.module.topic.msg.createTopicRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

public interface TopicModule {

    OkResponse createTopic(createTopicRequest request, Session session);

    OkResponse commentTopic(commentTopicRequest request, Session session);

    TopicListResponse findTopicList(TopicListRequest request, Session session);

    OkResponse onOffLikeTopic(OnOffLikeRequest request, Session session);
}
