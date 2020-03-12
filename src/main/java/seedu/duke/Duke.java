package seedu.duke;

import seedu.ui.UI;

import java.util.Scanner;

public class Duke {
    public Duke() {

    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        UI.printListHeader("Index", "Student Name", "Assignment 1", "Result");
        UI.printListBody(1, "Person1", "CS2101", 100);
        UI.printListBody(2, "Person2", "CS2113", "A");
    }
}
