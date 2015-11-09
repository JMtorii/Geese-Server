package com.geese.server.service;

import com.geese.server.domain.Flock;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
public interface TopicService {
    /**
     * Deletes a Flock entry from the database.
     * @param flockId   The id of the Flock to delete from the database
     * @return  The number of deleted Flocks
     */
    int delete(String flockId);

    /**
     * Finds all Flock entries from the database.
     * @return  The information of all Flock entries that are found from the database.
     */
    List<Flock> findAll();

    /**
     * Finds the information of a single Flock entry.
     * @param flockId    The id of the requested Flock entry.
     * @return      The information of the found Flock entry.
     */
    Flock findOne(String flockId);

    /**
     * Saves a new Flock entry to the database.
     * @param saved     The information of the saved Flock entry.
     * @return          Number of created entries
     */
    int create(Flock saved);

    /**
     * Updates an existing Flock entry in the database.
     * @param flockId       The flockId of the requested Flock entry.
     * @param updatedFlock  The information of the updated Flock entry.
     * @return              The number of updated Flock objects.
     */
    int update(String flockId, Flock updatedFlock);
}