package service;

import enums.RideStatus;
import factory.FareCalculatorFactory;
import fare.FareCalculationStrategy;
import observer.DriverObserver;
import observer.PassengersObserver;
import observer.RideStatusNotifier;
import ride.Ride;
import ride.RideRequest;
import users.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RideMatchingService {
    private static RideMatchingService rideMatchingService;
    private List<Driver> drivers;
    private List<RideRequest> rideRequests;
    private RideStatusNotifier rideStatusNotifier;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public RideMatchingService() {
        this.rideStatusNotifier = new RideStatusNotifier();
        drivers = new ArrayList<>();
        rideRequests = new ArrayList<>();
        rideStatusNotifier.addObserver(new DriverObserver());
        rideStatusNotifier.addObserver(new PassengersObserver());
    }

    public static synchronized RideMatchingService getRideMatchingService() {
        if (rideMatchingService == null) {
            rideMatchingService = new RideMatchingService();
        }
        return rideMatchingService;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void requestRide(RideRequest rideRequest) {
        rideRequests.add(rideRequest);
        try {
            System.out.println("Ride requested for user: " + rideRequest.getPassenger().getPassengerName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(() -> {
            findBestDriver(rideRequest);
        });
    }

    private void findBestDriver(RideRequest rideRequest) {
        double minimumDistance = Integer.MAX_VALUE;
        Driver bestDriver = null;

        try {
            System.out.println("Finding the best driver");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(Driver driver : drivers) {
            if(driver.isAvailable()) {
                double distance = driver.getCurrentLocation().distanceTo(rideRequest.getPickUpLocation());
                if(distance < minimumDistance) {
                    minimumDistance = distance;
                    bestDriver = driver;
                }
            }
        }

        if(bestDriver != null) {
            Ride ride = new Ride(UUID.randomUUID().toString(), rideRequest, bestDriver);
            FareCalculationStrategy fareCalculator = FareCalculatorFactory.getFareCalculator(rideRequest.getRideType());
            double fare = fareCalculator.calculateFare(ride);

            ride.setFare(fare);
            ride.setRideStatus(RideStatus.ACCEPTED);
            bestDriver.setAvailable(false);
            System.out.println("Driver found, name of the driver is: " + bestDriver.getDriverName());
            System.out.println("Total fare: " + fare);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rideStatusNotifier.notifyObservers(ride.getId(), ride.getRideStatus());

            completeRide(ride);
        } else {
            System.out.println("No driver is available");
        }
    }

    private void completeRide(Ride ride) {
        try {
            Thread.sleep(5000);
            ride.setRideStatus(RideStatus.IN_PROGRESS);
            System.out.println("Ride status: " + ride.getRideStatus());
            Thread.sleep(5000);
            ride.setRideStatus(RideStatus.COMPLETED);
            rideStatusNotifier.notifyObservers(ride.getId(), ride.getRideStatus());
            ride.getDriver().setAvailable(true);
            System.out.println("Ride completed successfully");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
