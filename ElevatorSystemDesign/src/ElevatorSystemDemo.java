import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ElevatorSystemDemo {
    private static Scanner scanner = new Scanner(System.in);
    private static ElevatorSystem elevatorSystem = ElevatorSystem.getElevatorSystem();
    public static void main(String[] args) {
        System.out.println("***Elevator System Demo***");
        System.out.println("Enter the number of elevator: ");
        int numberOfElevator = scanner.nextInt();
        System.out.println("Enter the capacity of each Elevator: ");
        int capacity = scanner.nextInt();
        // Consume the newline character left in the buffer
        scanner.nextLine(); // Add this line
        System.out.println("Enter the strategy type: (proximity/direction) : ");
        String strategyType = scanner.nextLine();

        elevatorSystem.createElevatorSystem(numberOfElevator, capacity, strategyType);

        while(true) {
            System.out.println("======MENU======");
            System.out.println("1. Add request");
            System.out.println("2. Exit System");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addRequest();
                case 2 -> exitSystem();
                default -> System.out.println("invalid choice");
            }
        }
    }

    private static void addRequest() {
        System.out.println("Enter the source floor: ");
        int sourceFloor = scanner.nextInt();;
        System.out.println("Enter the destination floor: ");
        int destinationFloor = scanner.nextInt();
        if(sourceFloor == destinationFloor) {
            System.out.println("source floor and destination floor is same, try again");
        } else {
            Request request = new Request(sourceFloor, destinationFloor);
            elevatorSystem.handleRequest(request);
            System.out.println("Request added into the system");
        }
    }

    private static void exitSystem() {
        System.out.println("Stopping all elevators");
        elevatorSystem.stopElevators();;
        elevatorSystem.waitForElevator();;
        System.out.println("All elevators has stopped");
        scanner.close();
    }
}