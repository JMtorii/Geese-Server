package com.geese.server.dao.impl;

import com.geese.server.dao.UserDAO;
import com.geese.server.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by JMtorii on 2015-10-12.
 */
public class UserDAOImpl implements UserDAO {
    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(int id) {
        return null;
    }

    @Override
    public int save(User savedUser) {
        return jdbc.update(
                "INSERT INTO User (email, password) " +
                        "values (?, ?)",
                savedUser.getEmail(), savedUser.getPassword()
        );
    }

    @Override
    public void delete(User deletedUser) {

    }
}
