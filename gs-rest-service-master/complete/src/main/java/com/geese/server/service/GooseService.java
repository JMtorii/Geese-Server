package com.geese.server.service;

import com.geese.server.domain.Goose;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
public interface GooseService {
    /**
     * Finds all Goose entries from the database.
     * @return  All existing Geese.
     */
    List<Goose> findAll();

    /**
     * Finds the a single Goose.
     * @param gooseId   The gooseId of the requested Goose entry.
     * @return          The found Goose.
     */
    Goose findOne(String gooseId);

    /**
     * Creates a new Goose entry to the database.
     * @param createdGoose      The information of the new Goose entry.
     * @return                  The created Goose.
     */
    Goose create(Goose createdGoose);

    /**
     * Updates an existing Goose entry in the database.
     * @param gooseId       The gooseId of the requested Goose entry.
     * @param updatedGoose  The information of the updated Goose entry.
     * @return              The updated Goose.
     */
    Goose update(String gooseId, Goose updatedGoose);

    /**
     * Deletes a Goose from the database.
     * @param gooseId   ID to delete from database.
     */
    Goose delete(String gooseId);
}
