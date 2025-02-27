package meetings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MeetingScheduler {
    private List<MeetingRoom> meetingRooms;

    public MeetingScheduler() {
        meetingRooms = new ArrayList<>();
    }

    public void addMeetingRoom(MeetingRoom meetingRoom) {
        meetingRooms.add(meetingRoom);
    }

    public void scheduleMeeting(String meetingRoomName, String meetingTitle, LocalDateTime startTime, LocalDateTime endTime,
                                List<String> attendees) {
        MeetingRoom meetingRoom = getMeetingRoom(meetingRoomName);
        if(meetingRoom != null) {
            if(meetingRoom.isAvailable(startTime, endTime)) {
                Meeting meeting = new Meeting(meetingTitle, startTime, endTime);
                for(String attendee : attendees) {
                    meeting.addAttendees(attendee);
                }
                meetingRoom.addMeetings(meeting);
                System.out.println("Meeting " + meeting.getMetingName() + " is successfully scheduled");
            } else {
                System.out.println("Error: Meeting room is not available for the given time slot.");
            }
        } else {
            System.out.println("Error: Meeting room not found");
        }
    }

    private MeetingRoom getMeetingRoom(String meetingRoomName) {
        System.out.println("meeting room trying to find: " + meetingRoomName);
        for(MeetingRoom meetingRoom : meetingRooms) {
            System.out.println("available meeting rooms: " + meetingRoom.getName());
            if(meetingRoom.getName().equals(meetingRoomName)) {
                return meetingRoom;
            }
        }
        return null;
    }

    public void viewAllMeetings() {
        for(MeetingRoom meetingRoom : meetingRooms) {
            List<Meeting> meetings = meetingRoom.getMeetings();
            for(Meeting meeting : meetings) {
                System.out.println("Meeting room name: " + meetingRoom.getName());
                System.out.println(meeting);
            }
        }
    }
}
