package com.geese.server.service;

import com.geese.server.domain.Event;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
public interface EventService {
    /**
     * Deletes a Event entry from the database.
     * @param eventId   The id of the Event to delete from the database
     * @return  The number of deleted Events
     */
    int delete(String eventId);

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
    Event findOne(String eventId);

    /**
     * Saves a new Event entry to the database.
     * @param saved     The information of the saved Event entry.
     * @return          Number of created entries
     */
    int create(Event saved);

    /**
     * Updates an existing Event entry in the database.
     * @param eventId       The eventId of the requested Event entry.
     * @param updatedEvent  The information of the updated Event entry.
     * @return              The number of updated Event objects.
     */
    int update(String eventId, Event updatedEvent);
}
