package com.geese.server;

import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TokenHandler.class);

    public TokenHandler() {
        super();
        this.secret = "someSecret";
    }

    public Goose parseUserFromToken(String token) {
        String username = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
        logger.info("Parsed user: " + username);
        return gooseService.loadUserByUsername(username);
    }

    public String createTokenForUser(Goose goose) {
        logger.info("Created token for user: " + goose.getUsername());
        return Jwts.builder()
        .setSubject(goose.getUsername())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
    }
}