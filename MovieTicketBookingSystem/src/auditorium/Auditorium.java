package auditorium;

import seat.Seat;
import show.MovieShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Auditorium {
    private String name;
    private int rows;
    private int columns;
    private List<MovieShow> shows;
    private List<List<Seat>> seats;

    public Auditorium(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.shows = new ArrayList<>();
        this.seats = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            List<Seat> row = new ArrayList<>();
            for(int j = 0; j < columns; j++) {
                row.add(new Seat(i, j));
            }
            seats.add(row);
        }
    }

    public boolean isSeatAvailable(Seat seat) {
        for(List<Seat> row : seats) {
            for(Seat availableSeat : row) {
                if(seat.getRow() == availableSeat.getRow() && seat.getColumn() == availableSeat.getColumn()) {
                    return !availableSeat.isBooked();
                }
            }
        }
        System.out.println("select a correct seat");
        return false;
    }

    public Seat bookSeat(Seat seat) {
        for(List<Seat> row : seats) {
            for(Seat availableSeat : row) {
                if(seat.getRow() == availableSeat.getRow() && seat.getColumn() == availableSeat.getColumn()) {
                    availableSeat.bookSeat();
                    return availableSeat;
                }
            }
        }
        System.out.println("select a correct seat");
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieShow> getShows() {
        return shows;
    }

    public void addShow(MovieShow show) {
        shows.add(show);
    }

    public void cancelSeat(Seat seat) {
        seat.setBooked(false);
    }

    public List<List<Seat>> getSeats() {
        return this.seats;
    }
}
