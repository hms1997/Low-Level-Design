package users;

import models.Location;

public class Driver{
    private String driverId;
    private String driverName;
    private boolean isAvailable;
    private Location currentLocation;

    public Driver(String driverId, String driverName, Location currentLocation) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.isAvailable = true;
        this.currentLocation = currentLocation;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
