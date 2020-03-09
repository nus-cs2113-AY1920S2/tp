package seedu.parser;

import seedu.module.performance.PerformanceList;
import seedu.module.performance.Performance;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<Performance> performances;

    /**
     * The user input typically include three categories,
     * the command type, command, and data. There is one
     * parser for each of the category respectively.
     */
    public Parser() {
        performances = new PerformanceList().getPerformanceList();
    }

    /**
     *Get Command word from userInput.
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
