package parkingslot;

import vehicles.Vehicle;
import vehicles.VehicleType;

public class ParkingSlot {
    private final int slotNumber;
    private final VehicleType slotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber, VehicleType slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        isOccupied = false;
    }

    public boolean isSlotAvailable() {
        return !isOccupied;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if(!isOccupied && this.slotType == vehicle.getVehicleType()) {
            System.out.println(isOccupied + " " + slotType + " " + slotNumber);
            this.vehicle = vehicle;
            isOccupied = true;
            return true;
        }
        return false;
    }

    public  boolean removeVehicle() {
        if(isOccupied) {
            vehicle = null;
            isOccupied = false;
            return true;
        }
        return false;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
