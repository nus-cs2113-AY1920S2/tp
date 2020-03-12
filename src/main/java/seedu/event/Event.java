package seedu.event;

import seedu.duke.Duke;
import seedu.exception.DukeException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.time.Instant;

public class Event {
    private String name;
    private DateTime datetime;
    private String venue;
    private ArrayList<String> participantList;
    private ArrayList<String> attendanceList;

    /**
     * Empty constructor. Sets name as "unnamed"
     */
    public Event() {
        setName("");
        setDatetime("");
        setVenue("");
        this.participantList = new ArrayList<>();
        this.attendanceList = new ArrayList<>();
    }

    /**
     * Constructor with name, datetime, venue provided.
     * @param name name of event
     * @param datetime datetime of event
     * @param venue venue of event
     */
    public Event(String name, String datetime, String venue) {
        this();
        setName(name);
        setDatetime(datetime);
        setVenue(venue);
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
     * @throws InvalidParameterException when trying to overwrite a non-empty
     *      and non-null name with an empty or null name
     */
    public void setName(String name) throws InvalidParameterException {
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
                throw new InvalidParameterException("empty name");
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
        return datetime.getDateTimeFormat();
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

    public String toString() {
        return "[E] + " + getName() + getDatetime() + getVenue();
    }
}