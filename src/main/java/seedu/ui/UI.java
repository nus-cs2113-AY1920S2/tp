package seedu.ui;

import seedu.StudentList;
import seedu.exception.DukeException;
import seedu.performance.Performance;

import java.util.Scanner;

public class UI {
    private Scanner in;
    private String userInput;
    private static String userName;


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
    public void close() {
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

    public static void editEventVenueMessage(String oldVenue, String newVenue, String eventType) {
        System.out.printf("Your %s venue was changed from |%s| to |%s|.\n",
                eventType, oldVenue, newVenue);
    }

    public void addEventMessage(String eventType, String eventName) {
        System.out.printf("New %s: %s was added successfully to "
                + "your Event list.\n", eventType, eventName);
    }

    public void addAttendanceMessage(String studentName, String eventName) {
        System.out.printf("Attendance of %s has been taken successfully"
                + " under event %s.\n", studentName, eventName);
    }

    public void addPerformanceMessage(String studentName, String taskName) {
        System.out.printf("The result of student %s has been added "
                + "successfully under event %s.\n", studentName, taskName);
    }

    public void deleteEventMessage(String eventType, String eventName) {
        System.out.printf("%s: %s was deleted successfully from "
                + "your Event list.\n", eventType, eventName);
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

    public String getAttendancePerimeter() {
        System.out.println("Please key in student name and result in the following format:");
        System.out.println("n/Student_Name p/Is_Present");
        return in.nextLine();
    }

    public void printWrongInput(String typeInput) {
        System.out.printf("Wrong %s input. If you need help with "
                + "the input format, please input help.\n", typeInput);
    }

    public void printInsufficientInput(String typeInput) {
        System.out.printf("No %s input. If you need help with "
                + "the input format, please input help.\n", typeInput);
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
    
    public void printGetHelp() {
        System.out.println("Hello " + userName + ", please select the type of "
                + "command that you wish to get the format for.");
        System.out.println("1. Event");
        System.out.println("2. Attendance");
        System.out.println("3. Performance");
        System.out.println("4. Student List");
        System.out.println("To track any list, input: type_of_list list");
    }
    
    public void printEventHelp() {
        System.out.print("To add an event, use the following format:\n  "
                + "Event add n/Event_name v/Venue_name d/yyyy-MM-dd. "
                + "You may also replace 'Event' with one of the following type:"
                + "\n  - Seminar\n  - Exam\n  - Tutorial\n\n");
        System.out.print("To edit an event, use the following format:\n  "
                + "Event editDateTime i/index_of_Event, or\n  "
                + "Event editName i/index_of_Event, or\n  "
                + "Event editVenue i/index_of_Event, or\n  "
                + "Event editEvent (please edit these lines)\n\n");
        System.out.print("To edit an event, use the following format:\n  "
                + "Event editDateTime i/index_of_Event, or\n  "
                + "Event editName i/index_of_Event, or\n  "
                + "Event editVenue i/index_of_Event, or\n  "
                + "Event editEvent (please edit these lines)\n\n");
    }

    public void printPerformanceHelp() {
        System.out.print("To add students' performance under an event, input:\n  "
                + "performance add (this event should already be in the "
                + "current event list) and follow step by step instructions.\n\n");
        System.out.print("To delete a student's performance under an event, input:\n  "
                + "Performance delete (this event should already be in the "
                + "current event list) and follow step by step instructions.\n\n");
    }

    public void printAttendanceHelp() {
        System.out.print("To add students' attendance under an event, input:\n  "
                + "attendance add (this event should already be in the "
                + "current event list) and follow step by step instructions.\n\n");
        System.out.print("To delete a student's performance under an event, input:\n  "
                + "attendance delete (this event should already be in the "
                + "current event list) and follow step by step instructions.\n\n");
    }

    public void printStudentListHelp() {
    }
}
