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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
                "SELECT * FROM Flock";

        List<Flock> flocks = new ArrayList<>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(query);

            for (Map row : rows) {
                Flock flock = new Flock.Builder()
                        .id((int) row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .name((String) row.get("name"))
                        .description((String) row.get("description"))
                        .latitude((float) row.get("latitude"))
                        .longitude((float) row.get("longitude"))
                        .radius((double) row.get("radius"))
                        .score((int) row.get("score"))
                        .createdTime(TimeHelper.fromDB((Timestamp) row.get("createdTime")))
                        .expireTime(TimeHelper.fromDB((Timestamp) row.get("expireTire")))
                        .imageUri((String) row.get("imageURI"))
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
                        "WHERE id = ?";

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
                                .imageUri(rs.getString("imageURI"))
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
                        "longitude = ?, radius = ?, score = ?, createdTime = ?, expireTime = ?, imageURI = ?" +
                        "WHERE id = ?";

        return jdbc.update(query,
                updatedFlock.getAuthorid(),
                updatedFlock.getName(),
                updatedFlock.getDescription(),
                updatedFlock.getLatitude(),
                updatedFlock.getLongitude(),
                updatedFlock.getRadius(),
                updatedFlock.getScore(),
                TimeHelper.toDB(updatedFlock.getCreatedTime()), // don't allow change to createdTime, add lastModifiedTime?
                TimeHelper.toDB(updatedFlock.getExpireTime()),
                updatedFlock.getImageUri(),
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
                "(authorid, name, description, latitude, longitude, radius, score, createdTime, expireTime, imageUri)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbc.update(query,
                created.getAuthorid(),
                created.getName(),
                created.getDescription(),
                created.getLatitude(),
                created.getLongitude(),
                created.getRadius(),
                created.getScore(),
                TimeHelper.toDB(LocalDateTime.now()),
                TimeHelper.toDB(created.getExpireTime()),
                created.getImageUri()
        );

        return jdbc.queryForObject("select last_insert_id()", Integer.class);
    }

    @Override
    public List<Flock> getNearbyFlocks(float latitude, float longitude) {

        // 111.045 for km
        String sqlString =
                "SELECT A.id AS id, " +
                        "A.authorid AS authorid, " +
                        "A.name AS name, " +
                        "A.description AS description, " +
                        "A.latitude AS latitude, " +
                        "A.longitude AS longitude, " +
                        "A.radius AS radius, " +
                        "A.score AS score, " +
                        "A.createdTime AS createdTime, " +
                        "A.expireTime AS expireTime, " +
                        "A.imageURI AS imageURI, " +
                        "ifnull(B.members, 0) AS members " +
                "FROM (" +
                        "SELECT z.id, " +
                            "z.authorid, " +
                            "z.name, " +
                            "z.description, " +
                            "z.latitude, " +
                            "z.longitude, " +
                            "z.radius, " +
                            "z.score, " +
                            "z.createdTime, " +
                            "z.expireTime, " +
                            "z.imageURI, " +
                            "p.distance_unit " +
                                "* DEGREES(ACOS(COS(RADIANS(p.latpoint)) " +
                                "* COS(RADIANS(z.latitude)) " +
                                "* COS(RADIANS(p.longpoint) - RADIANS(z.longitude)) " +
                                "+ SIN(RADIANS(p.latpoint)) " +
                                "* SIN(RADIANS(z.latitude)))) AS distance_in_km " +
                        "FROM Flock AS z " +
                        "JOIN ( " +
                            "SELECT ? AS latpoint, " +
                                "? AS longpoint, " +
                                "100.0 AS radius, " +
                                "111.045 AS distance_unit " +
                            ") AS p ON 1=1 " +
                        "WHERE z.latitude " +
                            "BETWEEN p.latpoint  - (p.radius / p.distance_unit) " +
                                "AND p.latpoint  + (p.radius / p.distance_unit) " +
                                "AND z.longitude " +
                            "BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) " +
                                "AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) " +
                        "ORDER BY distance_in_km " +
                        "LIMIT 15" +
                ") AS A " +
                "LEFT JOIN (" +
                    "SELECT flockid, count(*) as members " +
                    "FROM FavouritedFlocks " +
                    "GROUP BY flockid" +
                ") AS B ON A.id = B.flockid " +
                "ORDER BY A.id";

        List<Flock> flocks = new ArrayList<>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(sqlString, new Object[] {latitude, longitude} );

            for (Map row : rows) {
                Flock flock = new Flock.Builder()
                        .id((int) row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .name((String) row.get("name"))
                        .description((String) row.get("description"))
                        .latitude((float) row.get("latitude"))
                        .longitude((float) row.get("longitude"))
                        .radius((double) row.get("radius"))
                        .score((int) row.get("score"))
                        .createdTime(TimeHelper.fromDB((Timestamp) row.get("createdTime")))
                        .expireTime(TimeHelper.fromDB((Timestamp) row.get("expiredTime")))
                        .imageUri((String) row.get("imageURI"))
                        .members(Integer.valueOf(((Long) row.get("members")).intValue()))
                        .build();

                flocks.add(flock);
            }

            return flocks;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Goose: findAll returns no rows");
            return null;
        }
    }

    // TODO: implement me so I return a list of flock objects associated with the gooseId
    @Override
    public List<Flock> getFavourited(int gooseId) {
        String sqlString =
                "SELECT A.id AS id, " +
                        "A.authorid AS authorid, " +
                        "A.name AS name, " +
                        "A.description AS description, " +
                        "A.latitude AS latitude, " +
                        "A.longitude AS longitude, " +
                        "A.radius AS radius, " +
                        "A.score AS score, " +
                        "A.createdTime AS createdTime, " +
                        "A.expireTime AS expireTime, " +
                        "A.imageURI AS imageURI, " +
                        "ifnull(B.members, 0) AS members " +
                "FROM (" +
                    "SELECT * FROM Flock WHERE id IN (SELECT flockid FROM FavouritedFlocks WHERE gooseId = ?)" +
                ") AS A " +
                "LEFT JOIN (SELECT flockid, COUNT(*) AS members FROM FavouritedFlocks GROUP BY flockid) AS B ON A.id = B.flockid " +
                "ORDER BY A.id;";

        List<Flock> flocks = new ArrayList<>();

        try {
            List<Map<String, Object>> rows = jdbc.queryForList(sqlString, gooseId);

            for (Map row : rows) {
                Flock flock = new Flock.Builder()
                        .id((int) row.get("id"))
                        .authorid((int) row.get("authorid"))
                        .name((String) row.get("name"))
                        .description((String) row.get("description"))
                        .latitude((float) row.get("latitude"))
                        .longitude((float) row.get("longitude"))
                        .radius((double) row.get("radius"))
                        .score((int) row.get("score"))
                        .imageUri((String) row.get("imageURI"))
                        .members(Integer.valueOf(((Long) row.get("members")).intValue()))
                        .build();

                flocks.add(flock);
            }

            return flocks;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Goose: getFavourited returns no rows");
            return flocks;
        }
    }

    @Override
    public int joinFlock(int gooseId, int flockId) {
        String query = "INSERT INTO FavouritedFlocks " +
                "(gooseId, flockId)" +
                "VALUES (?, ?)";

        return jdbc.update(query,
                gooseId,
                flockId
        );
    }

    @Override
    public int unjoinFlock(int gooseId, int flockId) {
        String query = "DELETE FROM FavouritedFlocks " +
                "WHERE gooseId = ? AND flockId = ?";

        return jdbc.update(query,
                gooseId,
                flockId
        );
    }

    @Override
    public int getFlockMembers(int flockid) {
        String query =
                "SELECT count(gooseid) " +
                "FROM FavouritedFlocks " +
                "WHERE flockid = ? ";

        return jdbc.queryForObject(query, new Object[]{flockid}, Integer.class);
    }
}
