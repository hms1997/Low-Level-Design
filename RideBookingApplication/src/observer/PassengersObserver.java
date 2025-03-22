package observer;

import enums.RideStatus;
import enums.RideType;

public class PassengersObserver implements RideStatusObserver{
    @Override
    public void update(String rideId, RideStatus rideStatus) {
        System.out.println("Notifying Passenger: Ride ID " + rideId + " is now " + rideStatus);
    }
}
