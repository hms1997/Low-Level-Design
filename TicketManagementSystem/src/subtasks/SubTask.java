package subtasks;

import enums.Status;
import tickets.Ticket;

public class SubTask {
    private String id;
    private String title;
    private Status status;
    private final String parentId;

    public SubTask(String id, String title, String parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.status = Status.OPEN;
    }

    public void updateStatus(Status newStatus, Ticket parent) {
        this.status = newStatus;
    }

    public Status getStatus() {
        return status;
    }

    public String getParentId() {
        return parentId;
    }
}
