package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.TokenAuthenticationService;
import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private TokenAuthenticationService tokenAuthenticationService;

    public String Login(String email, String password) {
        // Make sure we added actual authentication here

        GooseAuthentication auth = new GooseAuthentication(gooseService.loadUserByUsername(email));
        return tokenAuthenticationService.getToken(auth);
    }

    public String LoginFromFacebook(String token) {
        return "";
    };
}
