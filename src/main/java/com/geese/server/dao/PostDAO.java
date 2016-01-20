package com.geese.server.dao;

import com.geese.server.domain.Topic;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface PostDAO {

    /**
     * Deletes a Topic entry from the database.
     * @param topicId   The id of the topic entry to delete
     */
    int delete(final int topicId);

    /**
     * Finds all Topic entries from the database.
     * @return  The information of all Topic entries that are found from the database.
     */
    List<Topic> findAll();

    /**
     * Finds the information of a single Topic entry.
     * @param topicId    The id of the requested Topic entry.
     * @return      The information of the found Topic entry.
     */
    Topic findOne(final int topicId);

    /**
     * Saves a new Topic entry to the database.
     * @param created   The information of the created Topic entry.
     * @return          The number of created Topics
     */
    int create(final Topic created);

    /**
     * Update an existing Topic entry in the database.
     * @param updated   The information of the saved Topic entry.
     * @return          The number of updated entries
     */
    int update(final Topic updated);
}
