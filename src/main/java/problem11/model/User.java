package problem11.model;

public class User {
    private String name;
    private String id;
    private String city;

    public User(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', city='" + city + "'}";
    }
}
