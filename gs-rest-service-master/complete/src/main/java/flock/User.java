package flock;

public class User {

    private final String fullName;
    private final String email;
    private final long phoneNumber;

    public User(String name, String email, long phone) {
        this.fullName = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
}
