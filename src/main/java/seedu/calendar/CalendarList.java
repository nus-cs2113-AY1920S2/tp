package seedu.calendar;

import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;
import java.util.ArrayList;

public class CalendarList {

    public CalendarList() {
    }

    /**
     * Gets events that fall under the first semester and the correct year.
     *
     * @param eventList List containing all the events.
     * @param year Year that the user wants to see the events under.
     * @throws DukeException If the event list is empty.
     */
    public static void getFirstSemester(EventList eventList, Integer year) throws DukeException {
        ArrayList<Event> semesterList = new ArrayList<>();
        ArrayList<Event> yearList = new ArrayList<>();
        semesterList = getSemesterOneEvents(eventList, semesterList);
        if (semesterList.size() == 0) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        yearList = academicYearEvents(semesterList, yearList, year);
        UI.printCalendar(yearList, year, year + 1, 1);
    }

    /**
     * Gets events that fall under semester one only, ie. Aug - Dec
     *
     * @param eventList List containing all the events.
     * @param list Empty list for correct events to be added.
     * @return List with events that fall under semester one.
     * @throws DukeException If it is unable to find the index of the event.
     */
    private static ArrayList<Event> getSemesterOneEvents(EventList eventList, ArrayList<Event> list)
            throws DukeException {
        for (int i = 0; i < eventList.list.size(); i++) {
            Event event = eventList.find(i);
            if (event.getMonth() > 7 && event.getMonth() < 13) {
                list.add(event);
            }
        }
        return list;
    }

    /**
     * Gets events that fall under the second semester and the correct year.
     *
     * @param eventList List containing all the events.
     * @param year Year that the user wants to see the events under.
     * @throws DukeException If the event list is empty.
     */
    public static void getSecondSemester(EventList eventList, Integer year) throws DukeException {
        ArrayList<Event> semesterList = new ArrayList<>();
        ArrayList<Event> yearList = new ArrayList<>();
        semesterList = getSemesterTwoEvents(eventList, semesterList);
        if (semesterList.size() == 0) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        yearList = academicYearEvents(semesterList, yearList, year);
        UI.printCalendar(yearList, year - 1, year,  2);
    }

    /**
     *  Gets events that fall under semester two only, ie. Jan - Jul
     *
     * @param eventList List containing all the events.
     * @param list Empty list for correct events to be added.
     * @return List with events that fall under semester two.
     * @throws DukeException If it is unable to find the index of the event.
     */
    private static ArrayList<Event> getSemesterTwoEvents(EventList eventList, ArrayList<Event> list)
            throws DukeException {
        for (int i = 0; i < eventList.list.size(); i++) {
            Event event = eventList.find(i);
            if (event.getMonth() > 0 && event.getMonth() < 8) {
                list.add(event);
            }
        }
        return list;
    }

    /**
     * Gets events that fall under the correct year. i.e sem 1 of ay/18-19 is in 2018.
     *
     * @param semesterList List of events that fall under the correct semester.
     * @param yearList Empty list for events of the correct year to be added.
     * @param year Year that the user wants to see the events under.
     * @return List with events that fall under the correct year.
     */
    private static ArrayList<Event> academicYearEvents(ArrayList<Event> semesterList, ArrayList<Event> yearList,
                                                       Integer year) {
        for (int i = 0; i < semesterList.size(); i++) {
            Event event = semesterList.get(i);
            if (event.getYear() == year) {
                yearList.add(event);
            }
        }
        return yearList;
    }

}
