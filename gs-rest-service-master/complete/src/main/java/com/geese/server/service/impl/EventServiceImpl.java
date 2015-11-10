package com.geese.server.service.impl;

import com.geese.server.dao.EventDAO;
import com.geese.server.domain.Event;
import com.geese.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDAO eventDAO;

    @Override
    public int delete(String eventId) {
        return eventDAO.delete(Integer.valueOf(eventId));
    }

    @Override
    public List<Event> findAll() {
        return eventDAO.findAll();
    }

    @Override
    public Event findOne(String eventId) {
        return eventDAO.findOne(Integer.valueOf(eventId));
    }


    @Override
    public int create(Event saved) {
        return eventDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the eventId
    public int update(String eventId, Event updatedEvent) {
        return eventDAO.update(updatedEvent);
    }
}
