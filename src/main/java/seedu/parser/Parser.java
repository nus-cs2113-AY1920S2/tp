package seedu.parser;

import java.util.Arrays;

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

    public static String[] performanceDataToParse(String userInput) {
        String[] instructions = userInput.split(" ",20);
        return Arrays.copyOfRange(instructions, 1, instructions.length);
    }
}
