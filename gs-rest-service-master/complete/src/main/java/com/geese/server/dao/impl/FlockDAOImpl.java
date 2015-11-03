package com.geese.server.dao.impl;

import com.geese.server.dao.FlockDAO;
import com.geese.server.domain.Flock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
    public void delete(Flock deleted) {

    }

    @Override
    public List<Flock> findAll() {
        return null;
    }

    // TODO: change to PreparedStatement
    // http://www.mkyong.com/spring/maven-spring-jdbc-example/
    @Override
    public Flock findOne(int id) {
        Connection connection = null;

//        try {
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if(connection != null ) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//
//                }
//            }
//        }

        return null;
    }

    @Override
    public Flock save(final Flock saved) {
        String insertString = "INSERT INTO Flock " +
                "(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        boolean success = jdbc.execute(insertString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, saved.getAuthorid());
                ps.setString(2, saved.getName());
                ps.setString(3, saved.getDescription());
                ps.setFloat(4, saved.getLatitude());
                ps.setFloat(5, saved.getLongitude());
                ps.setDouble(6, saved.getRadius());
                ps.setInt(7, 0); saved.getScore();
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now(ZoneId.ofOffset("", ZoneOffset.UTC))));
                ps.setNull(9, Types.TIMESTAMP);
                return ps.execute();
            }
        });

        return saved;
    }
}
