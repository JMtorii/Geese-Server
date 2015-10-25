package com.geese.server.controller;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by JMtorii on 2015-10-12.
 */
@RestController
@RequestMapping(value = "/goose")
public class GooseController {

    @Autowired
    private GooseService userService;

    /**
     * Gets a user by id
     * @param gooseId   Identifier for goose
     * @return          If goose is found, return the user object and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "/{gooseId}", method = RequestMethod.GET)
    public ResponseEntity<Goose> getUser(@PathVariable String gooseId) {
        Goose foundGoose = userService.findOne(gooseId);

        if (foundGoose != null) {
            return new ResponseEntity<>(foundGoose, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets all users
     * @return      If geese exist, return list of users and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Goose>> getAllGeese() {
        ArrayList<Goose> geese = userService.findAll();

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new goose if the id and username do not exist
     * @param goose Goose to persist in server
     * @return      If user is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Goose> createGoose(@RequestBody Goose goose) {
        Goose createdGoose = userService.create(goose);

        if (createdGoose != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing user
     * @param gooseId   Identifier for user
     * @param goose     User to persist in server
     * @return          If the user exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{gooseId}", method = RequestMethod.PUT)
    public ResponseEntity<Goose> updateUser(@PathVariable String gooseId, @RequestBody Goose goose) {
        Goose updatedGoose = userService.update(gooseId, goose);

        if (updatedGoose != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing user
     * @param gooseId   Identifier for the user
     * @return          If the user exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{gooseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Goose> deleteUser(@PathVariable String gooseId) {
        Goose deletedGoose = userService.delete(gooseId);

        if (deletedGoose != null) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
