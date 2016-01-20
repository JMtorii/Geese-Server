package com.geese.server.service.impl;

import com.geese.server.dao.CommentDAO;
import com.geese.server.domain.Post;
import com.geese.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class CommentServiceImpl implements PostService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public int delete(String postId) {
        return commentDAO.delete(Integer.valueOf(postId));
    }

    @Override
    public List<Post> findAll() {
        return commentDAO.findAll();
    }

    @Override
    public Post findOne(String postId) {
        return commentDAO.findOne(Integer.valueOf(postId));
    }


    @Override
    public int create(Post saved) {
        return commentDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the postId
    public int update(String postId, Post updatedPost) {
        return commentDAO.update(updatedPost);
    }
}
