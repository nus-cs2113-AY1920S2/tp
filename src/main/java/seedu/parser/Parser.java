package seedu.parser;

import seedu.event.Event;
import seedu.exception.DukeException;

import java.util.Arrays;
import java.util.DuplicateFormatFlagsException;
import java.util.UnknownFormatFlagsException;

public class Parser {
    /**
     * Returns the index in a string of parameters with this format:
     * [i/INDEX] ... .
     * @param parameters original parameters
     * @return the index in a string of parameters
     */
    public int parseIndex(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");
        int index = -1;
        for (String token: tokens) {
            switch (token.substring(0, 2)) {
            case "i/":
                index = Integer.parseInt(parameters.substring(2));
                break;
            default:
                // does nothing, as intended
                break;
            }
        }

        if (index == -1) {
            throw new DukeException("index not found");
        }
        return index;
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
        String name = "";
        String time = "";
        String date = "";
        String venue = "";

        splitByEventFlags(tokens, name, time, date, venue);

        String datetime = date + " " + time;

        return new Event(name, datetime, venue);
    }

    private void splitByEventFlags(String[] tokens, String name,
                                   String time, String date, String venue) {
        String mostRecent = null;
        for (String token : tokens) {
            switch (token.substring(0, 2)) {
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

    public static String[] performanceDataToParse(String userInput) {
        String[] instructions = userInput.split(" ",20);
        return Arrays.copyOfRange(instructions, 1, instructions.length);
    }

}
