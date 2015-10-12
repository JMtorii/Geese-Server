package com.geese.server.dao.impl;

import com.geese.server.dao.FlockDAO;
import com.geese.server.domain.Flock;
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
    @Autowired
    protected JdbcTemplate jdbc;

    /*`id` int(16) NOT NULL AUTO_INCREMENT,
    `authorid` int(16) NOT NULL,
    `name` varchar(32) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `latitude` float(10,6) DEFAULT NULL,
    `longitude` float(10,6) DEFAULT NULL,
    `radius` double DEFAULT NULL,*/

    String insertString = "INSERT INTO FLOCK " +
            "(AUTHORID, NAME, DESCRIPTION, LATITUDE," +
            " LONGITUDE, RADIUS) VALUES " +
            "(?, ?, ?, ?, ?, ?)";

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
        // Don't need to create new JdbcTemplate if it's autowired
        //this.jdbc = new JdbcTemplate(dataSource);
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
