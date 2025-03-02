package admin;

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
import java.util.concurrent.ConcurrentHashMap;

public final class AdminSystem {
    private static AdminSystem INSTANCE = null;
    private static Map<String, City> cities = new ConcurrentHashMap<>();

    private AdminSystem() {

    }

    public static AdminSystem getAdminSystem() {
        if(INSTANCE == null) {
            synchronized (AdminSystem.class) {
                if(INSTANCE == null) {
                    return new AdminSystem();
                }
            }
        }
        return INSTANCE;
    }
    public  void addCity(String name) {
        City city = new City(name);
        if(!cities.containsKey(name)) {
            cities.put(name, city);
            System.out.println("City " + name + " is added successfully");
        } else {
            System.out.println("City " + name + " is already added into the system");
        }
    }

    public  Map<String, City> getCities() {
        return cities;
    }

    public void addCinemaToCity(String cityName, String cinemaName) {
        Cinema cinema = new Cinema(cinemaName);
        if(cities.containsKey(cityName)) {
            City city = cities.get(cityName);
            city.addCinema(cinema);
            System.out.println("Cinema " + cinemaName + " is added into city: " + cityName);
        } else {
            System.out.println("City " + cityName + " is not available into the system");
        }
    }

    public void addAuditoriumToCinema(String cityName, String cinemaName, Auditorium auditorium) {
        if(cities.containsKey(cityName)) {
            City city = cities.get(cityName);
            List<Cinema> cinemas = city.getCinemas();
            for(Cinema cinema : cinemas) {
                if(cinema.getName().equals(cinemaName)) {
                    cinema.addAuditorium(auditorium);
                    System.out.println("Auditorium " + auditorium.getName() + " is successfully added to cinema: " + cinemaName
                    + " of city: " + cityName);
                    return;
                }
            }
            System.out.println("Cinema " + cinemaName + " is not found in city: " + cityName);
        } else {
            System.out.println("City: " + cityName + " is not created into the system yet!");
        }
    }

    public void addMovieShowToAuditorium(String cityName, String cinemaName, String auditoriumName, MovieShow show) {
        if(cities.containsKey(cityName)) {
            City city = cities.get(cityName);
            List<Cinema> cinemas = city.getCinemas();
            for(Cinema cinema : cinemas) {
                if(cinema.getName().equals(cinemaName)) {
                    List<Auditorium> auditoriums = cinema.getAuditoriums();
                    for(Auditorium auditorium : auditoriums) {
                        if(auditorium.getName().equals(auditoriumName)) {
                            auditorium.addShow(show);
                            System.out.println("Movie: " + show.getMovieName() + " is added into Auditorium " + auditorium.getName() + " of cinema: " + cinemaName
                                    + " of city: " + cityName);
                            return;
                        }
                    }
                    System.out.println("Auditorium: " + auditoriumName + " is not available in cinema: " + cinemaName);
                    return;
                }
            }
            System.out.println("Cinema " + cinemaName + " is not found in city: " + cityName);
        } else {
            System.out.println("City: " + cityName + " is not created into the system yet!");
        }
    }

    public void listCities() {
        System.out.println("Available cities: ");
        for(String city : cities.keySet()) {
            System.out.print(city + " ");
        }
        System.out.println();
    }

    public void listCinemasInCity(String cityName) {
        System.out.println("Available cinemas in city: " + cityName);
        cityName = cityName.toLowerCase();
        City city = cities.get(cityName);
        for(Cinema cinema : city.getCinemas()) {
            System.out.println(cinema.getName());
        }
    }

    public void listMoviesInCity(String cityName) {
        System.out.println("Available Movies in " + cityName + " are: ");
        City city = cities.get(cityName);
        if(city == null) {
            System.out.println("select a correct city name");
            return;
        }
        for(Cinema cinema : city.getCinemas()) {
            for(Auditorium auditorium : cinema.getAuditoriums()) {
                for(MovieShow show : auditorium.getShows()) {
                    System.out.println(show);
                }
            }
        }
    }
}
