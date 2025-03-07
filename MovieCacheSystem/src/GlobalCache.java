import java.util.*;

// Class to manage the global cache (L2)
class GlobalCache {
    int capacity;
    Map<String, Movie> cache; // Global popular searches cache
    Map<String, Integer> frequencyMap; // To track frequency for LFU eviction

    public GlobalCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<String, Movie>(20, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 20;
            }
        };
        this.frequencyMap = new HashMap<>();
    }

    public void add(String key, Movie movie) {
        if (cache.size() >= capacity) {
            removeLFU();
        }
        cache.put(key, movie);
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
    }

    public Movie get(String key) {
        if (cache.containsKey(key)) {
            frequencyMap.put(key, frequencyMap.get(key) + 1);
            return cache.get(key);
        }
        return null;
    }

    public void removeLFU() {
        if (!cache.isEmpty()) {
            String lfuKey = Collections.min(frequencyMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
            cache.remove(lfuKey);
            frequencyMap.remove(lfuKey);
        }
    }
}