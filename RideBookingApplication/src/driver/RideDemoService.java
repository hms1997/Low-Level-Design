package driver;

import enums.RideType;
import models.Location;
import ride.RideRequest;
import service.RideMatchingService;
import users.Driver;
import users.Passenger;

import java.util.UUID;

public class RideDemoService {
    private static RideMatchingService rideMatchingService = RideMatchingService.getRideMatchingService();
    public static void main(String[] args) {
        // Create a passenger
        Passenger passenger = new Passenger("1", "Himanghsu");
        Location pickupLocation = new Location(37.7749, -122.4194);
        Location destination = new Location(37.7849, -122.4294);

        // Create a driver
        Driver driver = new Driver("1", "PSJ", new Location(37.7750, -122.4184));
        Driver driver2 = new Driver("1", "Akash", new Location(27.7750, -222.4184));
        rideMatchingService.addDriver(driver);
        rideMatchingService.addDriver(driver2);

        //create ride request
        RideRequest rideRequest = new RideRequest(UUID.randomUUID().toString(), passenger, pickupLocation, destination, RideType.REGULAR);

        System.out.println("Ride requested in main method for user 1");
        rideMatchingService.requestRide(rideRequest);

        // Create a passenger 2
        Passenger passenger2 = new Passenger("1", "Riya");
        Location pickupLocation2 = new Location(37.7749, -122.4194);
        Location destination2 = new Location(37.7849, -122.4294);

        //create ride request
        RideRequest rideRequest2 = new RideRequest(UUID.randomUUID().toString(), passenger2, pickupLocation2, destination2, RideType.PREMIUM);
        System.out.println("Ride requested in main method for user 2");
        rideMatchingService.requestRide(rideRequest2);

    }
}
