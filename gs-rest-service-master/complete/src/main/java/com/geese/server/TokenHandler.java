package com.geese.server;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.impl.GooseServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Ni on 2015-11-18.
 */
@Component
public final class TokenHandler {

    private final String secret;

    @Autowired
    @Qualifier("gooseServiceImpl")
    private GooseService gooseService;

    public TokenHandler() {
        super();
        this.secret = "someSecret";
    }

    public TokenHandler(String secret) {
        super();
        this.secret = secret;
    }

    public Goose parseUserFromToken(String token) {
        String username = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
        return gooseService.loadUserByUsername(username);
        }

    public String createTokenForUser(Goose goose) {
        return Jwts.builder()
        .setSubject(goose.getName())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
        }
    }