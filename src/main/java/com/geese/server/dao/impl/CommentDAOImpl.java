package com.geese.server.dao.impl;

import com.geese.server.dao.PostDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Post;
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
public class CommentDAOImpl implements PostDAO {
    private static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Post> findAll() {
        String query =
                "SELECT * FROM Post;";

        List<Post> posts = new ArrayList<Post>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Post post = new Post.Builder()
                        .id((int)row.get("id"))
                        .topicid((int) row.get("topicid"))
                        .authorid((int) row.get("authorid"))
                        .text((String) row.get("text"))
                        .score((int) row.get("score"))
                        .createdTime((LocalDateTime) row.get("createdTime"))
                        .createdTime((LocalDateTime) row.get("expireTime"))
                        .build();

                posts.add(post);
            }

            return posts;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Post: findAll returns no rows");
            return null;
        }
    }

    @Override
    public Post findOne(final int postId) {
        String query =
                "SELECT * FROM Post " +
                        "WHERE id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{postId}, new RowMapper<Post>() {
                @Override
                public Post mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Post.Builder()
                                .id(rs.getInt("id"))
                                .topicid(rs.getInt("topicid"))
                                .authorid(rs.getInt("authorid"))
                                .text(rs.getString("text"))
                                .score(rs.getInt("score"))
                                .createdTime(TimeHelper.fromDB(rs.getTimestamp("createdTime")))
                                .expireTime(TimeHelper.fromDB(rs.getTimestamp("expireTime")))
                                .build();
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Post: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int update(final Post updatedPost) {
        String query =
                "UPDATE Post " +
                        "topicid = ?, authorid = ?, text = ?," +
                        "score = ?, createdTime = ?, expireTime=?" +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedPost.getTopicid(),
                updatedPost.getAuthorid(),
                updatedPost.getText(),
                updatedPost.getScore(),
                TimeHelper.toDB(updatedPost.getCreatedTime()));
    }

    @Override
    public int delete(final int postId) {
        String query =
                "DELETE FROM Post " +
                        "WHERE id = ?";

        return jdbc.update(query, postId);
    }

    @Override
    public int create(final Post created) {
        String query = "INSERT INTO Post " +
                "(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getAuthorid());
    }
}
