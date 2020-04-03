package seedu.parser;

import seedu.event.Event;
import seedu.event.Seminar;
import seedu.exception.PacException;

public class EventParser {
    private String name;
    private String date;
    private String time;
    private String venue;
    private int index;

    public EventParser() {
        this.name = "";
        this.date = "";
        this.time = "";
        this.venue = "";
        this.index = -1;
    }

    public void parse(String parameters) throws PacException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
    }

    /**
     * Parse parameters based on the following format:
     * n/EVENTNAME [t/EVENTTIME] [d/EVENTDATE] [v/EVENTVENUE].
     * @param parameters original parameters
     * @return an Event object with the relevant information
     */
    public Event parseEvent(String parameters) throws PacException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        String datetime = date + " " + time;
        if (name.equals("") && venue.equals("")) {
            throw new PacException("Please at least provide a name or venue for event.");
        }
        return new Event(name, datetime, venue);
    }

    public Seminar parseSeminar(String parameters) throws PacException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
        String datetime = date + " " + time;
        if (name.equals("") && venue.equals("")) {
            throw new PacException("Please at least provide a name or venue for seminar.");
        }
        return new Seminar(name, datetime, venue);
    }

    private void splitByEventFlags(String[] tokens) throws PacException {
        String mostRecent = null;
        for (String token : tokens) {
            if (token.length() < 2) {
                if (mostRecent == null) {
                    throw new PacException("Flag is too short.");
                } else if (validFlagToAppend(mostRecent)) {
                    append(mostRecent, token);
                }
            } else {
                switch (token.substring(0, 2).toLowerCase()) {
                case "n/":
                    ensureNotDuplicateFlag(name, "name");
                    name += token.substring(2);
                    mostRecent = "name";
                    break;
                case "t/":
                    ensureNotDuplicateFlag(time, "time");
                    time += token.substring(2);
                    mostRecent = "time";
                    break;
                case "d/":
                    ensureNotDuplicateFlag(date, "date");
                    date += token.substring(2);
                    mostRecent = "date";
                    break;
                case "v/":
                    ensureNotDuplicateFlag(venue, "venue");
                    venue += token.substring(2);
                    mostRecent = "venue";
                    break;
                case "i/":
                    ensureNotDuplicateFlag(index, "index");
                    try {
                        index = Integer.parseInt(token.substring(2));
                    } catch (NumberFormatException m) {
                        throw new PacException("Please provide a valid index.");
                    }
                    mostRecent = "index";
                    break;
                default:
                    // assumes that all valid flags have been processed before this line
                    if (isUnknownFlag(token)) {
                        throw new PacException("Please provide a valid flag.");
                    }
                    if (mostRecent == null) {
                        throw new PacException("Please provide a flag for your parameter.");
                    }
                    if (validFlagToAppend(mostRecent)) {
                        append(mostRecent, token);
                    }
                }
            }
        }
    }

    private boolean validFlagToAppend(String flag) {
        return flag.equals("name") || flag.equals("venue");
    }

    /**
     * Append a string to the most recently added parameter.
     * @param mostRecent the most recently added parameter
     * @param token the string to be appended
     */
    private void append(String mostRecent, String token) throws PacException {
        if (token.isEmpty() || token.equals(" ")) {
            return;
        }
        switch (mostRecent) {
        case "name":
            name += name.isEmpty() ? token : (" " + token);
            break;
        case "venue":
            venue += venue.isEmpty() ? token : (" " + token);
            break;
        default:
            throw new PacException("I am not sure '" + token + "' belongs to which flag. Is a flag provided beforehand?");
        }
    }

    private void ensureNotDuplicateFlag(String value, String name) throws PacException {
        if (!value.isEmpty()) {
            throw new PacException("Please provide only 1 " + name + " flag.");
        }
    }

    private void ensureNotDuplicateFlag(int value, String name) throws PacException {
        if (value != -1) {
            throw new PacException("Please provide only 1 " + name + " flag.");
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

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public int getIndex() {
        return index;
    }
}