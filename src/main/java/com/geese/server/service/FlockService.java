package com.geese.server.service;

import com.geese.server.domain.Flock;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface FlockService {
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

    /**
     * Finds all nearby Flocks
     * @param latitude      Latitude of the client
     * @param longitude     Longitude of the client
     * @return              List of nearby flocks
     */
    List<Flock> getNearbyFlocks(float latitude, float longitude);
}
