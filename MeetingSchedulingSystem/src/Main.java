import meetings.MeetingRoom;
import meetings.MeetingScheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static MeetingScheduler scheduler = new MeetingScheduler();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Meeting Scheduler System ===");
            System.out.println("1. Add Meeting Room");
            System.out.println("2. Schedule Meeting");
            System.out.println("3. View All Meetings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addMeetingRoom();
                case 2 -> scheduleMeeting();
                case 3 -> viewAllMeetings();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addMeetingRoom() {
        System.out.print("Enter Meeting Room Name: ");
        String roomName = scanner.nextLine();
        MeetingRoom meetingRoom = new MeetingRoom(roomName);
        scheduler.addMeetingRoom(meetingRoom);
    }

    private static void scheduleMeeting() {
        System.out.print("Enter Meeting Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Meeting Room Name: ");
        String room = scanner.nextLine();

        System.out.println("Enter start time in the format 'yyyy-MM-ddTHH:mm:ss' (e.g., 2024-03-01T10:00:00):");
        String startTimeInput = scanner.nextLine();
        LocalDateTime startTime = LocalDateTime.parse(startTimeInput);

        System.out.println("Enter end time in the format 'yyyy-MM-ddTHH:mm:ss' (e.g., 2024-03-01T10:00:00):");
        String input = scanner.nextLine();
        LocalDateTime endTime = LocalDateTime.parse(input);

        System.out.print("Enter Participants (comma separated): ");
        List<String> participants = Arrays.asList(scanner.nextLine().split(","));

        scheduler.scheduleMeeting(room, title, startTime, endTime, participants);
    }

    public static void viewAllMeetings() {
        scheduler.viewAllMeetings();
    }
}