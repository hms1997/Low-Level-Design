package users;

public class Passenger{
    private String passengerId;
    private String passengerName;

    public Passenger(String passengerId, String passengerName) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }
}
