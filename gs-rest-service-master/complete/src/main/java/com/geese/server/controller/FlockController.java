package com.geese.server.controller;

import com.geese.server.domain.Flock;
import com.geese.server.service.FlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by JMtorii on 2015-10-06.
 */
@RestController
@RequestMapping(value = "/flock")
public class FlockController {
    private static final Logger logger = LoggerFactory.getLogger(FlockController.class);

    @Autowired
    private FlockService flockService;

    /**
     * Gets a Flock by id
     * @param flockId   Identifier for Flock
     * @return          If Flock is found, return the Flock object and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "/{flockId}", method = RequestMethod.GET)
    public ResponseEntity<Flock> getFlock(@PathVariable String flockId) {
        Flock foundFlock = flockService.findOne(flockId);

        if (foundFlock != null) {
            return new ResponseEntity<>(foundFlock, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets all Flocks in database
     * @return      If Geese exist, return list of Geese and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Flock>> getAllFlocks() {
        List<Flock> geese = flockService.findAll();

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Flock if the flock does not already exist
     * @param flock Flock to persist in server
     * @return      If Flock is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Flock> createFlock(@RequestBody Flock flock) {
        int numCreatedFlock = flockService.create(flock);

        if (numCreatedFlock > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing Flock
     * @param flockId   Identifier for Flock
     * @param flock     Flock to persist in server
     * @return          If the Flock exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{flockId}", method = RequestMethod.PUT)
    public ResponseEntity<Flock> updateFlock(@PathVariable String flockId, @RequestBody Flock flock) {
        int numUpdatedFlock = flockService.update(flockId, flock);

        if (numUpdatedFlock > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing Flock
     * @param flockId   Identifier for the Flock
     * @return          If the Flock exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{flockId}", method = RequestMethod.DELETE)
    public ResponseEntity<Flock> deleteFlock(@PathVariable String flockId) {
        int numDeletedFlock = flockService.delete(flockId);

        if (numDeletedFlock > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
