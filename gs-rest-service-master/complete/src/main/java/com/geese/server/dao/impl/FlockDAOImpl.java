package com.geese.server.dao.impl;

import com.geese.server.dao.FlockDAO;
import com.geese.server.dao.util.TimeHelper;
import com.geese.server.domain.Flock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ecrothers on 2015-10-06.
 */
@Repository
public class FlockDAOImpl implements FlockDAO {
    private static final Logger logger = LoggerFactory.getLogger(FlockDAOImpl.class);

    @Autowired
    protected JdbcTemplate jdbc;

    @Override
    public List<Flock> findAll() {
        String query =
                "SELECT * FROM Flock;";

        List<Flock> flocks = new ArrayList<Flock>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Flock flock = new Flock.Builder()
                        .id((int)row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .name((String) row.get("name"))
                        .description((String) row.get("description"))
                        .latitude((float) row.get("latitude"))
                        .longitude((float) row.get("longitude"))
                        .radius((double) row.get("radius"))
                        .score((int) row.get("score"))
                        .createdTime((LocalDateTime) row.get("createdTime"))
                        .expireTime((LocalDateTime) row.get("expireTime"))
                        .build();

                flocks.add(flock);
            }

            return flocks;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Flock: findAll returns no rows");
            return null;
        }
    }

    @Override
    public Flock findOne(final int flockId) {
        String query =
                "SELECT * FROM Flock " +
                        "WHERE id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{flockId}, new RowMapper<Flock>() {
                @Override
                public Flock mapRow(ResultSet rs, int rowNum) throws SQLException {

                    if (rs.getRow() < 1) {
                        return null;
                    } else {
                        return new Flock.Builder()
                                .id(rs.getInt("id"))
                                .authorid(rs.getInt("authorid"))
                                .name(rs.getString("name"))
                                .description(rs.getString("description"))
                                .latitude(rs.getFloat("latitude"))
                                .longitude(rs.getFloat("longitude"))
                                .radius(rs.getDouble("radius"))
                                .score(rs.getInt("score"))
                                .createdTime(TimeHelper.fromDB(rs.getTimestamp("createdTime")))
                                .expireTime(TimeHelper.fromDB(rs.getTimestamp("expireTime")))
                                .build();
                    }
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Flock: findOne returns no rows");
            return null;
        }
    }

    @Override
    public int update(final Flock updatedFlock) {
        String query =
                "UPDATE Flock " +
                        "SET authorid = ?, name = ?, description = ?, latitude = ?," +
                        "longitude = ?, radius = ?, score = ?, createdTime = ?, expireTime = ?" +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedFlock.getAuthorid(),
                updatedFlock.getName(),
                updatedFlock.getDescription(),
                updatedFlock.getLatitude(),
                updatedFlock.getLongitude(),
                updatedFlock.getRadius(),
                updatedFlock.getScore(),
                TimeHelper.toDB(updatedFlock.getCreatedTime()),
                TimeHelper.toDB(updatedFlock.getExpireTime()),
                updatedFlock.getId());
    }

    @Override
    public int delete(final int flockId) {
        String query =
                "DELETE FROM Flock " +
                        "WHERE id = ?";

        return jdbc.update(query, flockId);
    }

    @Override
    public int create(final Flock created) {
        String query = "INSERT INTO Flock " +
                "(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(query,
                created.getAuthorid(),
                created.getName(),
                created.getDescription(),
                created.getLatitude(),
                created.getLongitude(),
                created.getRadius(),
                created.getScore(),
                TimeHelper.toDB(created.getCreatedTime()), //TODO use client or server version? Timestamp.valueOf(LocalDateTime.now(ZoneId.ofOffset("", ZoneOffset.UTC)))
                TimeHelper.toDB(created.getExpireTime())
        );
    }
}
