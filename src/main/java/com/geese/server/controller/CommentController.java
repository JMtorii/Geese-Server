package com.geese.server.controller;

import com.geese.server.domain.Comment;
import com.geese.server.service.CommentService;
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
@RequestMapping(value = "/comment")
public class CommentController {
    private static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /*CommentController(CommentService commentService) {
        this.commentService = commentService;
    }*/

    /*@RequestMapping(value="/", method= RequestMethod.POST)
    public Comment createComment(@RequestBody Comment comment) {
        Comment created = commentService.save(comment);
        return created;
    }*/

    /**
     * Gets a Comment by id
     * @param commentId   Identifier for Comment
     * @return          If Comment is found, return the Comment object and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getComment(@PathVariable String commentId) {
        Comment foundComment = commentService.findOne(commentId);

        if (foundComment != null) {
            return new ResponseEntity<>(foundComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * TODO: Is this necessary?
     *
     * Gets all Comments in database
     * @return      If comments exist, return list of Geese and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam int postId) {
        List<Comment> geese = commentService.findAll(postId);

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creates a new Comment if the comment does not already exist
     * @param comment Comment to persist in server
     * @return      If Comment is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        int numCreatedComment = commentService.create(comment);

        if (numCreatedComment > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing Comment
     * @param commentId   Identifier for Comment
     * @param comment     Comment to persist in server
     * @return          If the Comment exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> updateComment(@PathVariable String commentId, @RequestBody Comment comment) {
        int numUpdatedComment = commentService.update(commentId, comment);

        if (numUpdatedComment > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes an existing Comment
     * @param commentId   Identifier for the Comment
     * @return          If the Comment exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable String commentId) {
        int numDeletedComment = commentService.delete(commentId);

        if (numDeletedComment > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
