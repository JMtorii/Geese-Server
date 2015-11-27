package com.geese.server;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by Ni on 2015-11-18.
 */
public final class TokenHandler {

    private final String secret;
    private final GooseService gooseService;

    public TokenHandler(String secret, GooseService gooseService) {
        this.secret = secret;
        this.gooseService = gooseService;
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