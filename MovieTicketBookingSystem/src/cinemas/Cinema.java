package cinemas;

import auditorium.Auditorium;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private String address;
    private List<Auditorium> auditoriums;

    public Cinema(String name) {
        this.name = name;
        this.auditoriums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void addAuditorium(Auditorium auditorium) {
        auditoriums.add(auditorium);
    }
}
