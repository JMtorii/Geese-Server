package com.geese.server.dao;

import com.geese.server.domain.PostVote;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface PostVoteDAO {

    /**
     * Deletes a PostVote entry from the database.
     * @param gooseId   The gooseid of the postvote entry to delete
     * @param postId    The poarid of the postvote entry to delete
     */
    int delete(final int gooseId, final int postId);

    /**
     * Finds all PostVote entries from the database.
     * @param flockId   The id of the flock to match (if any)
     * @return  The information of all PostVote entries that are found from the database.
     */
    List<PostVote> findAll(final int flockId);

    /**
     * Finds the information of a single PostVote entry.
     * @param gooseId    The gooseid of the requested PostVote entry.
     * @param postId     The postid of the requested PostVote entry
     * @return      The information of the found Post entry.
     */
    PostVote findOne(final int gooseId, final int postId);

    /**
     * Saves a new PostVote entry to the database.
     * @param created   The information of the created Post entry.
     * @return          The number of created Posts
     */
    int create(final PostVote created);

    /**
     * Update an existing PostVote entry in the database.
     * @param updated   The information of the saved PostVote entry.
     * @return          The number of updated entries
     */
    int update(final PostVote updated);

    /**
     * Create or overwrite an existing PostVote entry in the database.
     * @param postVote   The information of the saved PostVote entry.
     * @return          The number of updated entries
     */
    int createOrOverwrite(final PostVote postVote);
}
