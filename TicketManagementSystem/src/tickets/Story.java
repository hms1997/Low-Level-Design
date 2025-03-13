package tickets;

import enums.Status;

import java.util.List;

public class Story extends Ticket {
    private List<Status> FLOW = List.of(Status.OPEN, Status.IN_PROGRESS, Status.TESTING, Status.IN_REVIEW, Status.DEPLOYED
    );

    public Story(String id, String title, String description) {
        super(id, title, description);
    }

    public List<Status> getFlow() {
        return FLOW;
    }

    @Override
    public Status getNextState() {
        int index = FLOW.indexOf(status);
        System.out.println("Index: " + index);
        return index < FLOW.size() - 1 ? FLOW.get(index + 1) : null;
    }

    @Override
    public Status getFinalStatus() {
        return Status.DEPLOYED;
    }
}
