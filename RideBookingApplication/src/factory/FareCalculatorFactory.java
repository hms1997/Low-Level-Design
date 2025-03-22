package factory;

import enums.RideType;
import fare.FareCalculationStrategy;
import strategy.PremiumFareStrategy;
import strategy.RegularFareStrategy;

public class FareCalculatorFactory {
    public static FareCalculationStrategy getFareCalculator(RideType rideType) {
        return switch (rideType) {
            case REGULAR -> new RegularFareStrategy();
            case PREMIUM -> new PremiumFareStrategy();
            default -> throw new IllegalArgumentException("Invalid ride type");
        };
    }
}
