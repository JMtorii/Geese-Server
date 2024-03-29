package com.geese.server.service;

import com.geese.server.domain.Comment;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
public interface CommentService {
    /**
     * Deletes a Comment entry from the database.
     * @param commentId   The id of the Comment to delete from the database
     * @return  The number of deleted Posts
     */
    int delete(String commentId);

    /**
     * Finds all Comment entries from the database.
     * @param postId    The id of the post to find all comments under
     * @return  The information of all Comment entries that are found from the database.
     */
    List<Comment> findAll(int postId);

    /**
     * Finds the information of a single Comment entry.
     * @param commentId    The id of the requested Comment entry.
     * @return      The information of the found Comment entry.
     */
    Comment findOne(String commentId);

    /**
     * Saves a new Comment entry to the database.
     * @param saved     The information of the saved Comment entry.
     * @return          Number of created entries
     */
    int create(Comment saved);

    /**
     * Updates an existing Comment entry in the database.
     * @param commentId       The commentId of the requested Comment entry.
     * @param updatedComment  The information of the updated Comment entry.
     * @return              The number of updated Comment objects.
     */
    int update(String commentId, Comment updatedComment);

    /**
     * Votes on a Comment,
     * updates an existing Comment entry in the database.
     * creates a CommentVote entry as well
     * @param commentId       The commentId of the requested Comment entry.
     * @param value        Upvote, no vote, or downvote
     * @return              The number of updated Comment objects.
     */
    int vote(int commentId, int value);

    /**
     * gets the authenticated user's vote value on a Comment
     * @param commentId       The commentId of the requested Comment entry.
     * @return              The value of the user's vote, 0 if none.
     */
    int getVote(int commentId);
}
