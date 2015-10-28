package com.geese.server.dao.impl;

import com.geese.server.dao.FlockDAO;
import com.geese.server.domain.Flock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
@Repository
public class FlockDAOImpl implements FlockDAO {
    private static Logger LOGGER = LoggerFactory.getLogger(FlockDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public int delete(int flockId) {
        return 0;
    }

    @Override
    public List<Flock> findAll() {
        return null;
    }

    @Override
    public Flock findOne(int id) {
        Connection connection = null;
        return null;
    }

    @Override
    public int create(final Flock created) {
        String insertString = "INSERT INTO Flock " +
                "(authorid, name, description, latitude, longitude, radius)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        boolean success = jdbc.execute(insertString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, created.getAuthorid());
                ps.setString(2, created.getName());
                ps.setString(3, created.getDescription());
                ps.setFloat(4, created.getLatitude());
                ps.setFloat(5, created.getLongitude());
                ps.setDouble(6, created.getRadius());

                return ps.execute();
            }
        });

        return 1;
    }

    @Override
    public int update(Flock updated) {
        return 0;
    }
}
