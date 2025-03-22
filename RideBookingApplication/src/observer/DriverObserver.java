package observer;

import enums.RideStatus;

public class DriverObserver implements RideStatusObserver{
    @Override
    public void update(String rideId, RideStatus rideStatus) {
        System.out.println("Notifying Driver: Ride ID " + rideId + " is now " + rideStatus);
    }
}
