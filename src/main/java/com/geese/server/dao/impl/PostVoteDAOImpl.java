package com.geese.server.dao.impl;

import com.geese.server.dao.PostDAO;
import com.geese.server.dao.PostVoteDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Post;
import com.geese.server.domain.PostVote;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecrothers on 2015-11-08.
 */
@Repository
public class PostVoteDAOImpl implements PostVoteDAO {
    private static final Logger logger = LoggerFactory.getLogger(PostVoteDAOImpl.class);
    private static final String TABLE_NAME = "PostVote";

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<PostVote> findAll(final int flockid) {
        StringBuilder query = new StringBuilder("SELECT * FROM " + TABLE_NAME);

        if (flockid > 0) {
            query.append(" WHERE flockid = ?");
        }

        List<PostVote> postVotes = new ArrayList<PostVote>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query.toString(), flockid);

            for (Map row : rows) {
                PostVote postVote = new PostVote.Builder()
                        .gooseid((int) row.get("gooseid"))
                        .postid((int) row.get("postid"))
                        .value((int) row.get("value"))
                        .build();

                postVotes.add(postVote);
            }

            return postVotes;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("PostVote: findAll returns no rows");
            return null;
        }
    }

    @Override
    public PostVote findOne(final int gooseid, final int postid) {
        String query =
                "SELECT * FROM " + TABLE_NAME + " " +
                        "WHERE gooseid = ? AND postid = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{gooseid, postid}, new RowMapper<PostVote>() {
                @Override
                public PostVote mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new PostVote.Builder()
                                .gooseid(rs.getInt("gooseid"))
                                .postid(rs.getInt("postid"))
                                .value(rs.getInt("value"))
                                .build();
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.warn("PostVote: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int  update(final PostVote updatedPostVote) {
        String query =
                "UPDATE " + TABLE_NAME + " " +
                        "SET gooseid = ?, postid = ?, value = ?," +
                        "WHERE gooseid = ? AND postid = ?";

        return jdbc.update(query,
                updatedPostVote.getGooseId(),
                updatedPostVote.getPostId(),
                updatedPostVote.getValue(),
                updatedPostVote.getGooseId(),
                updatedPostVote.getPostId()
        );
    }

    @Override
    public int delete(final int gooseId, final int postId) {
        String query =
                "DELETE FROM " + TABLE_NAME + " " +
                        "WHERE gooseid = ? AND postid = ?";

        return jdbc.update(query, gooseId, postId);
    }

    @Override
    public int create(final PostVote created) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(gooseid, postid, value) " +
                "VALUES (?, ?, ?)";

        return jdbc.update(query,
                created.getGooseId(),
                created.getPostId(),
                created.getValue()
        );
    }

    //TODO instead of overwriting old vote score for value = 0, why not just delete the PostVote
    @Override
    public int createOrOverwrite(final PostVote newPost) {
        String query = "INSERT INTO " + TABLE_NAME + " " +
                "(gooseid, postid, value) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE value = VALUES(value)";

        return jdbc.update(query,
                newPost.getGooseId(),
                newPost.getPostId(),
                newPost.getValue()
        );
    }
}
