package seedu.parser;

import seedu.event.Event;
import seedu.exception.DukeException;

public class ParserStorage {

    /**
     * Parses an event from String format back to task.
     *
     * @param line description of the event.
     * @return The corresponding task object.
     * @throws DukeException If the line is corrupted.
     */

//    public static Event createTaskFromStorage(String line) throws DukeException {
//        String[] taskParts = line.split("\\|");
//        try {
//            String name = taskParts[0].strip();
//            String datetime = taskParts[1].strip();
//            String venue = taskParts[2].strip();
//            Event event = new Event(name,datetime,venue);
//            return event;
//        } catch (Exception e) {
//            throw new DukeException("CORRUPTED_TASK");
//        }
//    }

    /**
     * Parses an event from events to String format.
     *
     * @param event The event.
     * @return The corresponding String format of the task object.
     */

    public static String toStorageString(Event event) {
        return event.getName() + " | " + event.getDatetime() + " | " + event.getVenue();
    }

}
