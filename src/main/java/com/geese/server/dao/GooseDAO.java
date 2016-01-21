package com.geese.server.dao;

import com.geese.server.domain.Flock;
import com.geese.server.domain.Goose;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
public interface GooseDAO {
    /**
     * Finds all Goose entries from the database.
     * @return  All existing Geese.
     */
    List<Goose> findAll();

    /**
     * Finds the a single Goose.
     * @param gooseId   The id of the requested Goose entry.
     * @return          The found Goose.
     */
    Goose findOne(int gooseId);

    /**
     * Favourite a flock for a Goose.
     * @param gooseId   The gooseId of the requested Goose entry.
     * @param flockId   The flockId of the favourite flock
     * @return          The number of updated entries
     */
    int favouriteFlock(String gooseId, String flockId);

    /**
     * Get favourited flocks for a Goose.
     * @param gooseId   The gooseId of the requested Goose entry.
     * @return          A list of favourited flocks
     */
    List<Flock> getFavourited(String gooseId);

    /**
     * Finds the a single Goose.
     * @param email     The email.
     * @return          The found Goose.
     */
    Goose findByEmail(String email);

    /**
     * Creates a new User entry to the database.
     * @param createdGoose      The information of the new User entry.
     * @return                  The number of created Goose objects.
     */
    int create(Goose createdGoose);

    /**
     * Updates an existing Goose entry in the database.
     * @param updatedGoose  The information of the updated Goose entry.
     * @return              The number of updated Goose objects.
     */
    int update(Goose updatedGoose);

    /**
     * Deletes a Goose from the database.
     * @param gooseId   The id of the requested Goose entry.
     * @return          The number of deleted Goose objects.
     */
    int delete(int gooseId);
}
