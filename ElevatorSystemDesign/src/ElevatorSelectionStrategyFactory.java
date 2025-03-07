public class ElevatorSelectionStrategyFactory {
    public static ElevatorSelectionStrategy getElevatorSelectionStrategy(String type) {
        if(type.equals("proximity")) {
            return new ProximityBasedElevatorSelectionStrategy();
        } else if(type.equals("direction")) {
            return new DirectionBasedElevatorSelectionStrategy();
        }
        throw new IllegalArgumentException("Unsupported strategy!: " + type);
    }
}
