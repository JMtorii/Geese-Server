package com.geese.server.controller;

import com.geese.server.domain.Event;
import com.geese.server.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by ecrothers on 2015-11-08.
 */
@RestController
@RequestMapping(value = "/event")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    /**
     * Gets a Event by id
     * @param eventId   Identifier for Event
     * @return          If Event is found, return the Event object and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@PathVariable String eventId) {
        Event foundEvent = eventService.findOne(eventId);

        if (foundEvent != null) {
            return new ResponseEntity<>(foundEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets all Events in database
     * @return      If Geese exist, return list of Geese and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> geese = eventService.findAll();

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creates a new Event if the event does not already exist
     * @param event Event to persist in server
     * @return      If Event is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        int numCreatedEvent = eventService.create(event);

        if (numCreatedEvent > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing Event
     * @param eventId   Identifier for Event
     * @param event     Event to persist in server
     * @return          If the Event exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
    public ResponseEntity<Event> updateEvent(@PathVariable String eventId, @RequestBody Event event) {
        int numUpdatedEvent = eventService.update(eventId, event);

        if (numUpdatedEvent > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes an existing Event
     * @param eventId   Identifier for the Event
     * @return          If the Event exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEvent(@PathVariable String eventId) {
        int numDeletedEvent = eventService.delete(eventId);

        if (numDeletedEvent > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
