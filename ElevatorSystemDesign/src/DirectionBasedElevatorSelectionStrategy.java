import java.util.List;

public class DirectionBasedElevatorSelectionStrategy implements ElevatorSelectionStrategy {
    private Elevator bestElevator;
    private int minDistance = Integer.MAX_VALUE;
    @Override
    public Elevator slectElevator(List<Elevator> elevators, Request request) {
        for(Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            int sourceFloor = request.getSourceFloor();
            int destinationFloor = request.getDestinationFloor();
            boolean isInSameDirection = currentFloor <= sourceFloor && currentFloor <= destinationFloor;
            int distance = Math.abs(currentFloor - sourceFloor);

            if(isInSameDirection && distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}
