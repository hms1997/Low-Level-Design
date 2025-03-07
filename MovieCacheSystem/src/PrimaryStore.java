import java.util.*;

// Class to manage the primary store
class PrimaryStore {
    Map<Integer, Movie> movies;

    public PrimaryStore() {
        this.movies = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.id, movie);
    }

    public List<Movie> searchByTitle(String title) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies.values()) {
            if (movie.title.contains(title)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> searchByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies.values()) {
            if (movie.genre.equals(genre)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> searchByYear(int year) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies.values()) {
            if (movie.releaseYear == year) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> searchMulti(String genre, int year, double minRating) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies.values()) {
            if (movie.genre.equals(genre) && movie.releaseYear == year && movie.rating >= minRating) {
                result.add(movie);
            }
        }
        return result;
    }
}
