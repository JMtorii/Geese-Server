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
    private static final String TABLE_NAME = "Post"; // NOTE: This is likely to change

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Event> findAll() {
        String query =
                "SELECT * FROM " + TABLE_NAME + ";";

        List<Event> topics = new ArrayList<Event>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Event topic = new Event.Builder()
                        .id((int)row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .title((String) row.get("title"))
                        .description((String) row.get("description"))
                        .pinned((int) row.get("pinned"))
                        .score((int) row.get("score"))
                        .createdTime((LocalDateTime) row.get("createdTime"))
                        .startTime((LocalDateTime) row.get("startTime"))
                        .endTime((LocalDateTime) row.get("endTime"))
                        .build();

                topics.add(topic);
            }

            return topics;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Event: findAll returns no rows");
            return null;
        }
    }

    @Override
    public Event findOne(final int topicId) {
        String query =
                "SELECT * FROM " + TABLE_NAME + " " +
                        "WHERE id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{topicId}, new RowMapper<Event>() {
                @Override
                public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Event.Builder()
                                .id(rs.getInt("id"))
                                .authorid(rs.getInt("authorid"))
                                .title(rs.getString("title"))
                                .description(rs.getString("description"))
                                .pinned(rs.getInt("pinned"))
                                .score(rs.getInt("score"))
                                .createdTime(TimeHelper.fromDB(rs.getTimestamp("createdTime")))
                                .startTime(TimeHelper.fromDB(rs.getTimestamp("startTime")))
                                .endTime(TimeHelper.fromDB(rs.getTimestamp("endTime")))
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
                "UPDATE " + TABLE_NAME + " " +
                        "SET flockid = ?, authorid = ?, title = ?," +
                        "description = ?, pinned = ?, score = ?, createdTime = ?, " +
                        "startTime = ?, endTime = ? " +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedEvent.getFlockid(),
                updatedEvent.getAuthorid(),
                updatedEvent.getTitle(),
                updatedEvent.getDescription(),
                updatedEvent.getPinned(),
                updatedEvent.getScore(),
                TimeHelper.toDB(updatedEvent.getCreatedTime()),
                TimeHelper.toDB(updatedEvent.getStartTime()),
                TimeHelper.toDB(updatedEvent.getEndTime()),
                updatedEvent.getId()
        );
    }

    @Override
    public int delete(final int topicId) {
        String query =
                "DELETE FROM " + TABLE_NAME + " " +
                        "WHERE id = ?";

        return jdbc.update(query, topicId);
    }

    @Override
    public int create(final Event created) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(flockid, authorid, title, description, pinned, score, createdTime, startTime, endTime)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getFlockid(),
                created.getAuthorid(),
                created.getTitle(),
                created.getDescription(),
                created.getPinned(),
                created.getScore(),
                TimeHelper.toDB(created.getCreatedTime()),
                TimeHelper.toDB(created.getStartTime()),
                TimeHelper.toDB(created.getEndTime())
        );
    }
}
