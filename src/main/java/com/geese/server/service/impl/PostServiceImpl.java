package com.geese.server.service.impl;

import com.geese.server.dao.PostDAO;
import com.geese.server.domain.Topic;
import com.geese.server.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class PostServiceImpl implements TopicService {
    @Autowired
    private PostDAO postDAO;

    @Override
    public int delete(String topicId) {
        return postDAO.delete(Integer.valueOf(topicId));
    }

    @Override
    public List<Topic> findAll() {
        return postDAO.findAll();
    }

    @Override
    public Topic findOne(String topicId) {
        return postDAO.findOne(Integer.valueOf(topicId));
    }


    @Override
    public int create(Topic saved) {
        return postDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the topicId
    public int update(String topicId, Topic updatedTopic) {
        return postDAO.update(updatedTopic);
    }
}
