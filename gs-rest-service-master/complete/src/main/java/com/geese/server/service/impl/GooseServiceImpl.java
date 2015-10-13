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
public class GooseServiceImpl implements GooseService {
    @Autowired
    GooseDAO gooseDAO;

    @Override
    public List<Goose> findAll() {
        return null;
    }

    @Override
    public Goose findOne(int id) {
        return null;
    }

    @Override
    public Goose save(Goose savedGoose) {
        return gooseDAO.save(savedGoose);
    }

    @Override
    public void delete(Goose deletedGoose) {

    }
}
