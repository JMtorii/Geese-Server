package com.geese.server.dao;

import com.geese.server.domain.Post;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface PostDAO {

    /**
     * Deletes a Post entry from the database.
     * @param postId   The id of the post entry to delete
     */
    int delete(final int postId);

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
    Post findOne(final int postId);

    /**
     * Saves a new Post entry to the database.
     * @param created   The information of the created Post entry.
     * @return          The number of created Posts
     */
    int create(final Post created);

    /**
     * Update an existing Post entry in the database.
     * @param updated   The information of the saved Post entry.
     * @return          The number of updated entries
     */
    int update(final Post updated);
}
