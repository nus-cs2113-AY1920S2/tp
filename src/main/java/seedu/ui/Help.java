package seedu.ui;

public class Help extends UI {

    public void printGetHelp() {
        System.out.println("Hello, please select the type of "
                + "command that you wish to get the format for.");
        System.out.println("1. Event");
        System.out.println("2. Attendance");
        System.out.println("3. Performance");
        System.out.println("4. Student List");
        System.out.println("To track any list, input: type_of_list list");
        System.out.println("Note: All command are NOT case sensitive.");
    }

    public void printEventHelp() {
        System.out.print("To add an event, use the following format:\n\t"
                + "Event add n/Event_name v/Venue_name d/yyyy-MM-dd. "
                + "You may also replace 'Event' with one of the following type:"
                + "\n\t- Seminar\n\t- Exam\n\t- Tutorial\n\n");
        System.out.print("To edit an event, use the following format:\n\t"
                + "Event editDateTime i/index_of_Event, or\n\t"
                + "Event editName i/index_of_Event, or\n\t"
                + "Event editVenue i/index_of_Event, or\n\t"
                + "Event editEvent (please edit these lines)\n\n");
        System.out.print("To edit an event, use the following format:\n\t"
                + "Event editDateTime i/index_of_Event, or\n\t"
                + "Event editName i/index_of_Event, or\n\t"
                + "Event editVenue i/index_of_Event, or\n\t"
                + "Event editEvent (please edit these lines)\n\n");
    }

    public void printPerformanceHelp() {
        System.out.println("All performance list should exist under an existing event."
                + "Follow step-by-step commands after the following commands are input.");
        System.out.println("To add students' performance, input:\n\t"
                + "performance add\n");
        System.out.println("To delete a student's performance under an event, input:\n\t"
                + "Performance delete\n");
        System.out.println("To sort a student's performance list under an event, input:\n\t"
                + "Performance sort\n");
    }

    public void printAttendanceHelp() {
        System.out.println("All attendance list should exist under an existing event."
                + "Follow step-by-step commands after the following commands are input.");
        System.out.println("To add students' attendance, input:\n\t"
                + "Attendance add\n");
        System.out.println("To delete a student's attendance, input:\n\t"
                + "Attendance delete\n");
        System.out.println("To sort a student's attendance list, input:\n\t"
                + "Attendance sort\n");
    }

    public void printStudentListHelp() {
        System.out.print("To create a new studentList for future events, input:\n\t"
                + "student add\n\n");
        System.out.print("To delete an existing studentList, input:\n\t"
                + "student delete (index)\n\n");
        System.out.print("To clear all existing studentList, input:\n\t"
                + "student clear\n\n");
        System.out.print("To find an existing studentList, input:\n\t"
                + "student find\n\n");
        System.out.print("To list all existing studentList, input:\n\t"
                + "student list\n\n");
        System.out.print("To sort all existing studentList by their names, input:\n\t"
                + "student sort/by/name\n\n");
        System.out.print("To sort all name within the existing studentList, input:\n\t"
                + "student sort/by/list\n\n");
    }
}
