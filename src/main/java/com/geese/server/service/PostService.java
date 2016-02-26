package com.geese.server.service;

import com.geese.server.domain.Post;
import com.geese.server.domain.PostVote;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
public interface PostService {
    /**
     * Deletes a Post entry from the database.
     * @param postId   The id of the Post to delete from the database
     * @return  The number of deleted Topics
     */
    int delete(int postId);

    /**
     * Finds all Post entries from the database, that match the passed args
     * @param flockId   The id of the flock to match (if any)
     * @return  The information of all Post entries that are found from the database.
     */
    List<Post> findAll(int flockId);

    /**
     * Finds the information of a single Post entry.
     * @param postId    The id of the requested Post entry.
     * @return      The information of the found Post entry.
     */
    Post findOne(int postId);

    /**
     * Finds the information of a single PostVote entry.
     * @param gooseId    The id of the user of the requested PostVote entry.
     * @param postId    The id of the post of the requested Post entry.
     * @return      The information of the found Post entry.
     */
    PostVote findOne(int gooseId, int postId);

    /**
     * Saves a new Post entry to the database.
     * @param saved     The information of the saved Post entry.
     * @return          Number of created entries
     */
    int create(Post saved);

    /**
     * Updates an existing Post entry in the database.
     * @param updatedPost  The information of the updated Post entry.
     * @return              The number of updated Post objects.
     */
    int update(Post updatedPost);

    /**
     * Votes on a Post,
     * updates an existing Post entry in the database.
     * creates a PostVote entry as well
     * @param postId       The postId of the requested Post entry.
     * @param value        Upvote, no vote, or downvote
     * @return              The number of updated Post objects.
     */
    int vote(int postId, int value);
}
