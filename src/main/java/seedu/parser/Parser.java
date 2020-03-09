package seedu.parser;

public class Parser {
    /**
     * Get Command word from userInput.
     *
     * @param userInput The userInput read by Ui.
     * @return The Command word.
     */

    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }

}
