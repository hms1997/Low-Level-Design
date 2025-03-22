package fare;

import ride.Ride;

public interface FareCalculationStrategy {
    double calculateFare(Ride ride);
}
