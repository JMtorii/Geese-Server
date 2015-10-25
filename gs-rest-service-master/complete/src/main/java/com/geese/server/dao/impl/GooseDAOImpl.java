package com.geese.server.dao.impl;

import com.geese.server.dao.GooseDAO;
import com.geese.server.domain.Goose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    public Goose findOne(int gooseId) {
        return null;
    }

    @Override
    public Goose create(final Goose createdGoose) {
        String insertString = "INSERT INTO Goose " +
                "(name, email, verified)" +
                "VALUES (?, ?, ?)";

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
    public Goose update(Goose updatedGoose) {
        return null;
    }

    @Override
    public Goose delete(int gooseId) {
        return null;
    }
}
