package com.geese.server.controller;

import com.geese.server.domain.Flock;
import com.geese.server.service.FlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by JMtorii on 2015-10-06.
 */
@RestController
@RequestMapping(value = "/flock")
public class FlockController {
    private static  Logger LOGGER = LoggerFactory.getLogger(FlockController.class);
    private FlockService flockService;

    @Autowired
    FlockController(FlockService flockService) {
        this.flockService = flockService;
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public Flock createFlock(@RequestBody Flock flock) {
        Flock created = flockService.save(flock);
        return created;
    }
}
