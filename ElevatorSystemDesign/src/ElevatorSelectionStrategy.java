import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator slectElevator(List<Elevator> elevators, Request request);
}
