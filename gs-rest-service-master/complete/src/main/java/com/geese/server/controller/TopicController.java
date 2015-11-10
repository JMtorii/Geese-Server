package com.geese.server.controller;

import com.geese.server.domain.Topic;
import com.geese.server.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 * Created by ecrothers on 2015-11-08.
 */
@RestController
@RequestMapping(value = "/topic")
public class TopicController {
    private static Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    /*TopicController(TopicService topicService) {
        this.topicService = topicService;
    }*/

    /*@RequestMapping(value="/", method= RequestMethod.POST)
    public Topic createTopic(@RequestBody Topic topic) {
        Topic created = topicService.save(topic);
        return created;
    }*/

    /**
     * Gets a Topic by id
     * @param topicId   Identifier for Topic
     * @return          If Topic is found, return the Topic object and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<Topic> getTopic(@PathVariable String topicId) {
        Topic foundTopic = topicService.findOne(topicId);

        if (foundTopic != null) {
            return new ResponseEntity<>(foundTopic, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets all Topics in database
     * @return      If Geese exist, return list of Geese and HTTP status 302; otherwise, 404
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> geese = topicService.findAll();

        if (geese != null) {
            return new ResponseEntity<>(geese, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Topic if the topic does not already exist
     * @param topic Topic to persist in server
     * @return      If Topic is successfully created, return HTTP status 201; otherwise, 400
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        int numCreatedTopic = topicService.create(topic);

        if (numCreatedTopic > 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            // TODO: choose better HTTP status
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an existing Topic
     * @param topicId   Identifier for Topic
     * @param topic     Topic to persist in server
     * @return          If the Topic exists and is changed, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{topicId}", method = RequestMethod.PUT)
    public ResponseEntity<Topic> updateTopic(@PathVariable String topicId, @RequestBody Topic topic) {
        int numUpdatedTopic = topicService.update(topicId, topic);

        if (numUpdatedTopic > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing Topic
     * @param topicId   Identifier for the Topic
     * @return          If the Topic exists and is deleted, return HTTP status 202; otherwise 404.
     */
    @RequestMapping(value = "/{topicId}", method = RequestMethod.DELETE)
    public ResponseEntity<Topic> deleteTopic(@PathVariable String topicId) {
        int numDeletedTopic = topicService.delete(topicId);

        if (numDeletedTopic > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
