package seedu.happypills.exception;

import seedu.happypills.HappyPills;

public class HappyPillsException extends Exception {
    /**
     * @param message Contain relevant information on the failed constraint(s)
     */
    public HappyPillsException(String message) {
        super(message);
    }
}
