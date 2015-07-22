package flock;

import java.util.LinkedList;
import java.util.List;

public class Flock {

    /*private final String name;
    public final String description;
    public final double latitude;
    public final double longitude;
    public final String createdDate;
    public final String privacy;
    public final int members;*/

    private final long id;
    private final String flockName;
    //private final List<Long> userIds; if we use nosql, use this
    private final long creatorId;
    private double latitude;
    private double longitude;
    private long range;

    public Flock(long id, String flockName, long creatorId) {
        this.id = id;
        this.flockName = flockName;
        this.creatorId = creatorId;
        //this.userIds = new LinkedList<Long>();
        //addUser(creatorId);
    }

    public long getId() {
        return id;
    }

    public String getFlockName() {
        return flockName;
    }

    /*public List<Long> getUserIds() {
        return userIds;
    }

    public void addUser(long userId) {
        userIds.add(userId);
    }*/
}
