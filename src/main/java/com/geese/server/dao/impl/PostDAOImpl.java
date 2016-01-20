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
public class PostDAOImpl implements TopicDAO {
    private static final Logger logger = LoggerFactory.getLogger(PostDAOImpl.class);
    private static final String TABLE_NAME = "Topic";

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Topic> findAll() {
        String query =
                "SELECT * FROM " + " TABLE_NAME " + ";";

        List<Topic> topics = new ArrayList<Topic>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Topic topic = new Topic.Builder()
                        .id((int)row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .title((String) row.get("title"))
                        .description((String) row.get("description"))
                        .pinned((int) row.get("pinned"))
                        .score((int) row.get("score"))
                        .createdTime((LocalDateTime) row.get("createdTime"))
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
                "SELECT * FROM " + TABLE_NAME + " " +
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
                                .title(rs.getString("title"))
                                .description(rs.getString("description"))
                                .pinned(rs.getInt("pinned"))
                                .score(rs.getInt("score"))
                                .createdTime(TimeHelper.fromDB(rs.getTimestamp("createdTime")))
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
                "UPDATE " + TABLE_NAME + " " +
                        "SET flockid = ?, authorid = ?, title = ?," +
                        "description = ?, pinned = ?, score = ?, createdTime = ? " +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedTopic.getFlockid(),
                updatedTopic.getAuthorid(),
                updatedTopic.getTitle(),
                updatedTopic.getDescription(),
                updatedTopic.getPinned(),
                updatedTopic.getScore(),
                TimeHelper.toDB(updatedTopic.getCreatedTime()),
                updatedTopic.getId()
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
    public int create(final Topic created) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(flockid, authorid, title, description, pinned, score, createdTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getFlockid(),
                created.getAuthorid(),
                created.getTitle(),
                created.getDescription(),
                created.getPinned(),
                created.getScore(),
                TimeHelper.toDB(created.getCreatedTime())
        );
    }
}
