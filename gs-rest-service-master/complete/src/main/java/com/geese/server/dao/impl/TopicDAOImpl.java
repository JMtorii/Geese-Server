package com.geese.server.dao.impl;

import com.geese.server.dao.TopicDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Topic;
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
public class TopicDAOImpl implements TopicDAO {
    private static final Logger logger = LoggerFactory.getLogger(TopicDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Topic> findAll() {
        String query =
                "SELECT * FROM Topic;";

        List<Topic> topics = new ArrayList<Topic>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Topic topic = new Topic.Builder()
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

                topics.add(topic);
            }

            return topics;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Topic: findAll returns no rows");
            return null;
        }
    }

    @Override
    public Topic findOne(final int topicId) {
        String query =
                "SELECT * FROM Topic " +
                        "WHERE id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{topicId}, new RowMapper<Topic>() {
                @Override
                public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Topic.Builder()
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
            logger.warn("Topic: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int update(final Topic updatedTopic) {
        String query =
                "UPDATE Topic " +
                        "SET authorid = ?, name = ?, description = ?, latitude = ?," +
                        "longitude = ?, radius = ?, score = ?, createdTime = ?, expireTime = ?" +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedTopic.getAuthorid(),
                updatedTopic.getName(),
                updatedTopic.getDescription(),
                updatedTopic.getLatitude(),
                updatedTopic.getLongitude(),
                updatedTopic.getRadius(),
                updatedTopic.getScore(),
                TimeHelper.toDB(updatedTopic.getCreatedTime()),
                TimeHelper.toDB(updatedTopic.getExpireTime()),
                updatedTopic.getId());
    }

    @Override
    public int delete(final int topicId) {
        String query =
                "DELETE FROM Topic " +
                        "WHERE id = ?";

        return jdbc.update(query, topicId);
    }

    @Override
    public int create(final Topic created) {
        String query = "INSERT INTO Topic " +
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
