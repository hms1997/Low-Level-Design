package ticket;

import seat.Seat;
import show.MovieShow;

import java.util.Objects;

public class Ticket {
    private double price;
    private MovieShow show;
    private String auditoriumName;
    private Seat seat;

    public Ticket(MovieShow show, String auditoriumName, Seat seat) {
        this.show = show;
        this.auditoriumName = auditoriumName;
        this.seat = seat;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Ensure same class

        Ticket ticket = (Ticket) obj; // Typecast to Seat

        return show.equals(ticket.show) && auditoriumName.equals(ticket.auditoriumName) && seat.equals(ticket.seat); // Compare row and column
    }

    @Override
    public int hashCode() {
        return Objects.hash(show, auditoriumName, seat); // Generate hash code using row & column
    }


}
