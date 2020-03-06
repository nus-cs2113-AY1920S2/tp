package seedu.event;

import java.util.ArrayList;

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
        this.name = "unnamed";
        this.datetime = null;
        this.venue = null;
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
        this.name = name;
        this.datetime = datetime;
        this.venue = venue;
    }

    /**
     * Returns the name of the event.
     * @return the name of the event
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the event.
     * @param name the new name for the event
     */
    public void setName(String name) {
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