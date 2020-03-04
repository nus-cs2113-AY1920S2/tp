package seedu.duke;

import task.Event;
import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        String myStr = in.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Event("test", parseDate(myStr)));
        int index = 1;
        for (Task task: taskList) {
            task.setDone();
            System.out.println(String.format("%d. %s", index++, task.toString()));
        }
    }

    public static LocalDateTime parseDate(String dateTimeString)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] splitDateTime = dateTimeString.split("\\s+", 2);
        String formattedDateTimeString = splitDateTime[0] + " " + splitDateTime[1];
        return LocalDateTime.parse(formattedDateTimeString, INPUT_DATE_FORMAT);
    }

}
