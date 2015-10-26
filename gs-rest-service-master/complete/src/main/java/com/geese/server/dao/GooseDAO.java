package com.geese.server.dao;

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
    Goose findOne(final int gooseId);

    /**
     * Creates a new User entry to the database.
     * @param createdGoose      The information of the new User entry.
     * @return                  The created Goose.
     */
    Goose create(final Goose createdGoose);

    /**
     * Updates an existing Goose entry in the database.
     * @param updatedGoose  The information of the updated Goose entry.
     * @return              The updated Goose.
     */
    Goose update(final Goose updatedGoose);

    /**
     * Deletes a Goose from the database.
     * @param gooseId   The deleted Goose entry.
     */
    Goose delete(final int gooseId);
}
