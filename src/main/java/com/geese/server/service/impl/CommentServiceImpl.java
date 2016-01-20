package com.geese.server.service.impl;

import com.geese.server.dao.CommentDAO;
import com.geese.server.domain.Comment;
import com.geese.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public int delete(String commentId) {
        return commentDAO.delete(Integer.valueOf(commentId));
    }

    @Override
    public List<Comment> findAll() {
        return commentDAO.findAll();
    }

    @Override
    public Comment findOne(String commentId) {
        return commentDAO.findOne(Integer.valueOf(commentId));
    }


    @Override
    public int create(Comment saved) {
        return commentDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the commentId
    public int update(String commentId, Comment updatedComment) {
        return commentDAO.update(updatedComment);
    }
}
