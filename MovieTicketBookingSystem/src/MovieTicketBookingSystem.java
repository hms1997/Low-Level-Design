import admin.AdminSystem;
import auditorium.Auditorium;
import booking.BookingSystem;
import seat.Seat;
import show.MovieShow;

import java.util.Scanner;

public class MovieTicketBookingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminSystem adminSystem = AdminSystem.getAdminSystem();
    private static BookingSystem bookingSystem = BookingSystem.getBookingSystem();
    public static void main(String[] args) {

        while(true) {
            System.out.println();
            System.out.println("** Ticket Booking System**");
            System.out.println("1. Add City");
            System.out.println("2. Add Cinema to City");
            System.out.println("3. Add Auditorium to Cinema");
            System.out.println("4. Add Movie Show to Auditorium");
            System.out.println("5. List Cities");
            System.out.println("6. List Cinemas in City");
            System.out.println("7. List Movies in City");
            System.out.println("8. Book Ticket");
            System.out.println("9. Cancel Ticket");
            System.out.println("10. Exit");
            System.out.println("11. show available seats");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1 -> addCity();
                case 2 -> addCinemaToCity();
                case 3 -> addAuditoriumToCinema();
                case 4 -> addMovieShowToAuditorium();
                case 5 -> adminSystem.listCities();
                case 6 -> listCinemasInCity();
                case 7 -> listMoviesInCity();
                case 8 -> bookTicket();
                case 9 -> cancelTicket();
                case 10 -> System.exit(0);
                case 11 -> listSittingArrangement();
                default -> System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public static void addCity() {
        System.out.println("Enter the city name: ");
        String name = scanner.nextLine();
        adminSystem.addCity(name);
    }

    private static void addCinemaToCity() {
        System.out.println("Enter City Name: ");
        String cityName = scanner.nextLine();
        System.out.println("Enter Cinema Name");
        String cinemaName = scanner.nextLine();

        adminSystem.addCinemaToCity(cityName, cinemaName);
    }

    private static void addAuditoriumToCinema() {
        System.out.println("Enter City Name: ");
        String cityName = scanner.nextLine();
        System.out.println("Enter Cinema Name");
        String cinemaName = scanner.nextLine();
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = scanner.nextLine();
        System.out.println("Enter number of rows");
        int rows = scanner.nextInt();
        System.out.println("Enter number of columns");
        int columns = scanner.nextInt();

        Auditorium auditorium = new Auditorium(auditoriumName, rows, columns);
        adminSystem.addAuditoriumToCinema(cityName, cinemaName, auditorium);
    }

    private static void addMovieShowToAuditorium() {
        System.out.println("Enter City Name: ");
        String cityName = scanner.nextLine();
        System.out.println("Enter Cinema Name");
        String cinemaName = scanner.nextLine();
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = scanner.nextLine();

        //create movieshow
        System.out.println("Enter the movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter start time (in milliseconds): ");
        long startTime = scanner.nextLong();
        System.out.print("Enter end time (in milliseconds): ");
        long endTime = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over
        MovieShow show = new MovieShow(movieName, startTime, endTime);

        adminSystem.addMovieShowToAuditorium(cityName, cinemaName, auditoriumName, show);
    }

    private static void listCinemasInCity() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        adminSystem.listCinemasInCity(cityName);
    }

    private static void listMoviesInCity() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        adminSystem.listMoviesInCity(cityName);
    }

    private static void listSittingArrangement() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        System.out.println("Enter Cinema Name");
        String cinemaName = scanner.nextLine();
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = scanner.nextLine();

        //create movieshow
        System.out.println("Enter the movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter start time (in milliseconds): ");
        long startTime = scanner.nextLong();
        System.out.print("Enter end time (in milliseconds): ");
        long endTime = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over
        MovieShow show = new MovieShow(movieName, startTime, endTime);
        bookingSystem.listSittingArrangement(cityName, cinemaName, auditoriumName, show);
    }

    private static void bookTicket() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        System.out.println("Enter Cinema Name");
        String cinemaName = scanner.nextLine();
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = scanner.nextLine();

        //create movieshow
        System.out.println("Enter the movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter start time (in milliseconds): ");
        long startTime = scanner.nextLong();
        System.out.print("Enter end time (in milliseconds): ");
        long endTime = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over
        MovieShow show = new MovieShow(movieName, startTime, endTime);

        System.out.println("Enter the row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter the column number: ");
        int column = scanner.nextInt();
        Seat seat = new Seat(row, column);

        bookingSystem.bookTickets(cityName, cinemaName, auditoriumName, show, seat);
    }

    private static void cancelTicket() {
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        System.out.println("Enter Cinema Name");
        String cinemaName = scanner.nextLine();
        System.out.println("Enter Auditorium Name: ");
        String auditoriumName = scanner.nextLine();

        //create movieshow
        System.out.println("Enter the movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter start time (in milliseconds): ");
        long startTime = scanner.nextLong();
        System.out.print("Enter end time (in milliseconds): ");
        long endTime = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over
        MovieShow show = new MovieShow(movieName, startTime, endTime);

        System.out.println("Enter the row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter the column number: ");
        int column = scanner.nextInt();
        Seat seat = new Seat(row, column);

        bookingSystem.cancelTicket(cityName, cinemaName, auditoriumName, show, seat);
    }
}