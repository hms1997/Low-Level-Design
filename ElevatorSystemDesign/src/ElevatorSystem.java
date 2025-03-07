import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private static ElevatorSystem elevatorSystem = null;
    private int numberOfElevator;
    private int capacityOfEachElevator;
    private String strategyType;
    private List<Elevator> elevators;
    private List<Thread> elevatorThreads;
    private ElevatorSelectionStrategy elevatorSelectionStrategy;

    private ElevatorSystem() {

    }

    public static ElevatorSystem getElevatorSystem() {
        if(elevatorSystem == null) {
            synchronized (ElevatorSystem.class) {
                if(elevatorSystem == null) {
                    elevatorSystem = new ElevatorSystem();
                }
            }
        }
        return elevatorSystem;
    }

    public void createElevatorSystem(int numberOfElevator, int capacityOfEachElevator, String strategyType) {
        this.numberOfElevator = numberOfElevator;
        this.capacityOfEachElevator = capacityOfEachElevator;
        this.strategyType = strategyType;
        this.elevators = new ArrayList<>();
        elevatorThreads = new ArrayList<>();

        for(int i = 0; i < numberOfElevator; i++) {
            Elevator elevator = new Elevator(i + 1, capacityOfEachElevator);
            elevators.add(elevator);
            Thread thread = new Thread(elevator);
            elevatorThreads.add(thread);
            thread.start();
        }
    }

    public void handleRequest(Request request) {
        elevatorSelectionStrategy = ElevatorSelectionStrategyFactory.getElevatorSelectionStrategy(strategyType);
        Elevator bestElevator = elevatorSelectionStrategy.slectElevator(elevators, request);

        if(bestElevator == null) {
            strategyType = strategyType.equals("proximity") ? "direction":"proximity";
            elevatorSelectionStrategy = ElevatorSelectionStrategyFactory.getElevatorSelectionStrategy(strategyType);
            bestElevator = elevatorSelectionStrategy.slectElevator(elevators, request);
        }

        if(bestElevator != null) {
            bestElevator.addRequest(request);
        } else {
            System.out.println("Elevator is not available for request: " + request);
        }
    }

    public void stopElevators() {
        for(Elevator elevator : elevators) {
            elevator.stop();
        }
    }

    public void waitForElevator() {
        for(Thread thread : elevatorThreads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
