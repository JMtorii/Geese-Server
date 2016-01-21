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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Repository
public class PostDAOImpl implements PostDAO {
    private static final Logger logger = LoggerFactory.getLogger(PostDAOImpl.class);
    private static final String TABLE_NAME = "Post";

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Post> findAll(final int flockid) {
        String query =
                "SELECT * FROM " + TABLE_NAME + " WHERE flockid = ?";

        List<Post> posts = new ArrayList<Post>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query, flockid);

            for (Map row : rows) {
                Post post = new Post.Builder()
                        .id((int)row.get("id"))
                        .flockid((int) row.get("flockid"))
                        .authorid((int) row.get("authorid"))
                        .title((String) row.get("title"))
                        .description((String) row.get("description"))
                        .pinned((boolean) row.get("pinned"))
                        .score((int) row.get("score"))
                        .createdTime(TimeHelper.fromDB((Timestamp)row.get("createdTime")))
                        .startTime(TimeHelper.fromDB((Timestamp)row.get("startTime")))
                        .endTime(TimeHelper.fromDB((Timestamp)row.get("endTime")))
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
                "SELECT * FROM " + TABLE_NAME + " " +
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
                                .authorid(rs.getInt("authorid"))
                                .title(rs.getString("title"))
                                .description(rs.getString("description"))
                                .pinned(rs.getBoolean("pinned"))
                                .score(rs.getInt("score"))
                                .startTime(TimeHelper.fromDB(rs.getTimestamp("startTime")))
                                .endTime(TimeHelper.fromDB(rs.getTimestamp("endTime")))
                                .createdTime(TimeHelper.fromDB(rs.getTimestamp("createdTime")))
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
                "UPDATE " + TABLE_NAME + " " +
                        "SET flockid = ?, authorid = ?, title = ?," +
                        "description = ?, pinned = ?, score = ?, createdTime = ?, startTime = ?, endTime = ? " +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedPost.getFlockid(),
                updatedPost.getAuthorid(),
                updatedPost.getTitle(),
                updatedPost.getDescription(),
                updatedPost.getPinned(),
                updatedPost.getScore(),
                TimeHelper.toDB(updatedPost.getStartTime()),
                TimeHelper.toDB(updatedPost.getEndTime()),
                TimeHelper.toDB(updatedPost.getCreatedTime()),
                updatedPost.getId()
        );
    }

    @Override
    public int delete(final int postId) {
        String query =
                "DELETE FROM " + TABLE_NAME + " " +
                        "WHERE id = ?";

        return jdbc.update(query, postId);
    }

    @Override
    public int create(final Post created) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(flockid, authorid, title, description, pinned, score, startTime, endTime, createdTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getFlockid(),
                created.getAuthorid(),
                created.getTitle(),
                created.getDescription(),
                created.getPinned(),
                created.getScore(),
                TimeHelper.toDB(created.getStartTime()),
                TimeHelper.toDB(created.getEndTime()),
                TimeHelper.toDB(created.getCreatedTime())
        );
    }
}
