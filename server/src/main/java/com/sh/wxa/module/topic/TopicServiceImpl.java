package com.sh.wxa.module.topic;

import com.google.common.collect.Lists;
import com.sh.wxa.module.topic.entity.Topic;
import com.sh.wxa.module.topic.entity.TopicInfo;
import com.sh.wxa.module.topic.mapper.TopicInfoMapper;
import com.sh.wxa.module.topic.mapper.TopicMapper;
import com.sh.wxa.module.topic.msg.TopicListRequest;
import com.sh.wxa.module.topic.msg.TopicListResponse;
import com.sh.wxa.module.topic.msg.createTopicRequest;
import com.sh.wxa.module.topic.msg.po.TopicPo;
import com.sh.wxa.onlinemanager.Session;
import com.sh.wxa.util.PageUtil;
import com.sh.wxa.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicInfoMapper topicInfoMapper;

    @Override
    public void commentTopic(Session session, createTopicRequest request) {
        final String context = request.getContent();
        Topic topic = new Topic();
        topic.setAuthorUid(session.getOpenId());
        topic.setAuthorNickName(session.getNickName());
        topic.setContent(context);
        topic.setAuthorIconUrl(session.getIconUrl());
        topic.setCreateTime(new Date());
        topicMapper.add(topic);
    }

    @Override
    public TopicListResponse findTopicList(Session session, TopicListRequest request) {
        final long index = Math.max(request.getBeginIndex(), 0);
        List<Topic> topicList = topicMapper.findByCondition(index, PageUtil.DEFAULT_PAGE_SIZE);
        TopicListResponse resp = new TopicListResponse();
        List<TopicPo> activityInfoList = resp.getTopicInfosList();
        for(Topic topic : topicList) {
            TopicPo info = topic.toInfo();
            if(topic.getTopicInfo() != null) {
                List<String> likeUsers = StringUtils.parseStringList(topic.getTopicInfo().getLikeUsers());
                if (likeUsers.contains(session.getOpenId())) {
                    info.setLike(1);
                }
            }
            activityInfoList.add(info);
        }
        return resp;
    }

    @Override
    public void onOffLikeTopic(Session session, long topicId, int onOffLike) {
        int count = topicInfoMapper.countById(topicId);
        if(count > 0) {
            TopicInfo topicInfo = topicInfoMapper.findById(topicId);
            String likeUsersStr = topicInfo.getLikeUsers();
            List<String> likeUsers = StringUtils.parseStringList(likeUsersStr);
            if(onOffLike == 1) {
                if(!likeUsers.contains(session.getOpenId())) {
                    likeUsers.add(session.getOpenId());
                }
            } else {
                likeUsers.remove(session.getOpenId());
            }
            topicInfo.setLikeUsers(StringUtils.toStringForList(likeUsers));
            topicInfoMapper.update(topicInfo);
        } else {
            if(onOffLike == 1) {
                TopicInfo topicInfo = new TopicInfo();
                topicInfo.setTopicId(topicId);
                List<String> likeUsers = Lists.newArrayList();
                likeUsers.add(session.getOpenId());
                topicInfo.setLikeUsers(StringUtils.toStringForList(likeUsers));
                topicInfoMapper.add(topicInfo);
            }
        }
    }

}
