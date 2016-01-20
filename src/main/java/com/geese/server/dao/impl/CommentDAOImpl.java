package com.geese.server.dao.impl;

import com.geese.server.dao.CommentDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Comment;
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
public class CommentDAOImpl implements CommentDAO {
    private static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Comment> findAll() {
        String query =
                "SELECT * FROM Comment;";

        List<Comment> comments = new ArrayList<Comment>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Comment comment = new Comment.Builder()
                        .id((int)row.get("id"))
                        .commentid((int) row.get("commentid"))
                        .authorid((int) row.get("authorid"))
                        .text((String) row.get("text"))
                        .score((int) row.get("score"))
                        .createdTime((LocalDateTime) row.get("createdTime"))
                        .createdTime((LocalDateTime) row.get("expireTime"))
                        .build();

                comments.add(comment);
            }

            return comments;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Comment: findAll returns no rows");
            return null;
        }
    }

    @Override
    public Comment findOne(final int commentId) {
        String query =
                "SELECT * FROM Comment " +
                        "WHERE id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{commentId}, new RowMapper<Comment>() {
                @Override
                public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Comment.Builder()
                                .id(rs.getInt("id"))
                                .commentid(rs.getInt("commentid"))
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
            logger.warn("Comment: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int update(final Comment updatedComment) {
        String query =
                "UPDATE Comment " +
                        "commentid = ?, authorid = ?, text = ?," +
                        "score = ?, createdTime = ?, expireTime=?" +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedComment.getCommentid(),
                updatedComment.getAuthorid(),
                updatedComment.getText(),
                updatedComment.getScore(),
                TimeHelper.toDB(updatedComment.getCreatedTime()));
    }

    @Override
    public int delete(final int commentId) {
        String query =
                "DELETE FROM Comment " +
                        "WHERE id = ?";

        return jdbc.update(query, commentId);
    }

    @Override
    public int create(final Comment created) {
        String query = "INSERT INTO Comment " +
                "(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getAuthorid());
    }
}
