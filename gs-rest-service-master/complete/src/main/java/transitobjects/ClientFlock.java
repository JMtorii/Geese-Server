package transitobjects;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class ClientFlock {
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private int range;
    private long author;
    private int member_count;

    public long getFlockId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getAuthor() {
        return author;
    }

    public int getRange() {
        return range;
    }

    public int getMemberCount() {
        return member_count;
    }
}
