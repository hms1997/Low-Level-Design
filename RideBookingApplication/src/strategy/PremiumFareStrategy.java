package strategy;

import fare.FareCalculationStrategy;
import ride.Ride;

public class PremiumFareStrategy implements FareCalculationStrategy {
    private static final double BASE_FARE = 10.0;
    private static final double PER_KM_RATE = 3.0;
    @Override
    public double calculateFare(Ride ride) {
        double distance = ride.getRideRequest().getPickUpLocation().distanceTo(ride.getRideRequest().getDropLocation());
        return BASE_FARE + (PER_KM_RATE * distance);
    }
}
