package seedu.event;

import seedu.exception.DukeException;
import seedu.ui.UI;
import java.util.ArrayList;

public class CalendarList {

    public CalendarList() {
    }

    public static void getFirstSemester(EventList eventList, Integer year) throws DukeException {
        ArrayList<Event> semesterList = new ArrayList<>();
        ArrayList<Event> yearList = new ArrayList<>();
        semesterList = getSemesterOneEvents(eventList, semesterList);
        if (semesterList.size() == 0) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        yearList = academicYearEvents(semesterList, yearList, year);
        UI.printCalendar(yearList, year + 1, year, 1);
    }

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

    public static void getSecondSemester(EventList eventList, Integer year) throws DukeException {
        ArrayList<Event> semesterList = new ArrayList<>();
        ArrayList<Event> yearList = new ArrayList<>();
        semesterList = getSemesterTwoEvents(eventList, semesterList);
        if (semesterList.size() == 0) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        yearList = academicYearEvents(semesterList, yearList, year);
        UI.printCalendar(yearList, year, year - 1,  2);
    }

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
