package com.geese.server.dao.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Repository
@SuppressWarnings("unused")
public class GooseDAOImpl implements GooseDAO {
    private static final Logger logger = LoggerFactory.getLogger(GooseDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Goose> findAll() {
        String sqlString =
                "SELECT * FROM Goose;";

        List<Goose> geese = new ArrayList<Goose>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(sqlString);

            for (Map row : rows) {
                Goose goose = new Goose.Builder(
                        (int) row.get("id"),
                        (String) row.get("name"),
                        (String) row.get("email"),
                        (boolean) row.get("verified")
                )
                        .password((String) row.get("password"))
                        .salt((String) row.get("salt"))
                        .build();

                geese.add(goose);
            }

            return geese;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Goose: findOne returns no rows");
            return null;
        }
    }

    @Override
    public Goose findOne(final int gooseId) {
        String sqlString =
                "SELECT * FROM Goose " +
                "WHERE id = ?;";

        try {
            return jdbc.queryForObject(sqlString, new Object[]{gooseId}, new RowMapper<Goose>() {
                @Override
                public Goose mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Goose.Builder(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getBoolean("verified")
                        )
                                .password(rs.getString("password"))
                                .salt(rs.getString("salt"))
                                .build();
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Goose: findOne returns no rows");
            return null;
        }
    }

    @Override
    public Goose create(final Goose createdGoose) {
        String insertString =
                "INSERT INTO Goose (name, email, verified)" +
                "VALUES (?, ?, ?);";

        boolean success = jdbc.execute(insertString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, createdGoose.getName());
                ps.setString(2, createdGoose.getEmail());
                ps.setBoolean(3, createdGoose.getVerified());

                return ps.execute();
            }
        });

        return createdGoose;
    }

    @Override
    public Goose update(final Goose updatedGoose) {
        return null;
    }

    @Override
    public Goose delete(final int gooseId) {
        return null;
    }
}
