package com.geese.server.service;

import com.geese.server.domain.Post;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
public interface PostService {
    /**
     * Deletes a Post entry from the database.
     * @param postId   The id of the Post to delete from the database
     * @return  The number of deleted Posts
     */
    int delete(String postId);

    /**
     * Finds all Post entries from the database.
     * @return  The information of all Post entries that are found from the database.
     */
    List<Post> findAll();

    /**
     * Finds the information of a single Post entry.
     * @param postId    The id of the requested Post entry.
     * @return      The information of the found Post entry.
     */
    Post findOne(String postId);

    /**
     * Saves a new Post entry to the database.
     * @param saved     The information of the saved Post entry.
     * @return          Number of created entries
     */
    int create(Post saved);

    /**
     * Updates an existing Post entry in the database.
     * @param postId       The postId of the requested Post entry.
     * @param updatedPost  The information of the updated Post entry.
     * @return              The number of updated Post objects.
     */
    int update(String postId, Post updatedPost);
}
