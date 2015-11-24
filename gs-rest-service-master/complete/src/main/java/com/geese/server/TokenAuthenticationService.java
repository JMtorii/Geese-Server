package com.geese.server;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.impl.GooseServiceImpl;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ni on 2015-11-18.
 */
public class TokenAuthenticationService {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final TokenHandler tokenHandler;

    public TokenAuthenticationService(String secret, GooseServiceImpl gooseService) {
    tokenHandler = new TokenHandler(secret, gooseService);
    }

    public void addAuthentication(HttpServletResponse response, GooseAuthentication authentication) {
    final Goose goose = authentication.getDetails();
    response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(goose));
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