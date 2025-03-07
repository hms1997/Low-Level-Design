// Movie class representing movie attributes
class Movie {
    int id;
    String title;
    String genre;
    int releaseYear;
    double rating;

    public Movie(int id, String title, String genre, int releaseYear, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie[ " +  title + " (" + releaseYear + ") - " + genre + " [Rating: " + rating + "]]";
    }
}
