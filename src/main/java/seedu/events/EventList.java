package seedu.events;

import seedu.exception.EscException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the entire list of events.
 */
public class EventList {
    private ArrayList<Event> events;

    public EventList() {
        this.events = new ArrayList<Event>();
    }

    public EventList(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }

    /**
     * Adds an event to the list.
     * @param event Event to be added.
     */
    public void addEvent(Event event) throws EscException {
        events.add(event);
        System.out.println(event.getTopic() + " has been added.\n");
        listUpcoming();
    }

    /**
     * Removes an event from the list.
     * @param index Index of event to be removed.
     * @throws EscException if list of events is empty or event item at index does not exit.
     */
    public void removeEvent(int index) throws EscException {
        if (this.events.size() == 0) {
            throw new EscException("The event list is empty.");
        }
        try {
            System.out.println(events.get(index).getTopic() + " has been removed.");
            events.remove(index);
            listUpcoming();
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The event item does not exist.");
        }
    }

    /**
     * Lists all upcoming events.
     * @param dateRange Date range of upcoming events to show.
     */
    public void listUpcoming(int... dateRange) {
        if (this.events.size() == 0) {
            System.out.println("No stored events.");
            return;
        }
        ArrayList<Event> listToShow;
        if (dateRange.length == 0) {
            System.out.println("Here is the list of events.");
            listToShow = this.events;
        } else {
            System.out.println("Here is the list of upcoming events within " + dateRange[0] + " day(s).");
            listToShow = findUpcoming(dateRange[0]);
        }

        if (listToShow.isEmpty()) {
            System.out.println("No upcoming events within " + dateRange[0] + " day(s).");
        } else {
            for (int i = 0; i < listToShow.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + listToShow.get(i).toString());
            }
        }

    }

    /**
     * Finds upcoming events within the specified date range.
     * @param dateRange Date range to find upcoming events.
     * @return Upcoming events within the date range.
     */
    public ArrayList<Event> findUpcoming(int dateRange) {
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Event event : events) {
            LocalDate date = event.getDate();
            long period = today.until(date, ChronoUnit.DAYS);
            if (period <= dateRange && period >= 0) {
                upcomingEvents.add(event);
            }
        }

        Collections.sort(upcomingEvents);
        return upcomingEvents;
    }

}
