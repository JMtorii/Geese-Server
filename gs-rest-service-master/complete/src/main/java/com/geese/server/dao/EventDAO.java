package com.geese.server.dao;

import com.geese.server.domain.Event;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
public interface EventDAO {

    /**
     * Deletes a Event entry from the database.
     * @param eventId   The id of the event entry to delete
     */
    int delete(final int eventId);

    /**
     * Finds all Event entries from the database.
     * @return  The information of all Event entries that are found from the database.
     */
    List<Event> findAll();

    /**
     * Finds the information of a single Event entry.
     * @param eventId    The id of the requested Event entry.
     * @return      The information of the found Event entry.
     */
    Event findOne(final int eventId);

    /**
     * Saves a new Event entry to the database.
     * @param created   The information of the created Event entry.
     * @return          The number of created Events
     */
    int create(final Event created);

    /**
     * Update an existing Event entry in the database.
     * @param updated   The information of the saved Event entry.
     * @return          The number of updated entries
     */
    int update(final Event updated);
}
