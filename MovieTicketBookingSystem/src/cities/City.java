package cities;

import cinemas.Cinema;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Cinema> cinemas;

    public City(String name) {
        this.name = name;
        this.cinemas = new ArrayList<>();
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
