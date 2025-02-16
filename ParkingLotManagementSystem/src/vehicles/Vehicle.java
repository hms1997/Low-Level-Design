package vehicles;

public abstract class Vehicle {
    private final String name;
    private final VehicleType vehicleType;

    public Vehicle(String licencePlate, VehicleType vehicleType) {
        this.name = licencePlate;
        this.vehicleType = vehicleType;
    }

    public String getName() {
        return name;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
