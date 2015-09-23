package transitobjects;

import flock.FlockCore;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class JoinFlockPayload {
    private long userId;
    private long flockId;
    private String auth;

    public long getUserId() {
        return userId;
    }

    public long getFlockId() {
        return flockId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFlockId(long flockId) {
        this.flockId = flockId;
    }

    public String getAuthentication() {
        return this.auth;
    }

    public String setAuthentication(String auth) {
        return this.auth;
    }
}
