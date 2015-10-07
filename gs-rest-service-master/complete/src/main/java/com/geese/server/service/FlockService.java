package com.geese.server.service;

import com.geese.server.domain.Flock;

import java.util.List;
import java.util.Optional;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface FlockService {
    /**
     * Deletes a Flock entry from the database.
     * @param deleted   The deleted Flock entry.
     */
    void delete(Flock deleted);

    /**
     * Finds all Flock entries from the database.
     * @return  The information of all Flock entries that are found from the database.
     */
    List<Flock> findAll();

    /**
     * Finds the information of a single Flock entry.
     * @param id    The id of the requested Flock entry.
     * @return      The information of the found Flock entry.
     */
    Flock findOne(int id);

    /**
     * Saves a new Flock entry to the database.
     * @param saved     The information of the saved Flock entry.
     * @return          The information fo the saved Flock entry.
     */
    Flock save(Flock saved);

    /**
     * Updates the information of an existing Flock entry.
     * @param flock     The information of the updated Flock entry.
     * @return          The information of the updated Flock entry.
     */
    Flock update(Flock flock);
}
