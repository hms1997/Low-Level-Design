package booking;

import admin.AdminSystem;
import auditorium.Auditorium;
import cinemas.Cinema;
import cities.City;
import seat.Seat;
import show.MovieShow;
import ticket.Ticket;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class BookingSystem {
    private static BookingSystem INSTANCE = null;
    private static Set<Ticket> ticketSet = new HashSet<>();
    private static Map<String, City> cities = AdminSystem.getAdminSystem().getCities();
    private AdminSystem adminSystem = AdminSystem.getAdminSystem();

    private BookingSystem(){

    }

    public static BookingSystem getBookingSystem() {
        if(INSTANCE == null) {
            synchronized (AdminSystem.class) {
                if(INSTANCE == null) {
                    return new BookingSystem();
                }
            }
        }
        return INSTANCE;
    }

    public void bookTickets(String cityName, String cinemaName, String auditoriumName, MovieShow show, Seat seat) {
        if(cities.containsKey(cityName)) {
            List<Cinema> cinemas = cities.get(cityName).getCinemas();
            for(Cinema cinema : cinemas) {
                if(cinema.getName().equals(cinemaName)) {
                    List<Auditorium> auditoriums = cinema.getAuditoriums();
                    for(Auditorium auditorium : auditoriums) {
                        if(auditorium.getName().equals(auditoriumName)) {
                            List<MovieShow> shows = auditorium.getShows();
                            List<List<Seat>> seats = auditorium.getSeats();
                            if(shows.contains(show)) {
                                System.out.println("Sitting arrangements: ");
                                for(List<Seat> rowSeat : seats) {
                                    System.out.println(rowSeat);
                                }
                                System.out.println();
                                if(auditorium.isSeatAvailable(seat)) {
                                    Seat bookSeat = auditorium.bookSeat(seat);
                                    if(bookSeat == null) return;
                                    Ticket ticket = new Ticket(show, auditoriumName, seat);
                                    ticketSet.add(ticket);
                                    System.out.println("seat is booked and a ticket is created successfully");
                                    System.out.println(bookSeat);
                                } else {
                                    System.out.println("Selected seat is not available");
                                }
                            } else {
                                System.out.println("Movie: " + show.getMovieName() + " is not available");
                                adminSystem.listMoviesInCity(cityName);
                            }
                            return;
                        }
                    }
                    System.out.println("Auditorium: " + auditoriumName + " is not available");
                    return;
                }
            }
            System.out.println("Cinema " + cinemaName + " is not available");
        } else {
            System.out.println("city not fount");
        }
    }

    public void cancelTicket(String cityName, String cinemaName, String auditoriumName, MovieShow show, Seat seat) {
        Ticket ticket = new Ticket(show, auditoriumName, seat);
        if(ticketSet.contains(ticket)) {
            if(cities.containsKey(cityName)) {
                List<Cinema> cinemas = cities.get(cityName).getCinemas();
                for(Cinema cinema : cinemas) {
                    if (cinema.getName().equals(cinemaName)) {
                        List<Auditorium> auditoriums = cinema.getAuditoriums();
                        for (Auditorium auditorium : auditoriums) {
                            if (auditorium.getName().equals(auditoriumName)) {
                                List<List<Seat>> seats = auditorium.getSeats();
                                for(List<Seat> row : seats) {
                                    for(Seat seat1 : row) {
                                        if(seat1.equals(seat)) {
                                            seat1.releaseSeat();
                                            ticketSet.remove(ticket);
                                            System.out.println("Ticket canceled");
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println("Auditorium: " + auditoriumName + " is not available");
                        return;
                    }
                }
                System.out.println("Cinema: " + cinemaName + " is not available");
                return;
            }
            System.out.println("City: " + cityName + " is not available");
        } else {
            System.out.println("Invalid ticket!");
        }
    }

    public void listSittingArrangement(String cityName, String cinemaName, String auditoriumName, MovieShow show) {
        if(cities.containsKey(cityName)) {
            List<Cinema> cinemas = cities.get(cityName).getCinemas();
            for (Cinema cinema : cinemas) {
                if (cinema.getName().equals(cinemaName)) {
                    List<Auditorium> auditoriums = cinema.getAuditoriums();
                    for (Auditorium auditorium : auditoriums) {
                        if (auditorium.getName().equals(auditoriumName)) {
                            List<MovieShow> shows = auditorium.getShows();
                            List<List<Seat>> seats = auditorium.getSeats();
                            if (shows.contains(show)) {
                                System.out.println("Sitting arrangements: ");
                                for (List<Seat> rowSeat : seats) {
                                    System.out.println(rowSeat);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
