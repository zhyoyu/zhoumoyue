package com.sh.wxa.module.topic;

import com.sh.wxa.Services;
import com.sh.wxa.annotation.Module;
import com.sh.wxa.module.TopicModule;
import com.sh.wxa.module.topic.msg.OnOffLikeRequest;
import com.sh.wxa.module.topic.msg.TopicListRequest;
import com.sh.wxa.module.topic.msg.TopicListResponse;
import com.sh.wxa.module.topic.msg.createTopicRequest;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.OkResponse;

@Module
public class TopicModuleImpl implements TopicModule {

    @Override
    public OkResponse commentTopic(createTopicRequest request, Session session) {
        Services.getTopicService().commentTopic(session, request);
        return OkResponse.OK_RESPONSE;
    }

    @Override
    public TopicListResponse findTopicList(TopicListRequest request, Session session) {
        return Services.getTopicService().findTopicList(session, request);
    }

    @Override
    public OkResponse onOffLikeTopic(OnOffLikeRequest request, Session session) {
        Services.getTopicService().onOffLikeTopic(session, request.getTopicId(), request.getOnOffLike());
        return OkResponse.OK_RESPONSE;
    }

}
