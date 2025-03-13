package manager;

import enums.Status;
import factory.TicketFactory;
import metadata.Comment;
import subtasks.SubTask;
import tickets.Epic;
import tickets.OnCall;
import tickets.Story;
import tickets.Ticket;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class TicketManager {
    private static TicketManager ticketManager = null;
    /**
     * mapping of ticket id and ticket
     */
    private final Map<String, Ticket> tickets = new HashMap<>();
    /**
     * mapping of subtask id and subtask
     */
    private final Map<String, SubTask> subTasks = new HashMap<>();

    /**
     * List contain all the ticket ids of current sprint
     */
    private final List<String> currentSprint = new ArrayList<>();
    private final AtomicLong ticketCounter = new AtomicLong();
    private final AtomicLong subTaskCounter = new AtomicLong();

    private TicketManager(){

    }

    public static TicketManager getTicketManager() {
        if(ticketManager == null) {
            synchronized (TicketManager.class) {
                if(ticketManager == null) {
                    ticketManager = new TicketManager();
                }
            }
        }
        return ticketManager;
    }

    public String createTicket(String type, String title, String description) {
        String id = "T" + ticketCounter.incrementAndGet();
        Ticket ticket = TicketFactory.createTicket(type, id, title, description);
        tickets.put(id, ticket);
        return id;
    }

    public void updateStatus(String ticketId, Status newStatus) {
        Ticket ticket = tickets.get(ticketId);
        if(ticket == null ) {
            throw new IllegalArgumentException("Ticket not found");
        }
        ticket.updateStatus(newStatus);
    }

    public String addSubTask(String parentId, String title) {
        Ticket parent = tickets.get(parentId);
        if (parent == null) throw new IllegalArgumentException("Parent not found");

        String id = "ST" + subTaskCounter.incrementAndGet();
        SubTask subTask = new SubTask(id, title, parentId);
        parent.addSubTask(subTask);
        subTasks.put(id, subTask);
        return id;
    }

    public void addToSprint(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (!(ticket instanceof Story)) {
            throw new IllegalArgumentException("Only stories can be added to sprint");
        }
        currentSprint.add(ticketId);
    }

    public void removeFromSprint(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if(!(ticket instanceof Story)) {
            throw new IllegalArgumentException("Ticket not a story");
        }
        if (!currentSprint.remove(ticketId)) {
            throw new IllegalArgumentException("Ticket not in current sprint");
        }
    }

    public void removeSubTask(String subTaskId) {
        SubTask subTask = subTasks.remove(subTaskId);
        if (subTask == null) {
            throw new IllegalArgumentException("Sub-task not found");
        }

        Ticket parent = tickets.get(subTask.getParentId());
        if (parent != null) {
            parent.removeSubTask(subTask);
        }
    }

    public void updateSubTaskStatus(String subTaskId, Status newStatus) {
        SubTask subTask = subTasks.get(subTaskId);
        if (subTask == null) {
            throw new IllegalArgumentException("Sub-task not found");
        }

        Ticket parent = tickets.get(subTask.getParentId());
        if (parent == null) {
            throw new IllegalStateException("Parent ticket not found");
        }

        // Correct validation: Check if newStatus is a valid next state for the parent
        List<Status> flow = parent.getFlow();
        int currentIndex = flow.indexOf(subTask.getStatus());
        System.out.println("Current Index: " + currentIndex);
        int newIndex = flow.indexOf(newStatus);
        System.out.println("New index: " + newIndex);

        if (currentIndex < 0) {
            throw new IllegalStateException("Parent ticket status not found in flow");
        }

        System.out.println(currentIndex + " " + newIndex);

        if(newIndex == currentIndex + 1) {
            subTask.updateStatus(newStatus, parent);
        } else {
            throw new IllegalArgumentException("Invalid status transition");
        }
    }

    // Helper method to get the status flow for a ticket
    private List<Status> getFlow(Ticket ticket) {
        if (ticket instanceof Story) {
            return List.of(Status.OPEN, Status.IN_PROGRESS, Status.TESTING, Status.IN_REVIEW, Status.DEPLOYED);
        } else if (ticket instanceof Epic) {
            return List.of(Status.OPEN, Status.IN_PROGRESS, Status.COMPLETED, Status.CLOSED);
        } else if (ticket instanceof OnCall) {
            return List.of(Status.OPEN, Status.IN_PROGRESS, Status.RESOLVED, Status.CLOSED);
        } else {
            throw new IllegalStateException("Unknown ticket type");
        }
    }

    public Status getTicketStatus(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found");
        }
        return ticket.getStatus();
    }

    public List<String> getCurrentSprintStories() {
        return new ArrayList<>(currentSprint);
    }

    public List<SubTask> getSubTasks(String parentId) {
        Ticket ticket = tickets.get(parentId);
        if (ticket == null) return Collections.emptyList();
        return ticket.getSubTasks();
    }

    public void addComment(String ticketId, String commentDescription) {
        Ticket ticket = tickets.get(ticketId);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Comment comment = new Comment(commentDescription, currentDateTime);
        if (ticket != null) {
            ticket.addComments(comment);
        }
    }

    public List<Comment> getComments(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        return ticket != null ? ticket.getComments() : Collections.emptyList();
    }
}
