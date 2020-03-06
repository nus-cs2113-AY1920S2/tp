package seedu.duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner in;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints a line divider.
     */
    public void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints welcome messages.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What is your name?");
        System.out.println("Hello " + getUserInput());
        printDividerLine();
    }

    /**
     * Prints exit messages.
     */
    public void printGoodbyeMessage() {
        System.out.println("Exiting DUKE\n" + LOGO);
    }

    /**
     * Prints a prompt to user and returns the next line of user input.
     * @return String of user input
     */
    public String getUserInput() {
        System.out.println(">");
        return in.nextLine().trim();
    }

    public void showAddTaskMessage(Task newTask, int listSize) {
        System.out.println(String.format("Added task:\n     %s\nNow you have %d tasks in the list!",
                newTask.toString(), listSize));
    }

    public void showDoneMessage(Task taskMarkedDone, int doneIndex) {
        System.out.println(String.format("[ %d. %s ] is marked done!",
                doneIndex, taskMarkedDone.toString()));
    }

    public void showDeleteMessage(Task deletedTask, int deleteIndex) {
        System.out.println(String.format("[ %d. %s ] is deleted!", deleteIndex, deletedTask.toString()));
    }

    public void showListTasks(ArrayList<Task> taskList) {
        StringBuilder stringFromArrayList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            stringFromArrayList.append((i+1) + "." + taskList.get(i).toString());
            if (i != taskList.size()-1) {
                stringFromArrayList.append(System.lineSeparator());
            }
        }
        System.out.println(String.format("Here are all your requested tasks:%s%s",
                System.lineSeparator(), stringFromArrayList.toString()));
    }
}
