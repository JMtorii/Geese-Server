package com.geese.server.service;

import com.geese.server.domain.Goose;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
public interface GooseService {
    /**
     * Finds all Goose entries from the database.
     * @return  The information of all Goose entries that are found from the database.
     */
    List<Goose> findAll();

    /**
     * Finds the information of a single Goose entry.
     * @param id    The id of the requested Goose entry.
     * @return      The information of the found Goose entry.
     */
    Goose findOne(int id);

    /**
     * Saves a new Goose entry to the database.
     * @param savedGoose     The information of the saved Goose entry.
     * @return          The number of affected rows
     */
    Goose save(Goose savedGoose);

    /**
     * Deletes a Goose entry from the database.
     * @param deletedGoose   The deleted Goose entry.
     */
    void delete(Goose deletedGoose);
}
