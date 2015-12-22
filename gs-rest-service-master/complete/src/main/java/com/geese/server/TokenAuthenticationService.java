package com.geese.server;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.impl.GooseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ni on 2015-11-18.
 */

@Service
public class TokenAuthenticationService {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final TokenHandler tokenHandler;

    public TokenAuthenticationService(String secret) {
        tokenHandler = new TokenHandler(secret);
    }

    public void addAuthentication(HttpServletResponse response, GooseAuthentication authentication) {
    final Goose goose = authentication.getDetails();
    response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(goose));
    }

    public String getToken(GooseAuthentication authentication) {
        final Goose goose = authentication.getDetails();
        return tokenHandler.createTokenForUser(goose);
    }

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