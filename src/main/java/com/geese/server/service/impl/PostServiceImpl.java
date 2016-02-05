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
        return postDAO.findAll(flockId);
    }

    @Override
    public Post findOne(int postId) {
        return postDAO.findOne(postId);
    }


    @Override
    public int create(Post saved) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();

        Post newPost = new Post.Builder(saved)
                .authorid(authorId)
                .createdTime(LocalDateTime.now())
                .build();
        return postDAO.create(newPost);
    }

    @Override
    public int update(Post updatedPost) {
        return postDAO.update(updatedPost);
    }

    @Override
    public int vote(int postId, int value) {
        //SecurityContextHolder.getContext().getAuthentication().getName();
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int authorId = auth.getDetails().getId();


        Post post = postDAO.findOne(postId);
        post = new Post.Builder(post).score(post.getScore()+value).build();
        postDAO.update(post);
        PostVote postVote = new PostVote.Builder(authorId, postId).value(value).build();
        return postVoteDAO.createOrOverwrite(postVote);
    }
}
