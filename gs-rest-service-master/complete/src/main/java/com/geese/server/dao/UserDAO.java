package com.geese.server.dao;

import com.geese.server.domain.User;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
public interface UserDAO {
    /**
     * Finds all Flock entries from the database.
     * @return  The information of all Flock entries that are found from the database.
     */
    List<User> findAll();

    /**
     * Finds the information of a single Flock entry.
     * @param id    The id of the requested Flock entry.
     * @return      The information of the found Flock entry.
     */
    User findOne(int id);

    /**
     * Saves a new Flock entry to the database.
     * @param savedUser     The information of the saved Flock entry.
     * @return          The number of affected rows
     */
    int save(User savedUser);

    /**
     * Deletes a Flock entry from the database.
     * @param deletedUser   The deleted Flock entry.
     */
    void delete(User deletedUser);
}
