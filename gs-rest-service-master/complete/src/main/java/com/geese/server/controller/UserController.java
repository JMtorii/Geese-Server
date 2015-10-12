package com.geese.server.controller;

import com.geese.server.domain.User;
import com.geese.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JMtorii on 2015-10-12.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/user/{userId}", method= RequestMethod.POST)
    public @ResponseBody User createFlock(@RequestParam(value="email", required=true) String email,
                                          @RequestParam(value="password", required=true) String password) {
        User user = User.getBuilder()
                .email(email)
                .password(password)
                .build();

        userService.save(user);

        return user;
    }
}
