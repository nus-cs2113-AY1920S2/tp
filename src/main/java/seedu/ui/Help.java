package seedu.ui;

public class Help extends UI {

    public void printGetHelp() {
        StringBuilder output = new StringBuilder(
            "Hello, please select 1-4 for its command format.\n"
            + "1. Event-related\n"
            + "2. Attendance\n"
            + "3. Performance\n"
            + "4. Student List\n"
        );
        output.append("To track any list, input: <type_of_list> list\n");
        output.append("Note: All command are NOT case sensitive.\n");

        display(output.toString());
    }

    public void printEventHelp() {
        StringBuilder output = new StringBuilder(
            "To add an event, use this command the following format:\n"
            + "\tEvent add n/Event_name [v/Venue_name] [d/yyyy-MM-dd t/HHmm].\n"
            + "\n"
            + "You may also replace 'Event' with one of the following type:\n"
            + "\t- Seminar\n"
            + "\n"
        );
        output.append(
            "To edit an event, use these commands with the following format:\n"
            + "\tEvent editDateTime i/Event_index d/yyyy-MM-dd t/HHmm, or\n"
            + "\tEvent editName i/Event_index n/Event_name, or\n"
            + "\tEvent editVenue i/Event_index v/Event_venue, or\n"
            + "\tEvent editEvent i/Event_index n/Event_name [d/yyyy-MM-dd t/HHmm] [v/Event_venue]\n"
            + "\n"
        );
        output.append(
            "To list all events, use the command\n"
            + "\tevent list\n"
            + "\n"
            + "To delete an event, use the command\n"
            + "\tevent delete i/Event_index\n"
        );
        display(output.toString());
    }

    public void printPerformanceHelp() {
        display("All performance list should exist under an existing event."
                + "Follow step-by-step commands after the following commands are input.");
        display("To add students' performance, input:\n\t"
                + "performance add\n");
        display("To delete a student's performance under an event, input:\n\t"
                + "Performance delete\n");
        display("To sort a student's performance list under an event, input:\n\t"
                + "Performance sort\n");
    }

    public void printAttendanceHelp() {
        display("All attendance list should exist under an existing event."
                + "Follow step-by-step commands after the following commands are input.");
        display("To add students' attendance, input:\n\t"
                + "Attendance add\n");
        display("To delete a student's attendance, input:\n\t"
                + "Attendance delete\n");
        display("To sort a student's attendance list, input:\n\t"
                + "Attendance sort\n");
    }

    public void printStudentListHelp() {
        System.out.print("To create a new studentList for future events, input:\n\t"
                + "student.list add\n\n");
        System.out.print("To delete an existing studentList, input:\n\t"
                + "student.list delete (index)\n\n");
        System.out.print("To clear all existing studentList, input:\n\t"
                + "student.list clear\n\n");
        System.out.print("To find an existing studentList, input:\n\t"
                + "student.list find\n\n");
        System.out.print("To list all existing studentList, input:\n\t"
                + "student.list list\n\n");
        System.out.print("To sort all existing studentList by their names, input:\n\t"
                + "student.list sort/by/name\n\n");
        System.out.print("To sort all name within the existing studentList, input:\n\t"
                + "student.list sort/by/list\n\n");
    }
}
