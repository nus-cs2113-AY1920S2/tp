package seedu.parser;

import seedu.event.Event;
import seedu.exception.DukeException;
import seedu.attendance.Attendance;

public class ParserStorage {


    /**
     * Parses an event from String format back to task.
     *
     * @param line description of the event.
     * @return The corresponding task object.
     * @throws DukeException If the line is corrupted.
     */

    public static Attendance createTaskFromStorageAttendance(String line) throws DukeException {
        String[] taskParts = line.split("\\|");
        try {
            String nameOfModule = taskParts[0].strip();
            String nameOfStudent = taskParts[0].strip();
            String description = taskParts[1].strip();
            String hasAttended = taskParts[2].strip();
            Attendance attendance = new Attendance(nameOfModule, nameOfStudent, description, hasAttended);
            return attendance;
        } catch (Exception e) {
            throw new DukeException("CORRUPTED_TASK");
        }
    }

    /**
     * Parses an event from String format back to task.
     *
     * @param line description of the event.
     * @return The corresponding task object.
     * @throws DukeException If the line is corrupted.
     */

    public static Event createTaskFromStorageEvent(String line) throws DukeException {
        String[] taskParts = line.split("\\|");
        try {
            String name = taskParts[0].strip();
            String datetime = taskParts[1].strip();
            String venue = taskParts[2].strip();
            Event event = new Event(name,datetime,venue);
            return event;
        } catch (Exception e) {
            throw new DukeException("CORRUPTED_TASK");
        }
    }

    /**
     * Parses an event from events to String format.
     *
     * @param event The event.
     * @return The corresponding String format of the task object.
     */

    public static String toStorageStringEvent(Event event) {
        return event.getName() + " | " + event.getDatetime() + " | " + event.getVenue();
    }

    public static String toStorageStringAttendance(Attendance attendance) {
        return attendance.getStudent() + " | " + attendance.getDescription() + " | " + attendance.getAttendance();
    }
}
