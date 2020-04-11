package seedu.events;

import java.io.Serializable;
import java.time.LocalDate;

public class Event implements Comparable<Event>, Serializable {
    private String topic;
    private LocalDate eventDate;

    public Event(String topic, LocalDate eventDate) {
        this.topic = topic;
        this.eventDate = eventDate;
    }

    public String getTopic() {
        return this.topic;
    }

    public LocalDate getDate() {
        return this.eventDate;
    }

    /**
     * Custom comparable between two events. Sorted by date.
     *
     * @param o Other event to compare against.
     * @return Integer depicting result of comparison.
     */
    @Override
    public int compareTo(Event o) {
        return this.eventDate.compareTo(o.getDate());
    }

    public String toString() {
        return this.topic + ": "  + this.eventDate.toString();
    }

}
