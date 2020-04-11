package seedu.ui;

public class HelpUI extends UI {

    public void printGetHelp() {
        StringBuilder output = new StringBuilder(
            "Hello, please select 1-5 for its command format.\n"
            + "1. Student List\n"
            + "2. Event-related\n"
            + "3. Calendar\n"
            + "4. Attendance\n"
            + "5. Performance\n"
        );
        output.append("To view event list, input: event list\n");
        output.append("To view other lists, input: <type_of_list> view\n");
        output.append("Note: All command are NOT CASE SENSITIVE.\n");
        output.append("To exit help function, enter 'back'\n");
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
        output.append("Enter 'list' for command format list. To exit help function, enter 'back'\n");
        display(output.toString());
    }

    public void printCalendarHelp() {
        display("To view events in a calendar, there should be events that exist in the event list.");
        display("To view calendar, input: s/Semester_Number ay/Year_one-Year_two.\n");
        display("* Note that semester can only be an integer 1 or 2.");
        display("* Note that the academic year must contain two consecutive years separated by a hyphen.\n "
                + " Each year should be in a double digit format.");
        display("Enter 'list' for command format list. To exit help function, enter 'back'\n");
    }

    public void printPerformanceHelp() {
        display("All performance list should exist under an existing event."
                + "Follow step-by-step commands after the following commands are input.");
        display("To add students' performance, input:\n\t"
                + "performance add\n");
        display("To delete a student's performance under an event, input:\n\t"
                + "Performance delete\n");
        display("To edit a student's performance under an event, input:\n\t"
                + "Performance edit");
        display("\tYou are allowed to edit student's performance by student's name or result.\n");
        display("To sort a student's performance list under an event, input:\n\t"
                + "Performance sort");
        display("\tYou are allowed to sort student's performance by students' name or result.\n");
        display("To view a student's performance under an event, input:\n\t"
                + "Performance view");
        display("Enter 'list' for command format list. To exit help function, enter 'back'\n");
    }

    public void printAttendanceHelp() {
        display("All attendance list should exist under an existing event."
                + "Follow step-by-step commands after the following commands are input.");
        display("To add students' attendance, input:\n\t"
                + "attendance add\n");
        display("To clear a student's attendance list, input:\n\t"
                + "attendance clear\n");
        display("To sort a student's attendance list, input:\n\t"
                + "attendance sort\n");
        display("To find a student's attendance, input:\n\t"
                + "attendance find\n");
        display("To edit a student's attendance, input:\n\t"
                + "attendance edit\n");
        display("Enter 'list' for command format list. To exit help function, enter 'back'\n");
    }

    public void printStudentListHelp() {
        display("To create a new studentList for future events, input:\n\t"
                + "studentlist add\n");
        display("To delete an existing studentList, input:\n\t"
                + "studentlist delete\n");
        display("To clear all existing studentList, input:\n\t"
                + "studentlist clear\n");
        display("To find an existing studentList, input:\n\t"
                + "studentlist find\n");
        display("To list all existing studentList, input:\n\t"
                + "studentlist view\n");
        display("To sort all existing studentList, input:\n\t"
                + "studentlist sort\n");
        display("Enter 'list' for command format list. To exit help function, enter 'back'\n");
    }
}
