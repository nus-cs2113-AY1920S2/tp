package seedu.event;

import seedu.attendance.AttendanceList;
import seedu.exception.DukeException;
import seedu.performance.PerformanceList;
import java.time.Instant;

public class Event {
    protected String name;
    protected DateTime datetime;
    protected String venue;
    //private ArrayList<StudentList> studentsList;
    protected AttendanceList attendanceList;
    protected PerformanceList performanceList;


    /**
     * Empty constructor. Sets name as "unnamed"
     */
    public Event() throws DukeException {
        setName("");
        setDatetime("");
        setVenue("");
        this.attendanceList = new AttendanceList();
        this.performanceList = new PerformanceList();
    }

    /**
     * Constructor with name, datetime, venue provided.
     * @param name name of event
     * @param datetime datetime of event
     * @param venue venue of event
     */
    public Event(String name, String datetime, String venue) throws DukeException {
        setName(name);
        setDatetime(datetime);
        setVenue(venue);
        this.attendanceList = new AttendanceList();
        this.performanceList = new PerformanceList();
    }

    /**
     * Returns the name of the event.
     * @return the name of the event
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the event. If the specified name is empty or {@code null},
     * and the original name is also empty or {@code null},
     * the name will take the form: event_(secondsSinceEpoch)
     * @param name the new name for the event
     * @throws DukeException when trying to overwrite a non-empty
     *      and non-null name with an empty or null name
     */
    public void setName(String name) throws DukeException {
        if (this.name == null || this.name.isEmpty()) {
            // if original name is empty or null
            if (name == null || name.isEmpty()) {
                // if new name is empty or null
                this.name = "event_" + Instant.now().getEpochSecond();
            } else {
                this.name = name;
            }
        } else {
            // if original name is not empty and null
            if (name == null || name.isEmpty()) {
                // if new name is empty or null
                throw new DukeException("Empty name");
            } else {
                // if new name is not empty and not null
                this.name = name;
            }
        }
    }

    /**
     * Returns the datetime of the event.
     * @return the datetime of the event
     */
    public String getDatetime() {
        return datetime.toString();
    }

    /**
     * Sets the datetime of the event.
     * @param datetime the new datetime for the event
     */
    public void setDatetime(String datetime) {
        this.datetime = new DateTime(datetime);
    }

    /**
     * Returns the venue of the event.
     * @return the venue of the event
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Sets the venue of the event.
     * @param venue the new venue for the event
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    public PerformanceList getPerformanceList() {
        return performanceList;
    }

    @Override
    public String toString() {
        if (getDatetime().equals("yyyy-MM-dd HHmm") && getVenue().equals("")) {
            return "Event: " + getName();
        } else if (getVenue().equals("")) {
            return "Event: " + getName() + ", time: " + getDatetime();
        }
        return "Event: " + getName() + ", time: " + getDatetime() + ", venue: " + getVenue();
    }
}