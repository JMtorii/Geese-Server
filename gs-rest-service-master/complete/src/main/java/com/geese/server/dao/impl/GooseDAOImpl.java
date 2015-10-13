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
import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
@Repository
public class GooseDAOImpl implements GooseDAO {
    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Goose> findAll() {
        return null;
    }

    @Override
    public Goose findOne(int id) {
        return null;
    }

    @Override
    public Goose save(final Goose savedGoose) {
        String insertString = "INSERT INTO Goose " +
                "(email, password)" +
                "VALUES (?, ?)";

        boolean success = jdbc.execute(insertString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, savedGoose.getEmail());
                ps.setString(2, savedGoose.getPassword());

                return ps.execute();
            }
        });

        return savedGoose;


//        return jdbc.update(
//                "INSERT INTO Goose (email, password) " +
//                        "values (?, ?)",
//                savedGoose.getEmail(), savedGoose.getPassword()
//        );
    }

    @Override
    public void delete(Goose deletedGoose) {

    }
}
