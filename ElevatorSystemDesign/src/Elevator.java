import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator implements Runnable{
    private int id;
    private int totalCapacity;
    private int currentCapacity;
    private int currentFloor;
    private Direction direction;
    private PriorityQueue<Request> requests;
    private boolean isRunning;


    public Elevator(int id, int capacity) {
        this.id = id;
        this.totalCapacity = capacity;
        this.currentCapacity = 0;
        this.currentFloor = 0;
        this.isRunning = true;
        this.direction = Direction.IDEAL;
        this.requests = new PriorityQueue<>((r1, r2) -> {
            int distance1 = Math.abs(currentFloor - r1.getSourceFloor());
            int distance2 = Math.abs(currentFloor - r2.getSourceFloor());

            return Integer.compare(distance1, distance2);
        });
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public synchronized void addRequest(Request request) {
        if(currentCapacity < totalCapacity) {
            requests.add(request);
            System.out.println("Request is added to elevator: " + id);
        } else {
            System.out.println("Capacity is full");
        }
    }

    private synchronized void processRequest() {
        Request request = requests.poll();
        if(request != null) {
            int sourceFloor = request.getSourceFloor();
            int destinationFloor = request.getDestinationFloor();

            //go to source floor
            moveToTargetFloor(sourceFloor);
            System.out.println("Elevator: " + id + " picked up request at floor: " + sourceFloor);
            //go to destination floor
            moveToTargetFloor(destinationFloor);
            System.out.println("Elevator: " + id + " picked up request at floor: " + destinationFloor);
        }
    }

    private void moveToTargetFloor(int targetFloor) {
        while(currentFloor != targetFloor) {
            if(targetFloor > currentFloor) {
                currentFloor++;
                direction = Direction.UP;
            } else {
                currentFloor--;
                direction = Direction.DOWN;
            }
            System.out.println("Elevator: " + id + " is at floor: " + currentFloor);
            try {
                Thread.sleep(600);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        direction = Direction.IDEAL;
    }

    @Override
    public void run() {
        while(isRunning) {
            processRequest();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Elevator: " + id + " has stopped!");
    }

    //stop the elevator thread
    public void stop() {
        isRunning = false;
    }
}
