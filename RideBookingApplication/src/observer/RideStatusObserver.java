package observer;

import enums.RideStatus;

public interface RideStatusObserver {
    void update(String rideId, RideStatus status);
}
