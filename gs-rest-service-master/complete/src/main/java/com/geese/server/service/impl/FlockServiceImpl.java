package com.geese.server.service.impl;

import com.geese.server.dao.FlockDAO;
import com.geese.server.domain.Flock;
import com.geese.server.service.FlockService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
@Service
public class FlockServiceImpl implements FlockService {
    private FlockDAO flockDAO;

    // https://github.com/pkainulainen/java-advent-2014/blob/master/src/main/java/com/javaadvent/bootrest/todo/MongoDbTodoService.java
    @Override
    public void delete(Flock deleted) {
        Flock deletedFlock = findOne(deleted.getId());
        flockDAO.delete(deletedFlock);
    }

    @Override
    public List<Flock> findAll() {
        return null;
    }

    @Override
    public Flock findOne(int id) {
        return null;
    }

    @Override
    public Flock save(Flock saved) {
        return null;
    }

    @Override
    public Flock update(Flock flock) {
        return null;
    }
}
