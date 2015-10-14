package com.geese.server.controller;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JMtorii on 2015-10-12.
 */
@RestController
@RequestMapping(value = "/goose")
public class GooseController {
//    @Autowired
//    GooseService gooseService;

    private GooseService gooseService;

    @Autowired
    GooseController(GooseService gooseService) {
        this.gooseService = gooseService;
    }

//    @RequestMapping(value="/goose/{gooseId}", method=RequestMethod.POST)
//    public @ResponseBody Goose createFlock(@RequestParam(value="email", required=true) String email,
//                                          @RequestParam(value="password", required=true) String password) {
//        Goose goose = Goose.getBuilder()
//                .email(email)
//                .password(password)
//                .build();
//
//        gooseService.save(goose);
//
//        return goose;
//    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Goose createGoose(@RequestBody Goose goose) {
        return gooseService.save(goose);
    }
}
