package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.TokenHandler;
import com.geese.server.domain.Goose;
import com.geese.server.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ni on 2015-11-18.
 */

@Service
public class TokenServiceImpl implements TokenService {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Override
    public void addAuthentication(HttpServletResponse response, GooseAuthentication authentication) {
        final Goose goose = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(goose));
    }

    @Override
    public String getToken(GooseAuthentication authentication) {
        final Goose goose = authentication.getDetails();
        return tokenHandler.createTokenForUser(goose);
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Goose goose = tokenHandler.parseUserFromToken(token);
            if (goose != null) {
                return new GooseAuthentication(goose);
            }
        }
        return null;
    }
}