package seedu.ui;

import seedu.StudentList;
import seedu.attendance.Attendance;
import seedu.event.Event;
import seedu.exception.DukeException;
import seedu.performance.Performance;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static seedu.duke.Duke.studentListCollection;

public class UI {
    private static Scanner in;
    private static String userInput;
    private static String userName;
    private final String columnOfFour = ("| %-10d|  %-30s|  %-35s|  %-10s|%n");
    private final String columnOfThree = ("| %-10d|  %-35s|  %-43s|%n");


    public UI() {
        in = new Scanner(System.in);
    }

    /**
     * Advances this scanner past the current line and stores the input that
     * was skipped, excluding any line separator at the end.
     * The position is set to the beginning of the next line.
     */
    public void readUserInput() {
        userInput = in.nextLine();
    }


    /**
     * Returns the string that is read from
     * {@code readUserInput()} most recently.
     *
     * @return the most recent line of user input
     */
    public String getUserInput() {
        return userInput;
    }

    public String getStringInput() {
        return in.nextLine();
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
    public void setUserName() {
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
    public static void printSplitOfFour() {
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

    public static void printSplitOfThree() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(37).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(45).forEach(System.out::print);
        System.out.print("|\n");
    }

    public static void printSplit() {
        Stream.generate(() -> "_").limit(97).forEach(System.out::print);
        System.out.print("\n");
    }

    /**
     * This prints the headers, index, header1, header2, and header 3
     * in order respectively.
     *
     * @param index   A String printed at row 1 column 1.
     * @param header1 A String printed at row 1 column 2.
     * @param header2 A String printed at row 1 column 3.
     * @param header3 A String printed at row 1 column 4.
     */
    public static void printHeaderOfFour(int index, String header1,
                                         String header2, String header3) {
        String headerFormat = ("| %-10s|  %-30s|  %-35s|  %-10s|%n");
        printSplit();
        System.out.printf(headerFormat, index, header1, header2, header3);
        printSplitOfFour();
    }

    /**
     * This prints the headers, index, body1, body2, and body3
     * in order respectively.
     *
     * @param index A String printed at column 1.
     * @param body1 A String printed at column 2.
     * @param body2 A String printed at column 3.
     * @param body3 A String printed at column 4.
     */
    public void printBodyOfFour(int index, String body1,
                                String body2, String body3) {
        System.out.printf(columnOfFour, index, body1, body2, body3);
        printSplitOfFour();
    }

    public void printHeaderOfThree(String index, String header1, String header2) {
        String columnOfThree = ("| %-10s|  %-35s|  %-43s|%n");
        printSplit();
        System.out.printf(columnOfThree, index, header1, header2);
        printSplitOfThree();
    }

    public void printBodyOfThree(int index, String body1, String body2) {
        System.out.printf(columnOfThree, index, body1, body2);
        printSplitOfThree();
    }


    public static void printEventList(ArrayList<Event> list) {
        System.out.println("Here are all the events in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    public static void printSeminarList(ArrayList<Event> list) {
        System.out.println("Here are all the seminar events in your list.");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
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

    public static void printCalendar(ArrayList<Event> list, int semesterOneYear, int semesterTwoYear, int semester) {
        System.out.printf("Events of Semester %d of AY %d/%d\n",
                semester, semesterOneYear, semesterTwoYear);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " +  list.get(i));
        }
    }

    /**
     * The message showed to the user after successful deletion of a performance.
     *
     * @param performance The Performance deleted.
     */
    public void deletePerformanceMessage(Performance performance, String eventName, boolean hasDeleted) {
        if (hasDeleted) {
            String studentName = performance.getStudent();
            System.out.printf("The result of student %s has been added "
                    + "successfully under event %s.\n", studentName, eventName);
        } else {
            System.out.printf("There is no record of %s's result in the list\n",
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

    public String getResultOfStudent(String studentName) {
        System.out.println("Please key in the result for student " + studentName);
        return in.nextLine();
    }

    public String getPerformanceParameter() {
        System.out.println("Please key in student name and result in the following format:");
        System.out.println("n/Student_Name r/result. If you are finished, enter done.");
        return in.nextLine();
    }

    public String getStudentName(String typeCommand) {
        System.out.printf("Please key in the name of student that you want to %s", typeCommand);
        return in.nextLine();
    }

    public Boolean getTypeOfAddPerformance() {
        System.out.println("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        String input = in.nextLine();
        return input.toLowerCase().equals("yes");
    }

    public String getEventName() {
        System.out.println("Please key in the name of event that "
                + "you wish to make change to its student's performance.");
        return in.nextLine();
    }

    public String getEventNameForAttendance() {
        System.out.println("Please key in the name of event.");
        return in.nextLine();
    }

    public String getAttendancePerimeter() {
        System.out.println("Please key in student name and result in the following format:");
        System.out.println("n/Student_Name p/Is_Present");
        return in.nextLine();
    }

    public void displayStudentList(StudentList studentList, String listName) {
        System.out.println("Student List created, named : " + listName);
        studentList.showList();
    }

    public void addStudent(StudentList studentList) {
        String studentName;
        do {
            System.out.println("Please enter a student Name. If you are finished, enter done");
            studentName = in.nextLine();
            if (studentName.contains("done")) {
                break;
            }
            studentList.addToList(studentName);
        } while (!studentName.equals("done"));
    }

    public String getListName() {
        System.out.println("What is the name of your list?");
        return in.nextLine();
    }

    public void displayStudentListCollection() throws DukeException {
        int index = 1;
        try {
            for (StudentList studentList : studentListCollection) {
                System.out.print("[" + index + "] ");
                studentList.showList();
                System.out.println("--------------");
                index++;
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void printGetHelp() {
        System.out.println("Hello " + userName + ", please refer to the "
                + "format below to use this app.");
        System.out.println("To track any list, input:\n  type_of_list list");
        printEventHelp();
        printPerformanceHelp();
        printAttendanceHelp();
    }

    private void printEventHelp() {
        System.out.print("To add an event, use the following format:\n  "
                + "Event add n/Event_name v/Venue_name d/yyyy-MM-dd. "
                + "You may also replace 'Event' with one of the following type:"
                + "\n  - Seminar\n  - Exam\n  - Tutorial\n");
        System.out.print("To edit an event, use the following format:\n  "
                + "Event editDateTime i/index_of_Event, or\n  "
                + "Event editName i/index_of_Event, or\n  "
                + "Event editVenue i/index_of_Event, or\n  "
                + "Event editEvent (please edit these lines)\n");
        System.out.print("To edit an event, use the following format:\n  "
                + "Event editDateTime i/index_of_Event, or\n  "
                + "Event editName i/index_of_Event, or\n  "
                + "Event editVenue i/index_of_Event, or\n  "
                + "Event editEvent (please edit these lines)\n");
    }

    private void printPerformanceHelp() {
        System.out.print("To add students' performance under an event, input:\n  "
                + "performance add (this event should already be in the "
                + "current event list) and follow step by step instructions.\n");
        System.out.print("To delete a student's performance under an event, input:\n  "
                + "Performance delete (this event should already be in the "
                + "current event list) and follow step by step instructions.\n");
    }

    private void printAttendanceHelp() {
        System.out.print("To add students' attendance under an event, input:\n  "
                + "attendance add (this event should already be in the "
                + "current event list) and follow step by step instructions.\n");
        System.out.print("To delete a student's performance under an event, input:\n  "
                + "attendance delete (this event should already be in the "
                + "current event list) and follow step by step instructions.\n");
    }
}
