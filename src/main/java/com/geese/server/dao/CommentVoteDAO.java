package com.geese.server.dao;

import com.geese.server.domain.CommentVote;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface CommentVoteDAO {

    /**
     * Deletes a CommentVote entry from the database.
     * @param gooseId   The gooseid of the commentvote entry to delete
     * @param commentId    The commentid of the commentvote entry to delete
     */
    int delete(final int gooseId, final int commentId);

    /**
     * Finds all CommentVote entries from the database.
     * @param flockId   The id of the flock to match (if any)
     * @return  The information of all CommentVote entries that are found from the database.
     */
    List<CommentVote> findAll(final int flockId);

    /**
     * Finds the information of a single CommentVote entry.
     * @param gooseId    The gooseid of the requested CommentVote entry.
     * @param commentId     The commentid of the requested CommentVote entry
     * @return      The information of the found Comment entry.
     */
    CommentVote findOne(final int gooseId, final int commentId);

    /**
     * Saves a new CommentVote entry to the database.
     * @param created   The information of the created Comment entry.
     * @return          The number of created Comments
     */
    int create(final CommentVote created);

    /**
     * Update an existing CommentVote entry in the database.
     * @param updated   The information of the saved CommentVote entry.
     * @return          The number of updated entries
     */
    int update(final CommentVote updated);

    /**
     * Create or overwrite an existing CommentVote entry in the database.
     * @param commentVote   The information of the saved CommentVote entry.
     * @return          The number of updated entries
     */
    int createOrOverwrite(final CommentVote commentVote);
}
