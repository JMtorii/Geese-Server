package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.domain.Goose;
import com.geese.server.service.TokenService;
import com.geese.server.service.GooseService;
import com.geese.server.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Service
@SuppressWarnings("unused")
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    @Qualifier("gooseServiceImpl")
    private GooseService gooseService;

    @Autowired
    private TokenService tokenService;

    public String sha256(String hexString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(DatatypeConverter.parseHexBinary(hexString));
        return DatatypeConverter.printHexBinary(md.digest());
    }

    public String sha256(byte[] rawBytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(rawBytes);
        return DatatypeConverter.printHexBinary(md.digest());
    }

    @Override
    public String Login(String email, String password) {
        // TODO : Tighten up authentication?
        Goose goose = gooseService.loadUserByUsername(email);

        if (goose == null) {
            return "";
        }

        String passwordAttempt = "";
        try {
            byte[] saltBytes = DatatypeConverter.parseHexBinary(goose.getSalt());
            byte[] passwordBytes = password.getBytes("UTF-8"); // UTF-8 or 16?
            byte[] saltedPasswordBytes = ByteBuffer.allocate(saltBytes.length + passwordBytes.length).put(saltBytes).put(passwordBytes).array();
            passwordAttempt = sha256(saltedPasswordBytes);
            logger.debug(passwordAttempt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (passwordAttempt.equals(goose.getPassword())) {
            logger.info("Authenticated password for " + goose.getUsername());
            GooseAuthentication auth = new GooseAuthentication(goose);
            return tokenService.getToken(auth);
        }

        return "";

    }

    @Override
    public String LoginFromFacebook(String token) {
        return "";
    };
}
