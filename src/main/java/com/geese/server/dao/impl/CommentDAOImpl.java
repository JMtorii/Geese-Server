package com.geese.server.dao.impl;

import com.geese.server.dao.CommentDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Comment;
import com.geese.server.domain.CommentVote;
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
public class CommentDAOImpl implements CommentDAO {
    private static final Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class);
    private static final String TABLE_NAME = "Comment";

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Comment> findAll(final int gooseid, final int postId) {
        String query =
                "SELECT * FROM " + TABLE_NAME + " AS a " +
                "LEFT JOIN (SELECT * FROM " + TABLE_NAME + "Vote WHERE gooseid = ?) AS b " +
                "ON (a.id = b.commentid) " +
                "LEFT JOIN (SELECT id AS authorid, name AS authorName FROM Goose) AS g " +
                "ON (a.authorid = g.authorid) " +
                "WHERE postid = ? " +
                "ORDER BY createdTime DESC";

        // Hopefully SQL optimizer takes the WHERE filter before joining Goose

        List<Comment> comments = new ArrayList<Comment>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query, gooseid, postId);

            for (Map row : rows) {
                Comment.Builder commentBuilder = new Comment.Builder()
                        .id((int)row.get("id"))
                        .postid((int) row.get("postid"))
                        .authorid((int) row.get("authorid"))
                        .text((String) row.get("text"))
                        .score((int) row.get("score"))
                        .createdTime(TimeHelper.fromDB((Timestamp)row.get("createdTime")))
                        .createdTime(TimeHelper.fromDB((Timestamp)row.get("expireTime")));

                if (row.get("gooseid") != null && row.get("commentid") != null && row.get("value") != null) {
                    commentBuilder.userVote(new CommentVote.Builder()
                        .gooseid((int) row.get("gooseid"))
                        .commentid((int) row.get("commentid"))
                        .value((int) row.get("value"))
                        .build()
                    );
                } else {
                    commentBuilder.userVote(new CommentVote.Builder()
                        .gooseid(gooseid)
                        .commentid((int)row.get("id"))
                        .value(0)
                        .build()
                    );
                }

                if (row.get("authorName") != null) {
                    commentBuilder.authorName((String) row.get("authorName"));
                }

                comments.add(commentBuilder.build());
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
                                .postid(rs.getInt("postid"))
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
                        "SET postid = ?, authorid = ?, `text` = ?," +
                        "score = ?, createdTime = ?, expireTime = ? " +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedComment.getPostid(),
                updatedComment.getAuthorid(),
                updatedComment.getText(),
                updatedComment.getScore(),
                TimeHelper.toDB(updatedComment.getCreatedTime()),
                TimeHelper.toDB(updatedComment.getExpireTime()),
                updatedComment.getId()
        );
    }

    @Override
    public int delete(final int commentId) {
        String query =
                "DELETE FROM CommentVote " +
                        "WHERE commentid = ?";

        jdbc.update(query, commentId);

        query =
                "DELETE FROM Comment " +
                        "WHERE id = ?";

        return jdbc.update(query, commentId);
    }

    @Override
    public int create(final Comment created) {
        String query = "INSERT INTO Comment " +
                    "(postid, authorid, `text`, score, createdTime, expireTime)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getPostid(),
                created.getAuthorid(),
                created.getText(),
                created.getScore(),
                TimeHelper.toDB(created.getCreatedTime()),
                TimeHelper.toDB(created.getExpireTime())
        );
    }
}
