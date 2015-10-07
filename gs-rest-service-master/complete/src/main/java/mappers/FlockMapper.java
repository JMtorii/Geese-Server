package mappers;

import flock.Flock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlockMapper implements RowMapper {

    public Flock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Flock flock = new Flock(null);
        flock.setId(rs.getLong("id"));
        flock.setName(rs.getString("name"));
        flock.setDescription(rs.getString("description"));
        flock.setLatitude(rs.getDouble("latitude"));
        flock.setLongitude(rs.getDouble("longitude"));
        flock.setRange(rs.getInt("range"));
        return flock;
    }
}