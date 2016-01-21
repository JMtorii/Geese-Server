package com.geese.server.controller;

import com.geese.server.domain.Post;
import com.geese.server.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by ecrothers on 2015-11-08.
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    /**
     * Gets a Post by id
     * @param postId   Identifier for Post
     * @return          If Post is found, return the Post object and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPost(@PathVariable String postId) {
        Post foundPost = postService.findOne(postId);

        if (foundPost != null) {
            return new ResponseEntity<>(foundPost, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets all Posts in database
     * @return      If Geese exist, return list of Geese and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam String flockId) {
        List<Post> geese = postService.findAll(flockId);

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Post if the post does not already exist
     * @param post Post to persist in server
     * @return      If Post is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        int numCreatedPost = postService.create(post);

        if (numCreatedPost > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing Post
     * @param postId   Identifier for Post
     * @param post     Post to persist in server
     * @return          If the Post exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(@PathVariable String postId, @RequestBody Post post) {
        int numUpdatedPost = postService.update(postId, post);

        if (numUpdatedPost > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing Post
     * @param postId   Identifier for the Post
     * @return          If the Post exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<Post> deletePost(@PathVariable String postId) {
        int numDeletedPost = postService.delete(postId);

        if (numDeletedPost > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
