package seedu.command.calendar;

import seedu.event.Event;
import seedu.event.EventList;
import seedu.exception.DukeException;
import seedu.ui.UI;
import java.util.ArrayList;

public class CalendarList {
    private static final int NO_OF_MONTHS = 6;

    public CalendarList() {
    }

    /**
     * Gets events that fall under the first semester and the correct year.
     *
     * @param eventList List containing all the events.
     * @param year Year that the user wants to see the events under.
     * @throws DukeException If the event list is empty.
     */
    public static void showFirstSemester(EventList eventList, Integer year) throws DukeException {
        ArrayList<Event> semesterList;
        ArrayList<Event> yearList;
        ArrayList<ArrayList<String>> monthList;
        semesterList = getSemesterOneEvents(eventList);
        yearList = getAcademicYearEvents(semesterList, year);
        monthList = getMonthEvents(yearList);
        if (yearList.isEmpty()) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        UI.printCalendar(monthList, year, year + 1, 1);
        printMonths(monthList);
    }


    /**
     * Gets events that fall under semester one only, ie. Aug - Dec
     *
     * @param eventList List containing all the events.
     * @return List with events that fall under semester one.
     * @throws DukeException If it is unable to find the index of the event.
     */
    private static ArrayList<Event> getSemesterOneEvents(EventList eventList)
            throws DukeException {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < eventList.list.size(); i++) {
            Event event = eventList.find(i);
            if (event.getMonth() > 6) {
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
    public static void showSecondSemester(EventList eventList, int year) throws DukeException {
        ArrayList<Event> semesterList;
        ArrayList<Event> yearList;
        ArrayList<ArrayList<String>> monthList;
        semesterList = getSemesterTwoEvents(eventList);
        yearList = getAcademicYearEvents(semesterList, year);
        monthList = getMonthEvents(yearList);
        if (yearList.isEmpty()) {
            throw new DukeException("Unable to find any events for this time period.");
        }
        UI.printCalendar(monthList, year - 1, year, 2);
        printMonths(monthList);
    }

    /**
     *  Gets events that fall under semester two only, ie. Jan - Jul
     *
     * @param eventList List containing all the events.
     * @return List with events that fall under semester two.
     * @throws DukeException If it is unable to find the index of the event.
     */
    private static ArrayList<Event> getSemesterTwoEvents(EventList eventList)
            throws DukeException {
        ArrayList<Event> list = new ArrayList<>();
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
     * @param year Year that the user wants to see the events under.
     * @return List with events that fall under the correct year.
     */
    private static ArrayList<Event> getAcademicYearEvents(ArrayList<Event> semesterList, int year) {
        ArrayList<Event> yearList = new ArrayList<>();
        for (Event event : semesterList) {
            if (event.getYear().equals(year)) {
                yearList.add(event);
            }
        }
        return yearList;
    }

    private static ArrayList<ArrayList<String>> getMonthEvents(ArrayList<Event> yearList)
            throws DukeException {

        ArrayList<ArrayList<String>> monthList = new ArrayList<>(NO_OF_MONTHS);
        for (int k = 0; k < NO_OF_MONTHS; k++) {
            monthList.add(new ArrayList<>());
        }
        for (Event event : yearList) {
            int month = event.getMonth();
            switch (month) {
            case 1:
            case 7:
                monthList.get(0).add(event.getName());
                break;
            case 2:
            case 8:
                monthList.get(1).add(event.getName());
                break;
            case 3:
            case 9:
                monthList.get(2).add(event.getName());
                break;
            case 4:
            case 10:
                monthList.get(3).add(event.getName());
                break;
            case 5:
            case 11:
                monthList.get(4).add(event.getName());
                break;
            case 6:
            case 12:
                monthList.get(5).add(event.getName());
                break;
            default:
                throw new DukeException("Month not found");
            }
        }
        return monthList;
    }

    private static void printMonths(ArrayList<ArrayList<String>> monthList) {
        ArrayList<String> monthHeaderDescription = initializeHeaderList();
        int maxNumberOfEvents = getMaxNumberOfEvents(monthList);
        monthList = make2DArray(monthList, maxNumberOfEvents);
        printHeaderDescription(monthHeaderDescription, monthList, maxNumberOfEvents);

    }

    private static void printHeaderDescription(ArrayList<String> monthHeaderDescription,
                                               ArrayList<ArrayList<String>> monthList, int maxNumberOfEvents) {
        for (int k = 0; k < maxNumberOfEvents; k++) {
            for (int j = 0; j < monthList.size(); j++) {
                if (!monthList.get(j).get(k).equals("")) {
                    monthHeaderDescription.add(j, monthList.get(j).get(k));
                }
            }
            UI.printBodyOfSix(monthHeaderDescription);
            monthHeaderDescription = removePreviousElements(monthHeaderDescription);
        }
    }

    private static int getMaxNumberOfEvents(ArrayList<ArrayList<String>> list) {
        int maxNumberOfEvents = 0;
        for (ArrayList<String> events : list) {
            if (events.size() > maxNumberOfEvents) {
                maxNumberOfEvents = events.size();
            }
        }
        return maxNumberOfEvents;
    }

    private static ArrayList<String> initializeHeaderList() {
        ArrayList<String> list = new ArrayList<>(6);
        for (int h = 0; h < NO_OF_MONTHS; h++) {
            list.add("");
        }
        return list;
    }

    private static ArrayList<String> removePreviousElements(ArrayList<String> list) {
        for (int h = 0; h < NO_OF_MONTHS; h++) {
            list.replaceAll(s -> "");
        }
        return list;
    }


    private static ArrayList<ArrayList<String>> make2DArray(ArrayList<ArrayList<String>> list, int maxNumberOfEvents) {
        for (ArrayList<String> strings : list) {
            if (strings.size() < maxNumberOfEvents) {
                int size = strings.size();
                for (int j = 0; j < maxNumberOfEvents - size; j++) {
                    strings.add("");
                }
            }
        }
        return list;
    }

}
