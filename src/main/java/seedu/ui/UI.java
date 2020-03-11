package seedu.ui;

import java.io.Serializable;
import java.util.Scanner;
import java.util.stream.Stream;

public class UI {
    private Scanner in;
    private String userInput;
    private String userName;
    final String LIST_FORMAT = ("| %-30s|  %-35s|  %-10s|%n");

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
     * @return the most recent line of user input
     */
    public String getUserInput() {
        return userInput;
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

    public void setUserName() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        display("Hello from\n" + logo);
        display("What is your name?");
        userName = in.nextLine();
        display("Hello " + userName + ". Welcome to your personal Professor Assistant Console.");
    }

    public void printSplit() {
        Stream.generate(() -> "_").limit(84).forEach(System.out::print);
        System.out.printf("\n");

    }
    public void showListHeader(String header1, String header2, String header3) {
        printSplit();
        System.out.printf(LIST_FORMAT, header1, header2, header3);
        printSplit();
    }

    public void showListFormat(String studentName, String assignmentName, Serializable result) {
        System.out.printf(LIST_FORMAT, studentName, assignmentName, result);
        printSplit();
    }


    public void addMessageEvent() {
    }

    public void addMessageAttendance() {
    }

    public void addMessagePerformance() {
    }

    public void editMessageEvent() {
    }

    public void editMessageAttendance() {
    }

    public void editMessagePerformance() {
    }

    public void deleteMessageEvent() {
    }

    public void deleteMessageAttendance() {
    }

    public void deleteMessagePerformance() {
    }
}
