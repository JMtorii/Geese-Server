package com.geese.server.service.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import com.geese.server.service.GooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Service
@SuppressWarnings("unused")
public class GooseServiceImpl implements GooseService {

    @Autowired
    GooseDAO gooseDAO;

    @Override
    public ArrayList<Goose> findAll() {
        return gooseDAO.findAll();
    }

    @Override
    public Goose findOne(String gooseId) {
        return gooseDAO.findOne(Integer.valueOf(gooseId));
    }

    @Override
    public Goose create(Goose savedGoose) {
        return gooseDAO.create(savedGoose);
    }

    @Override
    public Goose update(String gooseId, Goose updatedGoose) {
        // TODO: check whether gooseId matches updatedGoose.gooseId
        return gooseDAO.update(updatedGoose);
    }

    @Override
    public Goose delete(String gooseId) {
        return gooseDAO.delete(Integer.valueOf(gooseId));
    }
}
