package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.domain.Goose;
import com.geese.server.service.TokenService;
import com.geese.server.service.GooseService;
import com.geese.server.service.LoginService;
import com.geese.server.service.util.FacebookGraphResponse;
import com.geese.server.service.util.HashingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Service
@SuppressWarnings("unused")
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String FACEBOOK_GRAPH_API_ME = "https://graph.facebook.com/me?fields=name,email&access_token=";

    @Autowired
    @Qualifier("gooseServiceImpl")
    private GooseService gooseService;

    @Autowired
    private TokenService tokenService;

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
            passwordAttempt = HashingAlgorithm.sha256(saltedPasswordBytes);
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
        try {
            String url = FACEBOOK_GRAPH_API_ME + token;
            RestTemplate restTemplate = new RestTemplate();

            String fbName, fbEmail;
            try {
                FacebookGraphResponse response = restTemplate.getForObject(url, FacebookGraphResponse.class);
                fbName = response.getName();
                fbEmail = response.getEmail();
            } catch (Throwable ex) {
                throw new RuntimeException("FB request failure");
            }

            //TODO: Tighten access token failure cases? could get error:message from response for debug
            if (fbName == "" || fbEmail == "") {
                return "";
            }

            Goose goose = gooseService.findByEmail(fbEmail);
            //TODO: Instead of a random password, possibly use a flag for fb login only accounts
            if (goose == null) {
                Goose.Builder newFBGoose = new Goose.Builder(0, fbName, fbEmail, true);
                SecureRandom random = new SecureRandom();
                byte randomBytes[] = new byte[32];
                random.nextBytes(randomBytes);
                String randomPassword = HashingAlgorithm.sha256(randomBytes);
                newFBGoose.password(randomPassword);

                gooseService.create(newFBGoose.build());
                goose = gooseService.findByEmail(fbEmail);
            }

            //if goose is still null something happened during account creation and didn't throw
            if (goose == null) {
                throw new RuntimeException("goose creation from FB failed");
            }

            logger.info("Authenticated FB token for " + goose.getUsername());
            GooseAuthentication auth = new GooseAuthentication(goose);
            return tokenService.getToken(auth);

        } catch (Throwable ex) {
            throw new RuntimeException("failed login", ex);
        }
    };
}
