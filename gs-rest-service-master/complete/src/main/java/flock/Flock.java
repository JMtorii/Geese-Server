package flock;

import transitobjects.ClientFlockPayload;
import transitobjects.CreateFlockPayload;

import java.util.LinkedList;
import java.util.List;

public class Flock extends FlockCore {

    private long flockId;
    //private final List<Long> userIds; if we use nosql, use this

    public Flock(CreateFlockPayload cfp) {
        // TODO: generate flock id with atomic counter?
        flockId = 1;

        this.setAuthor(cfp.getAuthor());
        this.setDescription(cfp.getDescription());
        this.setLatitude(cfp.getLatitude());
        this.setLongitude(cfp.getLongitude());
        this.setName(cfp.getName());
        this.setRange(cfp.getRange());
    }

    public ClientFlockPayload getClientPayload() {
        ClientFlockPayload c = new ClientFlockPayload();
        c.setAuthor(this.getAuthor());
        c.setDescription(this.getDescription());
        c.setLatitude(this.getLatitude());
        c.setLongitude(this.getLongitude());
        c.setName(this.getName());
        c.setRange(this.getRange());

        return c;
    }

    public long getId() {
        return flockId;
    }

    public void setId(long id) {
        this.flockId = id;
    }

    /*public List<Long> getUserIds() {
        return userIds;
    }

    public void addUser(long userId) {
        userIds.add(userId);
    }*/
}
