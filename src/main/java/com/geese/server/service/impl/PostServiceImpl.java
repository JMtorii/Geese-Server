package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.dao.PostDAO;
import com.geese.server.dao.PostVoteDAO;
import com.geese.server.domain.Post;
import com.geese.server.domain.PostVote;
import com.geese.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PostVoteDAO postVoteDAO;

    @Override
    public int delete(int postId) {
        return postDAO.delete(postId);
    }

    @Override
    public List<Post> findAll(int flockId) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int gooseId = auth.getDetails().getId();

        return postDAO.findAll(gooseId, flockId);
    }

    @Override
    public Post findOne(int postId) {
        return postDAO.findOne(postId);
    }

    @Override
    public PostVote findOne(int gooseId, int postId) {
        return postVoteDAO.findOne(gooseId, postId);
    }

    @Override
    public int create(Post saved) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();
        int flockid = saved.getFlockid();
        String title = saved.getTitle();
        String description = saved.getDescription();

        Post newPost = new Post.Builder(flockid, authorId, title, description)
                .build();
        return postDAO.create(newPost);
    }

    @Override
    public int update(Post updatedPost) {
        return postDAO.update(updatedPost);
    }

    @Override
    public int vote(int postId, int value) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();

        int adjustedValue = value;
        PostVote oldVote = postVoteDAO.findOne(authorId, postId);
        if (oldVote != null) {
            adjustedValue -= oldVote.getValue();
        }
        Post post = postDAO.findOne(postId);
        post = new Post.Builder(post).score(post.getScore()+adjustedValue).build();
        postDAO.update(post);
        PostVote postVote = new PostVote.Builder(authorId, postId).value(value).build();
        return postVoteDAO.createOrOverwrite(postVote);
    }

    @Override
    public int getVote(int postId) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();

        PostVote vote= postVoteDAO.findOne(authorId, postId);
        if (vote == null) {
            return 0;
        }

        return vote.getValue();
    }
}
