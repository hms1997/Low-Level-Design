import enums.Status;
import manager.TicketManager;

public class SystemDriver {
    public static void main(String[] args) {
        TicketManager manager = TicketManager.getTicketManager();

        // Test 1: Create different ticket types
        String storyId = manager.createTicket("STORY", "Login Feature", "User authentication implementation");
        String epicId = manager.createTicket("EPIC", "Auth System", "Authentication framework");
        String onCallId = manager.createTicket("ON-CALL", "Server Down", "Emergency server maintenance");

        System.out.println("=== Ticket Creation Test ===");
        System.out.println("Story ID: " + storyId + " (Expected: T1)");
        System.out.println("Epic ID: " + epicId + " (Expected: T2)");
        System.out.println("OnCall ID: " + onCallId + " (Expected: T3)");

        // Test 2: Validate initial statuses
        System.out.println("\n=== Initial Status Validation ===");
        validateStatus(manager, storyId, Status.OPEN);
        validateStatus(manager, epicId, Status.OPEN);
        validateStatus(manager, onCallId, Status.OPEN);

        // Test 3: Valid status transitions
        System.out.println("\n=== Valid Status Transitions ===");
        testValidTransition(manager, storyId, Status.IN_PROGRESS);
        testValidTransition(manager, storyId, Status.TESTING);
        testValidTransition(manager, epicId, Status.IN_PROGRESS);
        testValidTransition(manager, onCallId, Status.IN_PROGRESS);

        // Test 4: Invalid status transitions
        System.out.println("\n=== Invalid Status Transitions ===");
        testInvalidTransition(manager, storyId, Status.COMPLETED); // Invalid for Story
        testInvalidTransition(manager, epicId, Status.TESTING);    // Invalid for Epic
        testInvalidTransition(manager, onCallId, Status.DEPLOYED); // Invalid for OnCall

        // Test 5: Sprint management
        System.out.println("\n=== Sprint Management ===");
        manager.addToSprint(storyId);
        System.out.println("Sprint stories after addition: " + manager.getCurrentSprintStories());

        try {
            manager.addToSprint(epicId); // Should fail
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error when adding Epic to sprint: " + e.getMessage());
        }

        manager.removeFromSprint(storyId);
        System.out.println("Sprint stories after removal: " + manager.getCurrentSprintStories());

        // Test 6: Sub-task lifecycle
        System.out.println("\n=== Sub-task Management ===");
        String subTask1 = manager.addSubTask(storyId, "UI Design");
        String subTask2 = manager.addSubTask(storyId, "Backend Implementation");
        String epicSubTask1 = manager.addSubTask(epicId, "implement SSO flow");
        String onCallSubTask1 = manager.addSubTask(storyId, "Fix production bug");
        System.out.println("Created Story sub-tasks: " + subTask1 + ", " + subTask2);
        System.out.println("Created epic sub-tasks: " + epicSubTask1);
        System.out.println("Created On-call sub-tasks: " + onCallSubTask1);

        //change status of subtask
        updateSubTaskStatus(manager, epicSubTask1, Status.COMPLETED);//error
        updateSubTaskStatus(manager, epicSubTask1, Status.IN_PROGRESS);//valid

        //try to close epic but it will fail as its sub task is not completed yet
        testInvalidTransition(manager, epicId, Status.CLOSED);
        //manager.updateStatus(epicId, Status.COMPLETED);
        //manager.updateStatus(epicId, Status.CLOSED);

        // Test 7: Sub-task removal
        manager.removeSubTask(subTask1);
        System.out.println("Sub-tasks after removal: " + manager.getSubTasks(storyId).size() + " (Expected: 1)");

        // Test 8: Comments functionality
        System.out.println("\n=== Comment Management ===");
        manager.addComment(storyId, "Initial discussion with UX team");
        manager.addComment(storyId, "Approved by product owner");
        System.out.println("Comments: " + manager.getComments(storyId));

        // Test 9: Error handling for invalid IDs
        System.out.println("\n=== Error Handling Tests ===");
        testInvalidTicketId(manager, "INVALID_ID");
    }

    private static void validateStatus(TicketManager manager, String ticketId, Status expected) {
        Status actual = manager.getTicketStatus(ticketId);
        System.out.println("Ticket " + ticketId + " status: " + actual +
                " (Expected: " + expected + ") " +
                (actual == expected ? "✓" : "✗"));
    }

    private static void testValidTransition(TicketManager manager, String ticketId, Status newStatus) {
        try {
            manager.updateStatus(ticketId, newStatus);
            System.out.println("Successfully updated " + ticketId + " to " + newStatus);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void testInvalidTransition(TicketManager manager, String ticketId, Status invalidStatus) {
        try {
            manager.updateStatus(ticketId, invalidStatus);
            System.out.println("Unexpected success for invalid transition to " + invalidStatus);
        } catch (Exception e) {
            System.out.println("Properly handled invalid transition: " + e.getMessage());
        }
    }

    private static void testInvalidTicketId(TicketManager manager, String invalidId) {
        try {
            manager.updateStatus(invalidId, Status.IN_PROGRESS);
        } catch (IllegalArgumentException e) {
            System.out.println("Properly handled invalid ticket ID: " + e.getMessage());
        }
    }

    private static void updateSubTaskStatus(TicketManager manager, String subTaskId, Status newStatus) {
        try {
            manager.updateSubTaskStatus(subTaskId, newStatus);
            System.out.println("Subtask: " + subTaskId + " is successfully updated to: " + newStatus);
        } catch (IllegalArgumentException e) {
            System.out.println("Properly handled invalid sub-task transition: " + subTaskId +" " + e.getMessage());
        }
    }
}