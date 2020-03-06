package seedu.IO;

import java.util.Scanner;

public class IO {
    private Scanner in;
    private String userInput;

    public IO() {
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
}
