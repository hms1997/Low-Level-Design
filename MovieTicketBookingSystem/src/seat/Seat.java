package seat;

import java.util.Objects;

public class Seat {
    private int seatNumber;
    private int row;
    private int column;
    private boolean isBooked;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Seat(" + row + "," + column + ", booked=" + isBooked + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Ensure same class

        Seat seat = (Seat) obj; // Typecast to Seat

        return row == seat.row && column == seat.column; // Compare row and column
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column); // Generate hash code using row & column
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void bookSeat() {
        this.isBooked = true;
    }
    public void releaseSeat() {
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
