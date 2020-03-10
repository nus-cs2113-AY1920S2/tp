import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting {
    //ArrayList<TeamMember> myMembers = new ArrayList<>();
    private String meetingName;
    private LocalDate meetingDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(String meetingName, LocalDate meetingDate, LocalTime startTime, LocalTime endTime) {
        this.meetingName = meetingName;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void editTime() {

    }
    public String toString() {
        return meetingName + ", " + meetingDate + ", " + startTime + ", " + endTime;
    }
}
