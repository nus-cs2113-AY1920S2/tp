package seedu.ui;

import seedu.attendance.Attendance;
import seedu.event.Event;
import seedu.performance.Performance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class UI {
    private static Scanner in;
    private static String userInput;
    private static String userName;
    static final String LIST_FORMAT = ("| %-10d|  %-30s|  %-35s|  %-10s|%n");

    public UI() {
        in = new Scanner(System.in);
    }

    /**
     * Advances this scanner past the current line and stores the input that
     * was skipped, excluding any line separator at the end.
     * The position is set to the beginning of the next line.
     */
    public static void readUserInput() {
        userInput = in.nextLine();
    }

    /**
     * Returns the string that is read from
     * {@code readUserInput()} most recently.
     * @return the most recent line of user input
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Close the scanner.
     */
    public static void close() {
        in.close();
    }

    public static void display(String message) {
        System.out.println(message);
    }

    /**
     * This prints the welcome message and set username for the application.
     */
    public static void setUserName() {
        String logo = "\n"
                + " ______       ____           ______\n"
                + "|   __  \\    /    \\      /     ___|\n"
                + "|  |__| |   /  /\\  \\     |   /\n"
                + "|   ____/  /   _    \\    |   |\n"
                + "|  |      /  /    \\  \\   |   \\____\n"
                + "|__|     /__/      \\__\\  \\________|\n";
        display("Hello from\n" + logo);
        display("What is your name?");
        userName = in.nextLine();
        display("Hello " + userName + ". Welcome to your "
                + "personal Professor Assistant Console.");
    }

    /**
     * This prints the horizontal split for a 4 columns table.
     */
    public static void printSplitContent() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(32).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(37).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(12).forEach(System.out::print);
        System.out.print("|\n");
    }

    public static void printSplit() {
        Stream.generate(() -> "_").limit(97).forEach(System.out::print);
        System.out.print("\n");
    }

    /**
     * This prints the headers, index, header1, header2, and header 3
     * in order respectively.
     * @param index A String printed at row 1 column 1.
     * @param header1 A String printed at row 1 column 2.
     * @param header2 A String printed at row 1 column 3.
     * @param header3 A String printed at row 1 column 4.
     */
    public static void printListHeader(String index, String header1,
                                       String header2, String header3) {
        String headerFormat = ("| %-10s|  %-30s|  %-35s|  %-10s|%n");
        printSplit();
        System.out.printf(headerFormat, index, header1, header2, header3);
        printSplitContent();
    }

    /**
     * This prints the headers, index, body1, body2, and body3
     * in order respectively.
     * @param index A String printed at column 1.
     * @param body1 A String printed at column 2.
     * @param body2 A String printed at column 3.
     * @param body3 A String printed at column 4.
     */
    public static void printListBody(int index, String body1,
                                     String body2, Serializable body3) {
        System.out.printf(LIST_FORMAT, index, body1, body2, body3);
        printSplitContent();
    }

    public static void printEventList(ArrayList<Event> list) {
        System.out.println("Here are all the events in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " +  list.get(i));
        }
    }

    public static void printSeminarList(ArrayList<Event> list) {
        System.out.println("Here are all the seminar events in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " +  list.get(i));
        }
    }

    public static void editEventMessage(String oldEvent, String newEvent, String eventType) {
        System.out.printf("Your %s was edited from |%s| to |%s|.\n",
                eventType, oldEvent, newEvent);
    }

    public static void editEventNameMessage(String oldName, String newName, String eventType) {
        System.out.printf("Your %s name was changed from |%s| to |%s|.\n",
                eventType, oldName, newName);

    }

    public static void editEventDateTimeMessage(String oldDateTime, String newDateTime, String eventType) {
        System.out.printf("Your %s date and time was changed from |%s| to |%s|.\n",
                eventType, oldDateTime, newDateTime);
    }

    public static void editEventVenueMessage(String oldVenue, String newVenue, String eventType) {
        System.out.printf("Your %s venue was changed from |%s| to |%s|.\n",
                eventType, oldVenue, newVenue);
    }

    public static void addEventMessage(String eventType, String eventName) {
        System.out.printf("New %s: %s was added successfully to "
                + "your Event list.\n", eventType, eventName);
    }

    public static void addAttendanceMessage(String studentName, String eventName) {
        System.out.printf("Attendance of %s has been taken successfully"
                + " under event %s.\n", studentName, eventName);
    }

    public static void addPerformanceMessage(String studentName, String taskName) {
        System.out.printf("The result of student %s has been added "
                + "successfully under event %s.\n", studentName, taskName);
    }

    public static void deleteEventMessage(String eventType, String eventName) {
        System.out.printf("%s: %s was deleted successfully from "
                + "your Event list.\n", eventType, eventName);
    }


    /**
     * The message showed to the user after successful deletion of a attendance.
     * @param attendance The Attendance deleted.
     */
    public static void deleteAttendanceMessage(Attendance attendance, boolean hasDeleted) {
        if (hasDeleted) {
            String studentName = attendance.getStudent();
            String eventName = attendance.getEvent();
            System.out.printf("Attendance of %s has been taken successfully"
                    + " under event %s.\n", studentName, eventName);
        } else {
            System.out.printf("There is no %s's attendance record in the list\n",
                    attendance.getStudent());
        }
    }

    public static void printCalendar(ArrayList<Event> list, int semesterOneYear, int semesterTwoYear, int semester) {
        System.out.printf("Events of Semester %d of AY %d/%d\n",
                semester, semesterOneYear, semesterTwoYear);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " +  list.get(i));
        }
    }

    /**
     * The message showed to the user after successful deletion of a performance.
     * @param performance The Performance deleted.
     */
    public static void deletePerformanceMessage(Performance performance, boolean hasDeleted) {
        if (hasDeleted) {
            String studentName = performance.getStudent();
            String eventName = performance.getEvent();
            System.out.printf("The result of student %s has been added "
                    + "successfully under event %s.\n", studentName, eventName);
        } else {
            System.out.printf("There is no %s's performance record in the list\n",
                    performance.getStudent());
        }
    }

    public static void printWrongInput(String typeInput) {
        System.out.printf("Wrong %s input. If you need help with "
                + "the input format, please input help.\n", typeInput);
    }

    public static void printInsufficientInput(String typeInput) {
        System.out.printf("No %s input. If you need help with "
                + "the input format, please input help.\n", typeInput);
    }
}
