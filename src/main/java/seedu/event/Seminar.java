package seedu.event;

import seedu.exception.DukeException;

import java.security.InvalidParameterException;
import java.time.Instant;

public class Seminar extends Event {
    private String name;
    private DateTime datetime;
    private String venue;

    public Seminar() throws DukeException {
        //super();
        setName("");
        setDatetime("");
        setVenue("");
    }

    public Seminar(String name, String datetime, String venue) throws DukeException {
        //super(name, datetime, venue);
        setName(name);
        setDatetime(datetime);
        setVenue(venue);
    }

    @Override
    public void setName(String name) throws InvalidParameterException {
        if (this.name == null || this.name.isEmpty()) {
            // if original name is empty or null
            if (name == null || name.isEmpty()) {
                // if new name is empty or null
                this.name = "seminar_" + Instant.now().getEpochSecond();
            } else {
                this.name = name;
            }
        }
    }

    @Override
    public String toString() {
        return "Seminar: " + getName() + "at: " + getDatetime() + ", " + getVenue();
    }
}
