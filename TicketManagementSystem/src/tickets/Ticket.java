package tickets;

import enums.Status;
import metadata.Comment;
import subtasks.SubTask;

import java.util.*;

public abstract class Ticket {
    protected String id;
    protected String title;
    protected String description;
    protected Status status;
    protected List<SubTask> subTasks = new ArrayList<>();
    protected List<Comment> comments = new ArrayList<>();

    public Ticket(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = Status.OPEN;
    }

    public abstract Status getNextState();
    public abstract Status getFinalStatus();

    public abstract List<Status> getFlow();

    public void updateStatus(Status newStatus) {
        if (!newStatus.equals(getNextState())) {
            throw new IllegalArgumentException("Invalid status transition");
        }

        if (newStatus == getFinalStatus() && !canClose()) {
            throw new IllegalStateException("Cannot close ticket with incomplete sub-tasks");
        }

        this.status = newStatus;
    }

    public boolean canClose() {
        return subTasks.stream().allMatch(st -> st.getStatus() == getFinalStatus());
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
    }

    public Status getStatus() {
        return status;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        comments.add(comment);
    }
}
