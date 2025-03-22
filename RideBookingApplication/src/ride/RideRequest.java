package ride;

import enums.RideType;
import models.Location;
import users.Passenger;

public class RideRequest {
    private String requestId;
    private Passenger passenger;
    private Location pickUpLocation;
    private Location dropLocation;
    private RideType rideType;

    public RideRequest(String id, Passenger passenger, Location pickUpLocation, Location dropLocation, RideType rideType) {
        requestId = id;
        this.passenger = passenger;
        this.pickUpLocation = pickUpLocation;
        this.dropLocation = dropLocation;
        this.rideType = rideType;
    }

    public String getRequestId() {
        return requestId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public Location getDropLocation() {
        return dropLocation;
    }

    public RideType getRideType() {
        return rideType;
    }
}
