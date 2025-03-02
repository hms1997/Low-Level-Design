package show;

import java.util.Objects;

public class MovieShow {
    private String movieName;
    private Long startTime;
    private Long endTime;

    public MovieShow(String movieName, Long startTime, Long endTime) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Ensure same class

        MovieShow that = (MovieShow) obj; // Typecast to MovieShow

        return Objects.equals(movieName, that.movieName) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, startTime, endTime);
    }

    @Override
    public String toString() {
        return "MovieShow{" +
                "movieName='" + movieName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
