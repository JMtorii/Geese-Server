package mappers;

import flock.Flock;
import org.springframework.jdbc.core.RowMapper;

private static final class FlockMapper implements RowMapper<Flock> {

    public Flock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Flock flock = new Flock();
        flock.setId(rs.getString("id"));
        flock.setName(rs.getString("name"));
        flock.setDescription(rs.getString("description"));
        flock.setLatitude(rs.getString("latitude"));
        flock.setLongitude(rs.getString("longitude"));
        flock.setRange(rs.getString("range"));
        return flock;
    }
}