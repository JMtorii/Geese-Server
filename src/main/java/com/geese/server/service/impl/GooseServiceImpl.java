package com.geese.server.service.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Service
@SuppressWarnings("unused")
public class GooseServiceImpl implements GooseService {

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
        return gooseDAO.create(createdGoose);
    }

    public int update(String gooseId, Goose updatedGoose) {
        // TODO: check whether gooseId matches updatedGoose.gooseId
        return gooseDAO.update(updatedGoose);
    }

    public int delete(String gooseId) {
        return gooseDAO.delete(Integer.valueOf(gooseId));
    }
}
