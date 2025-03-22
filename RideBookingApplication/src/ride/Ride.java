package ride;

import enums.RideStatus;
import users.Driver;

public class Ride {
    private String id;
    private RideRequest rideRequest;
    private Driver driver;
    private RideStatus rideStatus;
    private double fare;

    public Ride(String id, RideRequest rideRequest, Driver driver) {
        this.id = id;
        this.rideRequest = rideRequest;
        this.driver = driver;
        this.rideStatus = RideStatus.REQUESTED;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RideRequest getRideRequest() {
        return rideRequest;
    }

    public void setRideRequest(RideRequest rideRequest) {
        this.rideRequest = rideRequest;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
