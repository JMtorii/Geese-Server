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
    public Boolean save(final Flock saved) {
        String insertString = "INSERT INTO Flock " +
                "(authorid, name, description, latitude, longitude, radius)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        LOGGER.error("SOME SHIT: " + String.valueOf(saved.getAuthorid()));
        return jdbc.execute(insertString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, saved.getAuthorid());
                ps.setString(2, saved.getName());
                ps.setString(3, saved.getDescription());
                ps.setFloat(4, saved.getLatitude());
                ps.setFloat(5, saved.getLongitude());
                ps.setDouble(6, saved.getRange());

                return ps.execute();
            }
        });
    }
}
