import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import static common.Messages.MESSAGE_STARTENDDAY_OUT_OF_RANGE;
import static common.Messages.MESSAGE_STARTENDTIME_WRONG_FORMAT;

/**
 * TESTING SUMMARY DOC.
 */
public class Meeting {
    private String meetingName;
    private Integer startDay;
    private LocalTime startTime;
    private Integer endDay;
    private LocalTime endTime;

    public Meeting(String meetingName, Integer startDay, LocalTime startTime, Integer endDay, LocalTime endTime) throws MoException {

        if (startDay < 0 || startDay > 6 || endDay < 0 || endDay > 6) {
            throw new MoException(MESSAGE_STARTENDDAY_OUT_OF_RANGE);
        }

        if ((startTime.getMinute() != 0 && startTime.getMinute() != 30) || (endTime.getMinute() != 0 && endTime.getMinute() != 30)) {
            throw new MoException(MESSAGE_STARTENDTIME_WRONG_FORMAT);
        }

        this.meetingName = meetingName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void editTime() {

    }

    public String toString() {
        return meetingName + ", " + startDay + ", " + startTime + ", " + endDay + ", " + endTime;
    }
}
