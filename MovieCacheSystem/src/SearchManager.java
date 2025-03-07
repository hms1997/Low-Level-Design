import java.util.*;
// Class to handle search operations
class SearchManager {
    PrimaryStore primaryStore;
    GlobalCache globalCache;
    Map<Integer, User> users;
    CacheStats cacheStats;

    public SearchManager() {
        this.primaryStore = new PrimaryStore();
        this.globalCache = new GlobalCache(20);
        this.users = new HashMap<>();
        this.cacheStats = new CacheStats();
    }

    public void addMovie(int id, String title, String genre, int year, double rating) {
        Movie movie = new Movie(id, title, genre, year, rating);
        primaryStore.addMovie(movie);
        System.out.println("Movie '" + title + "' added successfully");
    }

    public void addUser(int id, String name, String preferredGenre) {
        User user = new User(id, name, preferredGenre);
        users.put(id, user);
        System.out.println("User '" + name + "' added successfully");
    }

    public void search(int userId, String searchValue) {
        cacheStats.incrementTotalSearches();
        User user = users.get(userId);
        if (user != null) {
            Movie movie = user.l1Cache.get(searchValue);
            if (movie != null) {
                cacheStats.incrementL1Hits();
                System.out.println(movie.title + " (Found in L1)");
                return;
            }

            movie = globalCache.get(searchValue);
            if (movie != null) {
                cacheStats.incrementL2Hits();
                System.out.println(movie.title + " (Found in L2)");
                user.l1Cache.put(searchValue, movie); // Update L1 cache
                return;
            }

            List<Movie> results = primaryStore.searchByTitle(searchValue);
            if (!results.isEmpty()) {
                cacheStats.incrementPrimaryStoreHits();
                for (Movie result : results) {
                    System.out.println(result.title + " (Found in Primary Store)");
                    user.l1Cache.put(result.title, result); // Update L1 cache
                    globalCache.add(result.title, result); // Update L2 cache
                }
            } else {
                System.out.println("No results found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void searchMulti(int userId, String genre, int year, double minRating) {
        cacheStats.incrementTotalSearches();
        User user = users.get(userId);
        if (user != null) {
            List<Movie> results = primaryStore.searchMulti(genre, year, minRating);
            if (!results.isEmpty()) {
                cacheStats.incrementPrimaryStoreHits();
                for (Movie result : results) {
                    System.out.println(result.title + " (Found in Primary Store)");
                    user.l1Cache.put(result.title, result); // Update L1 cache
                    globalCache.add(result.title, result); // Update L2 cache
                }
            } else {
                System.out.println("No results found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void viewCacheStats() {
        System.out.println(cacheStats);
    }

    public void clearCache(String cacheLevel) {
        if (cacheLevel.equals("L1")) {
            for (User user : users.values()) {
                user.l1Cache.clear();
            }
            System.out.println("L1 cache cleared successfully");
        } else if (cacheLevel.equals("L2")) {
            globalCache.cache.clear();
            globalCache.frequencyMap.clear();
            System.out.println("L2 cache cleared successfully");
        } else {
            System.out.println("Invalid cache level.");
        }
    }
}
