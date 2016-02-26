package com.geese.server.dao.impl;

import com.geese.server.dao.CommentVoteDAO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Repository
public class CommentVoteDAOImpl implements CommentVoteDAO {
    private static final Logger logger = LoggerFactory.getLogger(CommentVoteDAOImpl.class);
    private static final String TABLE_NAME = "CommentVote";

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<CommentVote> findAll(final int flockid) {
        StringBuilder query = new StringBuilder("SELECT * FROM " + TABLE_NAME);

        if (flockid > 0) {
            query.append(" WHERE flockid = ?");
        }

        List<CommentVote> commentVotes = new ArrayList<CommentVote>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query.toString(), flockid);

            for (Map row : rows) {
                CommentVote commentVote = new CommentVote.Builder()
                        .gooseid((int) row.get("gooseid"))
                        .commentid((int) row.get("commentid"))
                        .value((int) row.get("value"))
                        .build();

                commentVotes.add(commentVote);
            }

            return commentVotes;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("CommentVote: findAll returns no rows");
            return null;
        }
    }

    @Override
    public CommentVote findOne(final int gooseid, final int commentid) {
        String query =
                "SELECT * FROM " + TABLE_NAME + " " +
                        "WHERE gooseid = ? AND commentid = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{gooseid, commentid}, new RowMapper<CommentVote>() {
                @Override
                public CommentVote mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new CommentVote.Builder()
                                .gooseid(rs.getInt("gooseid"))
                                .commentid(rs.getInt("commentid"))
                                .value(rs.getInt("value"))
                                .build();
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.warn("CommentVote: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int  update(final CommentVote updatedCommentVote) {
        String query =
                "UPDATE " + TABLE_NAME + " " +
                        "SET gooseid = ?, commentid = ?, value = ?," +
                        "WHERE gooseid = ? AND commentid = ?";

        return jdbc.update(query,
                updatedCommentVote.getGooseId(),
                updatedCommentVote.getCommentId(),
                updatedCommentVote.getValue(),
                updatedCommentVote.getGooseId(),
                updatedCommentVote.getCommentId()
        );
    }

    @Override
    public int delete(final int gooseId, final int commentId) {
        String query =
                "DELETE FROM " + TABLE_NAME + " " +
                        "WHERE gooseid = ? AND commentid = ?";

        return jdbc.update(query, gooseId, commentId);
    }

    @Override
    public int create(final CommentVote created) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(gooseid, commentid, value) " +
                "VALUES (?, ?, ?)";

        return jdbc.update(query,
                created.getGooseId(),
                created.getCommentId(),
                created.getValue()
        );
    }

    //TODO instead of overwriting old vote score for value = 0, why not just delete the CommentVote
    @Override
    public int createOrOverwrite(final CommentVote newComment) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(gooseid, commentid, value) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE value = VALUES(value)";

        return jdbc.update(query,
                newComment.getGooseId(),
                newComment.getCommentId(),
                newComment.getValue()
        );
    }
}
