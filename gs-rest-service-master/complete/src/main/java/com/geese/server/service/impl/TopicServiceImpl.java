package com.geese.server.service.impl;

import com.geese.server.dao.TopicDAO;
import com.geese.server.domain.Topic;
import com.geese.server.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDAO topicDAO;

    @Override
    public int delete(String topicId) {
        return topicDAO.delete(Integer.valueOf(topicId));
    }

    @Override
    public List<Topic> findAll() {
        return topicDAO.findAll();
    }

    @Override
    public Topic findOne(String topicId) {
        return topicDAO.findOne(Integer.valueOf(topicId));
    }


    @Override
    public int create(Topic saved) {
        return topicDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the topicId
    public int update(String topicId, Topic updatedTopic) {
        return topicDAO.update(updatedTopic);
    }
}
