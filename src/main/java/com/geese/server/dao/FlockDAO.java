package com.geese.server.dao;

import com.geese.server.domain.Flock;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface FlockDAO {

    /**
     * Deletes a Flock entry from the database.
     * @param flockId   The id of the flock entry to delete
     */
    int delete(final int flockId);

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
    Flock findOne(final int flockId);

    /**
     * Saves a new Flock entry to the database.
     * @param created   The information of the created Flock entry.
     * @return          The number of created Flocks
     */
    int create(final Flock created);

    /**
     * Update an existing Flock entry in the database.
     * @param updated   The information of the saved Flock entry.
     * @return          The number of updated entries
     */
    int update(final Flock updated);

    /**
     * Finds all nearby Flocks
     * @param latitude  Latitude of the client
     * @param longitude Longitude of the client
     * @return          A list of nearby flocks
     */
    List<Flock> getNearbyFlocks(float latitude, float longitude);

    /**
     * Get favourited flocks for a Goose.
     * @param gooseId   Goose identifier
     * @return          A list of favourited flocks
     */
    List<Flock> getFavourited(int gooseId);

    /**
     * Join a flock
     * @param gooseId   Unique identifier of Goose
     * @param flockId   Unique identifier of Flock
     * @return          The number of modified Flocks
     */
    int joinFlock(int gooseId, int flockId);

    /**
     * Unjoin a flock
     * @param gooseId   Unique identifier of Goose
     * @param flockId   Unique identifier of Flock
     * @return          The number of modified Flocks
     */
    int unjoinFlock(int gooseId, int flockId);
}
