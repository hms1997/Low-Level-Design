package parkinglevel;

import parkingslot.ParkingSlot;
import vehicles.Vehicle;
import vehicles.VehicleType;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLevel {
    private final int levelNumber;
    private final List<ParkingSlot> parkingSlotList;


    public ParkingLevel(int levelNumber, int slotsPerType) {
        this.levelNumber = levelNumber;
        this.parkingSlotList = new ArrayList<>();

        for(int i = 0; i < slotsPerType; i++) {
            parkingSlotList.add(new ParkingSlot(i + 1, VehicleType.BIKE));
            parkingSlotList.add(new ParkingSlot(i + 1 + slotsPerType, VehicleType.CAR));
            parkingSlotList.add(new ParkingSlot(i + 1 + 2 * slotsPerType, VehicleType.TRUCK));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(ParkingSlot parkingSlot : parkingSlotList) {
            if(parkingSlot.isSlotAvailable() && parkingSlot.getSlotType() == vehicle.getVehicleType()) {
                parkingSlot.parkVehicle(vehicle);
                System.out.println(vehicle.getVehicleType() + " is parked successfully at level: " + levelNumber + " and slot: " + parkingSlot.getSlotNumber());
                return true;
            }
        }
        return false;
    }

    public boolean removeVehicle(int slotNumber) {
        for(ParkingSlot parkingSlot : parkingSlotList) {
            if(parkingSlot.getSlotNumber() == slotNumber) {
                parkingSlot.removeVehicle();
                System.out.println("Vehicle of type: " + parkingSlot.getSlotType() + " is removed");
                return true;
            }
        }
        return false;
    }

    public void displayAvailableSlots() {
        System.out.println("Available slots in level: " + levelNumber +" is ");
        Map<VehicleType, Integer> map = new HashMap<>();
        for(ParkingSlot parkingSlot : parkingSlotList) {
            if(parkingSlot.isSlotAvailable()) map.put(parkingSlot.getSlotType(),
                    map.getOrDefault(parkingSlot.getSlotType(), 0) + 1);
        }

        for (Map.Entry<VehicleType, Integer> entry : map.entrySet()) {
            VehicleType vehicleType = entry.getKey();
            Integer count = entry.getValue();
            System.out.println(vehicleType + ": " + count);
        }
    }
}
