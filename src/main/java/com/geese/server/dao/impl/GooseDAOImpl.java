package com.geese.server.dao.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
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

        List<Goose> geese = new ArrayList<>();

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
            logger.warn("Goose: findAll returns no rows");
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
    public Goose findByEmail(final String email) {
        String sqlString =
                "SELECT * FROM Goose " +
                        "WHERE email = ?;";

        try {
            return jdbc.queryForObject(sqlString, new Object[]{email}, new RowMapper<Goose>() {
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
            logger.warn("Goose: findByEmail returns no rows");
            return null;
        }
    }

    @Override
    public int create(final Goose createdGoose) {
        String sqlString =
                "INSERT INTO Goose (name, email, verified)" +
                "VALUES (?, ?, ?);";

        return jdbc.update(
                sqlString,
                createdGoose.getName(),
                createdGoose.getEmail(),
                createdGoose.getVerified()
        );
    }

    @Override
    public int update(final Goose updatedGoose) {
        String sqlString =
                "UPDATE Goose " +
                "SET name = ?, email = ?, verified = ?" +
                "WHERE id = ?";

        return jdbc.update(
                sqlString,
                updatedGoose.getName(),
                updatedGoose.getEmail(),
                updatedGoose.getVerified(),
                updatedGoose.getId()
        );
    }

    @Override
    public int delete(final int gooseId) {
        String sqlString =
                "DELETE FROM Goose " +
                "WHERE id = ?";

        return jdbc.update(sqlString, gooseId);
    }
}
