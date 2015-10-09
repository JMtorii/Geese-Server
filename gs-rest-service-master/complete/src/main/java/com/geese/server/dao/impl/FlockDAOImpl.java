package com.geese.server.dao.impl;

import com.geese.server.dao.FlockDAO;
import com.geese.server.domain.Flock;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 * Created by JMtorii on 2015-10-06.
 */
@Repository
public class FlockDAOImpl implements FlockDAO {
    //@Autowired
    //protected JdbcTemplate jdbc;

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
    public Flock save(Flock saved) {
        return null;
    }
}
