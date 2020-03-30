package seedu.ui;

import seedu.StudentList;
import seedu.exception.DukeException;
import seedu.performance.Performance;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import static seedu.duke.Duke.studentListCollection;

public class UI {
    private static String userName;
    private Scanner in;
    private String userInput;


    public UI() {
        in = new Scanner(System.in);
    }

    public static void display(String message) {
        System.out.println(message);
    }

    /**
     * Advances this scanner past the current line and stores the input that
     * was skipped, excluding any line separator at the end.
     * The position is set to the beginning of the next line.
     */
    public void readUserInput() {
        System.out.print(">>> ");
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
    public void close() {
        in.close();
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
                + "personal Professor Assistant Console. If you need "
                + "assistant with command format, input 'help'.");
    }

    public void editEventMessage(String oldEvent, String newEvent, String eventType) {
        System.out.printf("Your %s was edited from |%s| to |%s|.\n",
                eventType, oldEvent, newEvent);
    }

    public void editEventNameMessage(String oldName, String newName, String eventType) {
        System.out.printf("Your %s name was changed from |%s| to |%s|.\n",
                eventType, oldName, newName);
    }

    public void editEventDateTimeMessage(String oldDateTime, String newDateTime, String eventType) {
        System.out.printf("Your %s date and time was changed from |%s| to |%s|.\n",
                eventType, oldDateTime, newDateTime);
    }

    public void editEventVenueMessage(String oldVenue, String newVenue, String eventType) {
        System.out.printf("Your %s venue was changed from |%s| to |%s|.\n",
                eventType, oldVenue, newVenue);
    }

    public void addEventMessage(String eventType, String eventName) {
        System.out.printf("New %s: %s was added successfully to "
                + "your Event list.\n", eventType, eventName);
    }

    public void addAttendanceMessage(String studentName, String attendanceStatus, String eventName) {
        System.out.printf("Attendance of %s (%s) has been taken successfully"
                + " under event %s.\n", studentName, attendanceStatus, eventName);
    }

    public void addPerformanceMessage(String studentName, String taskName) {
        System.out.printf("The result of student %s has been added "
                + "successfully under event %s.\n\n", studentName, taskName);
    }

    public void deleteEventMessage(String eventType, String eventName) {
        System.out.printf("%s: %s was deleted successfully from "
                + "your Event list.\n", eventType, eventName);
    }

    public static void printBorderOfCalendar() {
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|");
        Stream.generate(() -> "_").limit(11).forEach(System.out::print);
        System.out.print("|\n");
    }

    public static void printCalendarHeading(int semesterOneYear, int semesterTwoYear, int semester) {
        printCalendarHorizontalLine();
        String line = "SEMESTER " + semester + " AY " + semesterOneYear + "/" + semesterTwoYear;
        System.out.printf(" %40s %n", line);
        printCalendarHorizontalLine();
    }

    public static void printCalendarHorizontalLine() {
        Stream.generate(() -> " _").limit(1).forEach(System.out::print);
        Stream.generate(() -> "_").limit(70).forEach(System.out::print);
        System.out.println(" ");
    }

    public static void printBodyOfSix(ArrayList<String> description) {
        String columnOfSix = ("| %-10s| %-10s| %-10s| %-10s| %-10s| %-10s|%n");
        System.out.printf(columnOfSix, description.get(0), description.get(1), description.get(2), description.get(3),
                description.get(4), description.get(5));
        printBorderOfCalendar();
    }


    public static void printCalendar(ArrayList<ArrayList<String>> list, int semesterOneYear, int semesterTwoYear,
                                     int semester) {
        printCalendarHeading(semesterOneYear, semesterTwoYear, semester);
        printCalendarMonthsHeading(semester);
    }

    public static void printCalendarMonthsHeading(int semester) {
        ArrayList<String> months = new ArrayList<>();
        if (semester == 1) {
            months.add(0, "JUL");
            months.add(1, "AUG");
            months.add(2, "SEP");
            months.add(3, "OCT");
            months.add(4, "NOV");
            months.add(5, "DEC");
        } else {
            months.add(0, "JAN");
            months.add(1, "FEB");
            months.add(2, "MAR");
            months.add(3, "APR");
            months.add(4, "MAY");
            months.add(5, "JUN");
        }
        printBodyOfSix(months);
    }

    /**
     * The message showed to the user after successful deletion of a performance.
     *
     * @param performance The Performance deleted.
     */
    public void deletePerformanceMessage(Performance performance, String eventName,
                                         boolean hasDeleted) throws DukeException {
        if (!hasDeleted) {
            throw new DukeException("Performance not found in list");
        }
        String studentName = performance.getStudent();
        System.out.printf("The result of student %s has been deleted "
                + "successfully under event %s.\n", studentName, eventName);
    }

    public String getResultOfStudent(String studentName) {
        System.out.println("Please key in the result for student " + studentName);
        return in.nextLine();
    }

    public String getAttendanceStatusOfStudent(String studentName) {
        System.out.println("Please key in the attendance status for student " + studentName + "[Y/N]");
        return in.nextLine();
    }


    public String getPerformanceParameter() {
        System.out.println("Please key in student name and result in the following format:");
        System.out.println("n/Student_Name r/result. If you are finished, enter done.");
        return in.nextLine();
    }

    public String getStudentName(String typeCommand) {
        System.out.printf("Please key in the name of student that you wish to %s \n", typeCommand);
        return in.nextLine();
    }

    public String getTypeOfAddPerformance() {
        System.out.println("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        return getStringInput();
    }

    public boolean isImportList() {
        String userInput = getTypeOfAddPerformance();
        return userInput.equals("yes");
    }

    public String getEventName() {
        System.out.println("Please key in the name of event that "
                + "you wish to access to its student's performance.");
        return in.nextLine();
    }

    public String getEventNameForAttendance() {
        System.out.println("Please key in the name of event.");
        return in.nextLine();
    }

    public void clearAttendanceMessage(String eventName) {
        System.out.println("Attendance List cleared for Event: " + eventName);
    }

    public void sortAttendanceByStatus(String eventName) {
        System.out.println("Attendance List is sorted by name for Event: " + eventName);
    }

    public void sortAttendanceByName(String eventName) {
        System.out.println("Attendance List is sorted by attendance status for Event:  " + eventName);
    }

    public void sortPerformanceByName(String eventName) {
        System.out.println("Performance List is sorted by Performance name for Event:  " + eventName);
    }

    public String getSortType() {
        System.out.println("Do you want to sort by students' name or grade?");
        return getStringInput().toLowerCase();
    }

    public void addStudent(StudentList studentList) {
        String studentName;
        do {
            System.out.println("Please enter a student Name. If you are finished, enter done");
            studentName = in.nextLine();
            studentList.addToList(studentName);
        } while (!studentName.equals("done"));
    }

    public String getListName() {
        System.out.println("What is the name of your list?");
        return in.nextLine();
    }

    public void printStudentListCollection() {
        if (studentListCollection.isEmpty()) {
            System.out.println("The student list collection is currently empty");
        } else {
            DisplayTable displayTable = new DisplayTable();
            for (int i = 0; i < studentListCollection.size(); i++) {
                displayTable.printHeaderOfTwo("List #" + (i + 1),
                        studentListCollection.get(i).getListName());
                int index = 1;
                for (String name : studentListCollection.get(i).getStudentList()) {
                    displayTable.printBodyOfTwo(index, name);
                    index++;
                }
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
