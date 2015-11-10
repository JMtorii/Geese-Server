package com.geese.server.dao.impl;

import com.geese.server.dao.EventDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Repository
public class EventDAOImpl implements EventDAO {
    private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Event> findAll() {
        String query =
                "SELECT * FROM Event;";

        List<Event> events = new ArrayList<Event>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Event event = new Event.Builder()
                        .id((int)row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .name((String) row.get("name"))
                        .description((String) row.get("description"))
                        .latitude((float) row.get("latitude"))
                        .longitude((float) row.get("longitude"))
                        .radius((double) row.get("radius"))
                        .score((int) row.get("score"))
                        .createdTime((LocalDateTime) row.get("createdTime"))
                        .expireTime((LocalDateTime) row.get("expireTime"))
                        .build();

                events.add(event);
            }

            return events;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Event: findAll returns no rows");
            return null;
        }
    }

    @Override
    public Event findOne(final int eventId) {
        String query =
                "SELECT * FROM Event " +
                        "WHERE id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{eventId}, new RowMapper<Event>() {
                @Override
                public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Event.Builder()
                                .id(rs.getInt("id"))
                                .authorid(rs.getInt("authorid"))
                                .name(rs.getString("name"))
                                .description(rs.getString("description"))
                                .latitude(rs.getFloat("latitude"))
                                .longitude(rs.getFloat("longitude"))
                                .radius(rs.getDouble("radius"))
                                .score(rs.getInt("score"))
                                .createdTime(TimeHelper.fromDB(rs.getTimestamp("createdTime")))
                                .expireTime(TimeHelper.fromDB(rs.getTimestamp("expireTime")))
                                .build();
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Event: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int update(final Event updatedEvent) {
        String query =
                "UPDATE Event " +
                        "SET authorid = ?, name = ?, description = ?, latitude = ?," +
                        "longitude = ?, radius = ?, score = ?, createdTime = ?, expireTime = ?" +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedEvent.getAuthorid(),
                updatedEvent.getName(),
                updatedEvent.getDescription(),
                updatedEvent.getLatitude(),
                updatedEvent.getLongitude(),
                updatedEvent.getRadius(),
                updatedEvent.getScore(),
                TimeHelper.toDB(updatedEvent.getCreatedTime()),
                TimeHelper.toDB(updatedEvent.getExpireTime()),
                updatedEvent.getId());
    }

    @Override
    public int delete(final int eventId) {
        String query =
                "DELETE FROM Event " +
                        "WHERE id = ?";

        return jdbc.update(query, eventId);
    }

    @Override
    public int create(final Event created) {
        String query = "INSERT INTO Event " +
                "(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getAuthorid(),
                created.getName(),
                created.getDescription(),
                created.getLatitude(),
                created.getLongitude(),
                created.getRadius(),
                created.getScore(),
                TimeHelper.toDB(created.getCreatedTime()), //TODO use client or server version? Timestamp.valueOf(LocalDateTime.now(ZoneId.ofOffset("", ZoneOffset.UTC)))
                TimeHelper.toDB(created.getExpireTime())
        );
    }
}
