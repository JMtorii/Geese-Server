package com.geese.server.dao;

import com.geese.server.domain.Comment;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface CommentDAO {

    /**
     * Deletes a Comment entry from the database.
     * @param commentId   The id of the comment entry to delete
     */
    int delete(final int commentId);

    /**
     * Finds all Comment entries from the database.
     * @param gooseId    The id of goose to return a vote for if any
     * @param postId    The id of posts to filter the comments by
     * @return  The information of all Comment entries that are found from the database.
     */
    List<Comment> findAll(final int gooseId, final int postId);

    /**
     * Finds the information of a single Comment entry.
     * @param commentId    The id of the requested Comment entry.
     * @return      The information of the found Comment entry.
     */
    Comment findOne(final int commentId);

    /**
     * Saves a new Comment entry to the database.
     * @param created   The information of the created Comment entry.
     * @return          The number of created Comments
     */
    int create(final Comment created);

    /**
     * Update an existing Comment entry in the database.
     * @param updated   The information of the saved Comment entry.
     * @return          The number of updated entries
     */
    int update(final Comment updated);
}
