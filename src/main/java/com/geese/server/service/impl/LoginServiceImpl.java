package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.service.TokenService;
import com.geese.server.service.GooseService;
import com.geese.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Service
@SuppressWarnings("unused")
public class LoginServiceImpl implements LoginService {

    @Autowired
    @Qualifier("gooseServiceImpl")
    private GooseService gooseService;

    @Autowired
    private TokenService tokenService;

    @Override
    public String Login(String email, String password) {
        //TODO Make sure we added actual authentication here

        GooseAuthentication auth = new GooseAuthentication(gooseService.loadUserByUsername(email));
        return tokenService.getToken(auth);
    }

    @Override
    public String LoginFromFacebook(String token) {
        return "";
    };
}
