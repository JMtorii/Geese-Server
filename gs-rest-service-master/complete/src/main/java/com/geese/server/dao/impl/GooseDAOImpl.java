package com.geese.server.dao.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Repository
@SuppressWarnings("unused")
public class GooseDAOImpl implements GooseDAO {
    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public ArrayList<Goose> findAll() {
        return null;
    }

    @Override
    public Goose findOne(final int gooseId) {
        String sqlString =
                "SELECT * FROM Goose " +
                "WHERE id = ?;";

        return jdbc.queryForObject(sqlString, new Object[]{gooseId}, new RowMapper<Goose>() {
            @Override
            public Goose mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        });
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


//        return jdbc.update(
//                "INSERT INTO Goose (email, password) " +
//                        "values (?, ?)",
//                savedGoose.getEmail(), savedGoose.getPassword()
//        );
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
