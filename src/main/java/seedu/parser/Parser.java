package seedu.parser;

import seedu.event.Event;
import seedu.module.performance.PerformanceList;
import seedu.module.performance.Performance;

import java.util.Arrays;
import java.util.List;
import java.util.DuplicateFormatFlagsException;
import java.util.UnknownFormatFlagsException;

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

    // TODO: [r/FREQ[/TIME or /DAY]]
    /**
     * Parse parameters based on the following format:
     * n/EVENTNAME [t/EVENTTIME] [d/EVENTDATE] [v/EVENTVENUE].
     * @param parameters original parameters
     * @return an Event object with the relevant information
     */
    public Event parseEvent(String parameters) {
        String[] tokens = parameters.split(" ");
        Integer index = -1;
        String name = "";
        String time = "";
        String date = "";
        String venue = "";

        splitByEventFlags(tokens, index, name, time, date, venue);

        String datetime = date + " " + time;

        return new Event(name, datetime, venue);
    }

    private void splitByEventFlags(String[] tokens, Integer index, String name,
                                   String time, String date, String venue) {
        String mostRecent = null;
        for (String token : tokens) {
            switch (token.substring(0, 2)) {
            case "i/":
                if (index.equals(-1)) {
                    throw new DuplicateFormatFlagsException("index");
                }
                index = Integer.parseInt(token.substring(2));
                break;
            case "n/":
                if (!name.isEmpty()) {
                    throw new DuplicateFormatFlagsException("name");
                }
                name += token.substring(2);
                mostRecent = name;
                break;
            case "t/":
                if (!time.isEmpty()) {
                    throw new DuplicateFormatFlagsException("time");
                }
                time += token.substring(2);
                mostRecent = time;
                break;
            case "d/":
                if (!date.isEmpty()) {
                    throw new DuplicateFormatFlagsException("date");
                }
                date += token.substring(2);
                mostRecent = date;
                break;
            case "v/":
                if (!venue.isEmpty()) {
                    throw new DuplicateFormatFlagsException("venue");
                }
                venue += token.substring(2);
                mostRecent = venue;
                break;
            default:
                // assumes that all valid flags have been processed before this line
                if (isUnknownFlag(token)) {
                    throw new UnknownFormatFlagsException("unknown flag");
                }
                if (mostRecent == null) {
                    throw new NullPointerException("parameter without flag");
                }
                mostRecent += (" " + token);
            }
        }
    }

    /**
     * Returns {@code true} if the input contains a flag.
     * It is assumed that all valid flags have been handled
     * before the execution of this function.
     * @param input the token to be checked
     * @return {@code true} if the input contains a flag
     */
    private boolean isUnknownFlag(String input) {
        return input.charAt(1) == '/';
    }
}
