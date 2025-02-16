package parkinglot;

import parkinglevel.ParkingLevel;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final String  parkingLotName;
    private final List<ParkingLevel> parkingLevels;

    public ParkingLot(String parkingLotName, int numberOfLevels, int slotsPerTypePerLevel) {
        this.parkingLotName = parkingLotName;
        parkingLevels = new ArrayList<>();
        for(int i = 0; i < slotsPerTypePerLevel; i++) {
            parkingLevels.add(new ParkingLevel(i, slotsPerTypePerLevel));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(ParkingLevel parkingLevel : parkingLevels) {
            if(parkingLevel.parkVehicle(vehicle)) {
                return true;
            }
        }
        System.out.println("No parking slot is available for Vehicle of type: " + vehicle.getVehicleType());
        return false;
    }

    public boolean removeVehicle(int levelNumber, int slotNumber) {
        if(levelNumber < parkingLevels.size()) {
            return parkingLevels.get(levelNumber).removeVehicle(slotNumber);
        }
        System.out.println("Vehicle does not exist at specified location");
        return false;
    }

    public void displayAvailableSlots() {
        System.out.println("\n Parking Lot Status: ");
        for(ParkingLevel parkingLevel : parkingLevels) {
            parkingLevel.displayAvailableSlots();
        }
    }
}
