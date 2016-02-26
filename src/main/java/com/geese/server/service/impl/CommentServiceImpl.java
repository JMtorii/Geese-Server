package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.dao.CommentDAO;
import com.geese.server.dao.CommentVoteDAO;
import com.geese.server.domain.Comment;
import com.geese.server.domain.CommentVote;
import com.geese.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private CommentVoteDAO commentVoteDAO;

    @Override
    public int delete(String commentId) {
        return commentDAO.delete(Integer.valueOf(commentId));
    }

    @Override
    public List<Comment> findAll(final int postId) {
        return commentDAO.findAll(postId);
    }

    @Override
    public Comment findOne(String commentId) {
        return commentDAO.findOne(Integer.valueOf(commentId));
    }


    @Override
    public int create(Comment saved) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();
        int postid = saved.getPostid();
        String text = saved.getText();

        Comment newComment = new Comment.Builder(postid, authorId, text)
                .build();

        return commentDAO.create(newComment);
    }

    @Override
    // TODO: Why are we sending the commentId
    public int update(String commentId, Comment updatedComment) {
        return commentDAO.update(updatedComment);
    }

    @Override
    public int vote(int commentId, int value) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();

        int adjustedValue = value;
        CommentVote oldVote = commentVoteDAO.findOne(authorId, commentId);
        if (oldVote != null) {
            adjustedValue -= oldVote.getValue();
        }
        Comment comment = commentDAO.findOne(commentId);
        comment = new Comment.Builder(comment).score(comment.getScore()+adjustedValue).build();
        commentDAO.update(comment);
        CommentVote commentVote = new CommentVote.Builder(authorId, commentId).value(value).build();
        return commentVoteDAO.createOrOverwrite(commentVote);
    }

    @Override
    public int getVote(int commentId) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();

        CommentVote vote= commentVoteDAO.findOne(authorId, commentId);
        if (vote != null) {
            return 0;
        }

        return vote.getValue();
    }
}
