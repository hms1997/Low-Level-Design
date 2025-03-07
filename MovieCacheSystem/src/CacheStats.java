// Class to manage cache statistics
class CacheStats {
    int l1Hits;
    int l2Hits;
    int primaryStoreHits;
    int totalSearches;

    public CacheStats() {
        this.l1Hits = 0;
        this.l2Hits = 0;
        this.primaryStoreHits = 0;
        this.totalSearches = 0;
    }

    public void incrementL1Hits() {
        l1Hits++;
    }

    public void incrementL2Hits() {
        l2Hits++;
    }

    public void incrementPrimaryStoreHits() {
        primaryStoreHits++;
    }

    public void incrementTotalSearches() {
        totalSearches++;
    }

    @Override
    public String toString() {
        return "CacheStats{" +
                "l1Hits=" + l1Hits +
                ", l2Hits=" + l2Hits +
                ", primaryStoreHits=" + primaryStoreHits +
                ", totalSearches=" + totalSearches +
                '}';
    }
}
