package seedu.calendar;

import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<ArrayList<Event>> monthList = new ArrayList<>(6);
        String[] headerMonthDescription = new String[6];
        for (int k = 0; k < 6; k++) {
            monthList.add(new ArrayList<>());
        }

        semesterList = getSemesterOneEvents(eventList, semesterList);
        if (semesterList.size() == 0) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        yearList = academicYearEvents(semesterList, yearList, year);
        monthList = separateMonths(yearList, monthList);
        UI.printCalendar(monthList, year, year + 1, 1);
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
            if (event.getMonth() > 6 && event.getMonth() < 13) {
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
        //UI.printCalendar(yearList, year - 1, year,  2);
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
            if (event.getMonth() > 0 && event.getMonth() < 7) {
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

    private static ArrayList<ArrayList<Event>> separateMonths(ArrayList<Event> yearList, ArrayList<ArrayList<Event>> monthList)
            throws DukeException {
        for (int i = 0; i < yearList.size(); i++) {
            Event event = yearList.get(i);
            int month = event.getMonth();
            switch (month) {
            case 1:
            case 7:
                monthList.get(0).add(event);
                break;
            case 2:
            case 8:
                monthList.get(1).add(event);
                break;
            case 3:
            case 9:
                monthList.get(2).add(event);
                break;
            case 4:
            case 10:
                monthList.get(3).add(event);
                break;
            case 5:
            case 11:
                monthList.get(4).add(event);
                break;
            case 6:
            case 12:
                monthList.get(5).add(event);
                break;
            default:
                throw new DukeException("Month not found");
            }
        }
        return monthList;
    }


}
