package meetings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    private String name;
    private List<Meeting> meetings;

    public MeetingRoom(String name) {
        this.name = name;
        this.meetings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void addMeetings(Meeting meeting) {
        meetings.add(meeting);
    }

    public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        for(Meeting meeting : meetings) {
            if(startTime.isBefore(meeting.getEndTime()) && endTime.isAfter(meeting.getStartTime())) {
                return false;
            }
        }
        return true;
    }
}
