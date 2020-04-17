package model.meeting;

import java.time.LocalTime;

/**
 * This class contains information of a meeting scheduled by the user.
 */
public class Meeting {
    private String meetingName;
    private Integer startDay;
    private LocalTime startTime;
    private Integer endDay;
    private LocalTime endTime;
    private int startDate;
    private int endDate;


    public Meeting(String meetingName, Integer startDay, LocalTime startTime,
                   Integer endDay, LocalTime endTime, int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }
}
