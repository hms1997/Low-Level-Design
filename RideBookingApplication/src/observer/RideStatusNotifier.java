package observer;

import enums.RideStatus;

import java.util.ArrayList;
import java.util.List;

public class RideStatusNotifier {
    private List<RideStatusObserver> observers = new ArrayList<>();

    public void addObserver(RideStatusObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String rideId, RideStatus rideStatus) {
        for(RideStatusObserver observer : observers) {
            observer.update(rideId, rideStatus);
        }
    }
}
