package com.geese.server.service.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Goose> findAll() {
        return gooseDAO.findAll();
    }

    @Override
    public Goose findOne(String gooseId) {
        return gooseDAO.findOne(Integer.valueOf(gooseId));
    }

    @Override
    public int create(Goose createdGoose) {
        return gooseDAO.create(createdGoose);
    }

    @Override
    public int update(String gooseId, Goose updatedGoose) {
        // TODO: check whether gooseId matches updatedGoose.gooseId
        return gooseDAO.update(updatedGoose);
    }

    @Override
    public int delete(String gooseId) {
        return gooseDAO.delete(Integer.valueOf(gooseId));
    }
}
