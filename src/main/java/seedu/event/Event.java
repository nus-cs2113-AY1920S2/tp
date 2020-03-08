package seedu.event;

import java.util.ArrayList;
import java.time.Instant;

public class Event {
    private String name;
    private String datetime;
    private String venue;
    private ArrayList<String> participantList;
    private ArrayList<String> attendanceList;

    /**
     * Empty constructor. Sets name as "unnamed"
     */
    public Event() {
        setName(null);
        setDatetime(null);
        setVenue(null);
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
     */
    public void setName(String name) {
        if (this.name == null || this.name.isEmpty()) {
            if (name == null || name.isEmpty()) {
                this.name = "event_" + Long.toString(Instant.now().getEpochSecond());
                return;
            }
        }
        this.name = name;
    }

    /**
     * Returns the datetime of the event.
     * @return the datetime of the event
     */
    public String getDatetime() {
        return this.datetime;
    }

    /**
     * Sets the datetime of the event.
     * @param datetime the new datetime for the event
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
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
}