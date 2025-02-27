package meetings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Meeting {
    private String metingName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<String> attendees;

    public Meeting(String metingName, LocalDateTime startTime, LocalDateTime endTime) {
        this.metingName = metingName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendees = new ArrayList<>();
    }

    public String getMetingName() {
        return metingName;
    }

    public void setMetingName(String metingName) {
        this.metingName = metingName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void addAttendees(String attendee) {
        attendees.add(attendee);
    }

    @Override
    public String toString() {
        return "Meeting Name: " + this.getMetingName() + " Start Time " + this.startTime +
                " End Time: " + this.endTime;
    }
}
