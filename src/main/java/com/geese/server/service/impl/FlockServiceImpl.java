package com.geese.server.service.impl;

import com.geese.server.GooseAuthentication;
import com.geese.server.dao.FlockDAO;
import com.geese.server.domain.Flock;
import com.geese.server.service.FlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
@Service
public class FlockServiceImpl implements FlockService {

    private static final Logger logger = LoggerFactory.getLogger(FlockService.class);

    @Autowired
    private FlockDAO flockDAO;

    @Override
    public int delete(String flockId) {
        return flockDAO.delete(Integer.valueOf(flockId));
    }

    @Override
    public List<Flock> findAll() {
        return flockDAO.findAll();
    }

    @Override
    public Flock findOne(String flockId) {
        return flockDAO.findOne(Integer.valueOf(flockId));
    }


    @Override
    public int create(Flock saved) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int gooseId = auth.getDetails().getId();
        saved.setAuthorid(gooseId);
        return flockDAO.create(saved);
    }

    @Override
    // TODO: Why are we sending the flockId
    public int update(String flockId, Flock updatedFlock) {
        //GooseAuthentication auth = SecurityContextHolder.getContext().getAuthentication();
        return flockDAO.update(updatedFlock);
    }

    @Override
    public List<Flock> getNearbyFlocks(float latitude, float longitude) {
        List<Flock> nearbyFlocks = flockDAO.getNearbyFlocks(latitude, longitude);
        List<Flock> favouritedFlocks = getFavourited();

        if (nearbyFlocks != null && favouritedFlocks != null) {
            // I'm so sorry for this. This is horrible
            for (Flock favourited : favouritedFlocks) {
                for (Flock nearbyFlock : nearbyFlocks) {
                    if (favourited.getId() == nearbyFlock.getId()) {
                        nearbyFlock.setFavourited(true);
                        break;
                    }
                }
            }
        }
        
        return nearbyFlocks;
    }

    @Override
    public List<Flock> getFavourited() {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int gooseId = auth.getDetails().getId();
        return flockDAO.getFavourited(gooseId);
    }

    @Override
    public int joinFlock(int flockId) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int gooseId = auth.getDetails().getId();
        return flockDAO.joinFlock(gooseId, flockId);
    }

    @Override
    public int unjoinFlock(int flockId) {
        GooseAuthentication auth = (GooseAuthentication) SecurityContextHolder.getContext().getAuthentication();
        int gooseId = auth.getDetails().getId();
        return flockDAO.unjoinFlock(gooseId, flockId);
    }
}
