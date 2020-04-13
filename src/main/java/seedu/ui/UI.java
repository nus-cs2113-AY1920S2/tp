package seedu.ui;

import seedu.student.StudentList;
import seedu.exception.PacException;
import seedu.performance.Performance;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import static seedu.pac.Pac.studentListCollection;

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

    public static void displayStudentListCollectionEmpty() {
        System.out.println("There is no existing student list.");
    }

    /**
     * Advances this scanner past the current line and stores the input that
     * was skipped, excluding any line separator at the end.
     * The position is set to the beginning of the next line.
     */
    public void readUserInput() {
        System.out.print(System.lineSeparator() + ">>> ");
        System.out.flush();
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
        readUserInput();
        return getUserInput();
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
        readUserInput();
        userName = getUserInput();
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
                + "successfully under event %s.\n", studentName, taskName);
        System.out.print("If you are adding student's result manually, ");
        System.out.print("record next student: (format: n/name r/result). ");
        System.out.print("When you are finished, input 'done'. \n\n");
    }

    public void deleteEventMessage(String eventType, String eventName) {
        System.out.printf("%s: %s was deleted successfully from "
                + "your Event list.\n", eventType, eventName);
    }

    public void printInvalidDateTimeFormat() {
        System.out.println("Datetime is not set. If you wish to add datetime, please enter the correct format:"
                + "yyyy-MM-dd HHmm");
    }

    public void printCalendarHeading(int semesterOneYear, int semesterTwoYear, int semester) {
        printCalendarHorizontalLine();
        String line = "SEMESTER " + semester + " AY " + semesterOneYear + "/" + semesterTwoYear;
        System.out.printf(" %75s %n", line);
        printCalendarHorizontalLine();
    }

    public void printCalendarHorizontalLine() {
        Stream.generate(() -> " _").limit(1).forEach(System.out::print);
        Stream.generate(() -> "_").limit(130).forEach(System.out::print);
        UI.display(" ");
    }

    public void printCalendarHeader(int semesterOneYear, int semesterTwoYear,
                                     int semester) {
        UI.display("");
        printCalendarHeading(semesterOneYear, semesterTwoYear, semester);
        printCalendarMonthsHeading(semester);
    }

    public void printCalendarMonthsHeading(int semester) {
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
        DisplayTable.printMonths(months);
    }

    /**
     * The message showed to the user after successful deletion of a performance.
     *
     * @param performance The Performance deleted.
     */
    public void deletePerformanceMessage(Performance performance, String eventName, boolean hasDeleted) {
        if (!hasDeleted) {
            display("Performance not found in list");
        }
        String studentName = performance.getStudent();
        System.out.printf("The result of student %s has been deleted "
                + "successfully under event %s.\n", studentName, eventName);
    }

    public void editPerformanceMessage(Performance performance, String type) {
        String studentName = performance.getStudent();
        System.out.printf("The student %s has been changed to %s successfully.\n", type, studentName);
    }

    public String getPerformanceName(String type) {
        System.out.printf("Please key in the student's new %s", type);
        return getStringInput();
    }

    public String getResultOfStudent(String studentName) {
        display("Please key in the result for " + studentName + ": ");
        readUserInput();
        return getUserInput();
    }

    public String getAttendanceStatusOfStudent(String studentName) {
        display("Please key in the attendance status for student " + studentName + " [Y/N]");
        readUserInput();
        return getUserInput();
    }

    public String getPerformanceParameter() {
        display("Please key in the type of performance parameter you want to edit: name / result");
        readUserInput();
        return getUserInput();
    }

    public String getPerformanceParameterToAdd() {
        display("Please key in the performance you want to add in this format: n/name r/result");
        display("When you are finished, input 'done' ");
        readUserInput();
        return getUserInput();
    }

    public String getStudentName(String typeCommand) {
        System.out.printf("Please key in the name of student that you wish to %s \n", typeCommand);
        readUserInput();
        return getUserInput();
    }

    public String getTypeOfAddPerformance() {
        display("Would you like to import an existing student list? "
                + "If yes, input 'yes'. Else, input anything.");
        return getStringInput();
    }

    public boolean isImportList() {
        String userInput = getTypeOfAddPerformance();
        return userInput.equals("yes");
    }

    public String getEventName() {
        display("Please key in the name of event that "
                + "you wish to access to its student's performance.");
        readUserInput();
        return getUserInput();
    }

    public String getEventNameForAttendance() {
        display("Please key in the name of event.");
        readUserInput();
        return getUserInput();
    }

    public void clearAttendanceMessage(String eventName) {
        display("Attendance List cleared for Event: " + eventName);
    }

    public void sortAttendanceByStatus(String eventName) {
        display("Attendance List is sorted by name for Event: " + eventName);
    }

    public void sortAttendanceByName(String eventName) {
        display("Attendance List is sorted by attendance name for Event:  " + eventName);
    }

    public void sortPerformanceByName(String eventName) {
        display("Performance List is sorted by Performance status for Event:  " + eventName);
    }

    public String getSortType() {
        display("Do you want to sort by students' name or result?");
        return getStringInput().toLowerCase();
    }

    public void addStudent(StudentList studentList) throws PacException {
        String studentName = "";
        do {
            display("Please enter a student Name. If you are finished, enter done.");
            readUserInput();
            studentName = getUserInput().trim();
            if (studentName.equals("done")) {
                break;
            } else if (studentList.isDuplicate(studentName)) {
                throw new PacException("Duplicated Student Name found.\nStudent List Add Command Failed.");
            } else {
                studentList.addToList(studentName);
            }
        } while (!studentName.equals("done"));
    }

    public String getListName() {
        display("What is the name of your list?");
        readUserInput();
        return getUserInput();
    }

    public void printStudentListCollection() {
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
