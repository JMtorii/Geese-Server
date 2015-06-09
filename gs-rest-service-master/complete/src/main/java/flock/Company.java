package flock;

public class Company {

    private final String name;
    private final String position;

    public Company(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
