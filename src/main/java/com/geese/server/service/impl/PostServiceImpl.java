package com.geese.server.service.impl;

import com.geese.server.dao.PostDAO;
import com.geese.server.domain.Post;
import com.geese.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    @Override
    public int delete(String postId) {
        return postDAO.delete(Integer.valueOf(postId));
    }

    @Override
    public List<Post> findAll(String flockId) {
        return postDAO.findAll(Integer.valueOf(flockId));
    }

    @Override
    public Post findOne(String postId) {
        return postDAO.findOne(Integer.valueOf(postId));
    }


    @Override
    public int create(Post saved) {
        return postDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the postId
    public int update(String postId, Post updatedPost) {
        return postDAO.update(updatedPost);
    }
}
