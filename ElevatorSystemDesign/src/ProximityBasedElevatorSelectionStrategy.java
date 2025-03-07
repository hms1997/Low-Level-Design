import java.util.List;

public class ProximityBasedElevatorSelectionStrategy implements ElevatorSelectionStrategy{
    private Elevator bestElevator;
    private int minDistance = Integer.MAX_VALUE;
    @Override
    public Elevator slectElevator(List<Elevator> elevators, Request request) {
        for(Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            if(distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}
