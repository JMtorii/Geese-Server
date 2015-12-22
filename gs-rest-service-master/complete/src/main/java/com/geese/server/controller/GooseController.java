package com.geese.server.controller;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.impl.GooseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
@RestController
@RequestMapping(value = "/goose")
public class GooseController {

    @Autowired
    @Qualifier("gooseServiceImpl")
    private GooseService gooseService;

    /**
     * Gets a Goose by id
     * @param gooseId   Identifier for Goose
     * @return          If Goose is found, return the Goose object and HTTP status 200; otherwise, 404
     */
    @RequestMapping(value = "/{gooseId}", method = RequestMethod.GET)
    public ResponseEntity<Goose> getGoose(@PathVariable String gooseId) {
        Goose foundGoose = gooseService.findOne(gooseId);

        if (foundGoose != null) {
            return new ResponseEntity<>(foundGoose, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets all Geese
     * @return      If Geese exist, return list of Geese and HTTP status 200; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Goose>> getAllGeese() {
        List<Goose> geese = gooseService.findAll();

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Goose if the id and username do not exist
     * @param goose Goose to persist in server
     * @return      If Goose is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Goose> createGoose(@RequestBody Goose goose) {
        int numCreatedGoose = gooseService.create(goose);

        if (numCreatedGoose > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing Goose
     * @param gooseId   Identifier for Goose
     * @param goose     Goose to persist in server
     * @return          If the Goose exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{gooseId}", method = RequestMethod.PUT)
    public ResponseEntity<Goose> updateGoose(@PathVariable String gooseId, @RequestBody Goose goose) {
        int numUpdatedGoose = gooseService.update(gooseId, goose);

        if (numUpdatedGoose > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing Goose
     * @param gooseId   Identifier for the Goose
     * @return          If the Goose exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{gooseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Goose> deleteGoose(@PathVariable String gooseId) {
        int numDeletedGoose = gooseService.delete(gooseId);

        if (numDeletedGoose > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
