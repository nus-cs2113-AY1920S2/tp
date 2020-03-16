package seedu.parser;

import seedu.duke.Duke;
import seedu.event.DateTime;
import seedu.event.Event;
import seedu.exception.DukeException;

public class EventParser {
    private String name;
    private String date;
    private String time;
    private String venue;

    public EventParser() {
        this.name = "";
        this.date = "";
        this.time = "";
        this.venue = "";
    }

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
            try {
                switch (token.substring(0, 2)) {
                case "i/":
                    index = Integer.parseInt(parameters.substring(2, 3));
                    break;
                default:
                    // does nothing, as intended
                    break;
                }
            } catch (Exception m) {
                throw new DukeException(m.getMessage());
            }
        }

        if (index < 0) {
            throw new DukeException("index not found");
        }
        return index;
    }

    /**
     * Parses string passed in to obtain name of the event.
     *
     * @param parameters Input String by user in the format: i/[INDEX] n/[EVENTNAME]
     * @return Name of event as a string.
     */
    public String parseEventName(String parameters) {
        String[] tokens = parameters.split(" ");
        String name = tokens[1].substring(2);
        return name;
    }

    /**
     * Parses input string to obtain date and time of the event.
     *
     * @param parameters Input String by user in the format: i/[INDEX] d/[EVENTDATE] t/[EVENTTIME]
     * @return Date and time of the event as a string.
     */
    public String parseEventDateTime(String parameters) {
        String[] tokens = parameters.split(" ");
        String date = tokens[1].substring(2);
        String time = tokens[2].substring(2);
        String datetime = date + " " + time;
        return datetime;
    }

    /**
     * Parses input string to obtain venue of the event.
     *
     * @param parameters Input String by user in the format: i/[INDEX] v/[EVENTVENUE]
     * @return Venue of the event as a String
     */
    public String parseVenue(String parameters) {
        String[] tokens = parameters.split(" ");
        String venue = tokens[1].substring(2);
        return venue;
    }

    // TODO: [r/FREQ[/TIME or /DAY]]
    /**
     * Parse parameters based on the following format:
     * n/EVENTNAME [t/EVENTTIME] [d/EVENTDATE] [v/EVENTVENUE].
     * @param parameters original parameters
     * @return an Event object with the relevant information
     */
    public Event parseEvent(String parameters) throws DukeException {
        String[] tokens = parameters.split(" ");

        splitByEventFlags(tokens);

        String datetime = date + " " + time;

        return new Event(name, datetime, venue);
    }

    private void splitByEventFlags(String[] tokens) throws DukeException {
        String mostRecent = null;
        for (String token : tokens) {
            if (token.length() < 2) {
                if (mostRecent == null) {
                    throw new DukeException("invalid flag, less than 2 chars long");
                } else {
                    mostRecent += (" " + token);    //TODO
                }
            }

            switch (token.substring(0,2)) {
            case "n/":
                ensureNotDuplicateFlag(name, "duplicate name flag");
                name += token.substring(2);
                mostRecent = name;
                break;
            case "t/":
                ensureNotDuplicateFlag(time, "duplicate time flag");
                time += token.substring(2);
                mostRecent = time;
                break;
            case "d/":
                ensureNotDuplicateFlag(date, "duplicate date flag");
                date += token.substring(2);
                mostRecent = date;
                break;
            case "v/":
                ensureNotDuplicateFlag(venue, "duplicate venue flag");
                venue += token.substring(2);
                mostRecent = venue;
                break;
            case "i/":
                break;
            default:
                // assumes that all valid flags have been processed before this line
                if (isUnknownFlag(token)) {
                    throw new DukeException("unknown flag");
                }
                if (mostRecent == null) {
                    throw new DukeException("parameter without flag");
                }
                mostRecent += (" " + token);
            }
        }
    }

    private void ensureNotDuplicateFlag(String name, String message) throws DukeException {
        if (!name.isEmpty()) {
            throw new DukeException(message);
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
