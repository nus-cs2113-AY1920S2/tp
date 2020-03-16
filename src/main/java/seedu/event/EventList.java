package seedu.event;

import seedu.exception.DukeException;
import seedu.ui.UI;

import java.util.ArrayList;

public class EventList {
    public ArrayList<Event> list;

    /**
     * Empty constructor. Creates an empty list.
     */
    public EventList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add the specified event to the end of list.
     * @param event the event to be appended
     */
    public void add(Event event) {
        list.add(event);
        UI.addEventMessage("Event", event.getName());
    }

    /**
     * Delete the first instance of the specified event in the list.
     * Returns {@code true} if the specified event is found (and removed)
     * from the list.
     * @param event the event to be deleted
     * @return {@code true} if the specified event is found (and removed)
     *      from the list
     */
    public boolean delete(Event event) {
        return list.remove(event);
    }

    /**
     * Removes the event at the specified position in this list.
     * Shifts any subsequent events to the left (subtracts one from their indices).
     * @param index the index of the element to be removed
     * @return the removed event
     */
    public Event delete(int index) {
        UI.deleteEventMessage("Event", list.get(index).getName());
        Event removedEvent = list.remove(index);
        return removedEvent;
    }

    /**
     * Returns the event at the specified position in this list.
     * @param index index of the event to find
     * @return the event in the specified position
     */
    public Event find(int index) {
        return list.get(index);
    }

    /**
     * Change the name of the event in the specified index.
     * @param index index of the event
     * @param name new name for the event
     */
    public void editName(int index, String name) throws DukeException {
        Event event = this.find(index);
        event.setName(name);
    }

    /**
     * Change the datetime of the event in the specified index.
     * @param index index of the event
     * @param datetime new datetime for the event
     */
    public void editDatetime(int index, String datetime) {
        Event event = this.find(index);
        event.setDatetime(datetime);
    }

    /**
     * Change the venue of the event in the specified index.
     * @param index index of the event
     * @param venue new venue for the event
     */
    public void editVenue(int index, String venue) {
        Event event = this.find(index);
        event.setVenue(venue);
    }

    public void editEvent(int index, Event event) {
        list.remove(index);
        list.add(index, event);
    }

    public int getSize() {
        return list.size();
    }

}
