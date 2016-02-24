package com.geese.server.controller;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.LoginService;
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
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> getToken(@RequestBody Goose goose) {
        String token = loginService.Login(goose.getEmail(), goose.getPassword());

        if (token != "") {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/fb/{accessToken}", method = RequestMethod.POST)
    public ResponseEntity<String> getTokenViaFB(@PathVariable String accessToken) {
        String token = loginService.LoginFromFacebook(accessToken);

        if (token != "") {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}