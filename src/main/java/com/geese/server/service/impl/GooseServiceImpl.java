package com.geese.server.service.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import com.geese.server.service.TokenService;
import com.geese.server.service.util.HashingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Service
@SuppressWarnings("unused")
public class GooseServiceImpl implements GooseService {

    private static final Logger logger = LoggerFactory.getLogger(GooseService.class);

    @Autowired
    GooseDAO gooseDAO;

    public List<Goose> findAll() {
        return gooseDAO.findAll();
    }

    public Goose findOne(String gooseId) {
        return gooseDAO.findOne(Integer.valueOf(gooseId));
    }

    public Goose findByEmail(String email) {
        return gooseDAO.findByEmail(email);
    }

    @Override
    public Goose loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public int create(Goose createdGoose) {
        SecureRandom random = new SecureRandom();
        byte randomBytes[] = new byte[32];
        random.nextBytes(randomBytes);
        Goose.Builder newGoose = new Goose.Builder(0, createdGoose.getName(), createdGoose.getEmail(), false);

        try {
            String salt = HashingAlgorithm.sha256(randomBytes);
            newGoose.salt(salt);
            byte[] saltBytes = DatatypeConverter.parseHexBinary(salt);
            byte[] passwordBytes = createdGoose.getPassword().getBytes("UTF-8"); // UTF-8 or 16?
            byte[] saltedPasswordBytes = ByteBuffer.allocate(saltBytes.length + passwordBytes.length).put(saltBytes).put(passwordBytes).array();
            String password = HashingAlgorithm.sha256(saltedPasswordBytes);
            newGoose.password(password);
            logger.debug(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gooseDAO.create(newGoose.build());
    }

    public int update(String gooseId, Goose updatedGoose) {
        // TODO: check whether gooseId matches updatedGoose.gooseId
        return gooseDAO.update(updatedGoose);
    }

    public int delete(String gooseId) {
        return gooseDAO.delete(Integer.valueOf(gooseId));
    }
}
