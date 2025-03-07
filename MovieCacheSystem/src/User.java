import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// User class representing users
class User {
    int id;
    String name;
    String preferredGenre;
    Map<String, Movie> l1Cache; // user specific cache

    public User(int id, String name, String preferredGenre) {
        this.id = id;
        this.name = name;
        this.preferredGenre = preferredGenre;
        l1Cache = new LinkedHashMap<>(5, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 5;
            }
        };
    }
}
