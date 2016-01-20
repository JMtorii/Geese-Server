package com.geese.server.service;

import com.geese.server.domain.Topic;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
public interface PostService {
    /**
     * Deletes a Topic entry from the database.
     * @param topicId   The id of the Topic to delete from the database
     * @return  The number of deleted Topics
     */
    int delete(String topicId);

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
    Topic findOne(String topicId);

    /**
     * Saves a new Topic entry to the database.
     * @param saved     The information of the saved Topic entry.
     * @return          Number of created entries
     */
    int create(Topic saved);

    /**
     * Updates an existing Topic entry in the database.
     * @param topicId       The topicId of the requested Topic entry.
     * @param updatedTopic  The information of the updated Topic entry.
     * @return              The number of updated Topic objects.
     */
    int update(String topicId, Topic updatedTopic);
}
