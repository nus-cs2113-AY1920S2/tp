package seedu.parser;

import seedu.event.Event;
import seedu.event.Seminar;
import seedu.exception.PacException;

public class EventParser {
    private static final String SHORT_FLAG_ERROR_MESSAGE = "Flag is too short.";
    private static final String INVALID_FLAG_ERROR_MESSAGE = "Please provide a valid flag.";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Please provide a valid index.";
    private static final String NO_FLAG_ERROR_MESSAGE = "Please provide a flag for your parameter.";
    private static final String NO_NAME_VENUE_FOR_EVENT_ERROR_MESSAGE = "Please at least provide a name or venue "
            + "for event.";
    private static final String NO_NAME_VENUE_FOR_SEMINAR_ERROR_MESSAGE = "Please at least provide a name or venue "
            + "for seminar.";

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

    /**
     * Parse parameters based on the following format:
     * n/EVENTNAME [t/EVENTTIME] [d/EVENTDATE] [v/EVENTVENUE].
     * @param parameters original parameters
     */
    public void parse(String parameters) throws PacException {
        String[] tokens = parameters.split(" ");
        splitByEventFlags(tokens);
    }

    private void splitByEventFlags(String[] tokens) throws PacException {
        String mostRecent = null;
        for (String token : tokens) {
            if (token.length() < 2) {
                if (mostRecent == null) {
                    throw new PacException(SHORT_FLAG_ERROR_MESSAGE);
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
                        throw new PacException(INVALID_INDEX_ERROR_MESSAGE);
                    }
                    mostRecent = "index";
                    break;
                default:
                    // assumes that all valid flags have been processed before this line
                    if (isUnknownFlag(token)) {
                        throw new PacException(INVALID_FLAG_ERROR_MESSAGE);
                    }
                    if (mostRecent == null) {
                        throw new PacException(NO_FLAG_ERROR_MESSAGE);
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
            throw new PacException("I am not sure '" + token + "' belongs to which flag. "
                    + "Is a flag provided beforehand?");
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

    /**
     * Returns the value of name after parsing.
     * @return name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of datetime after parsing.
     * @return datetime of event
     */
    public String getDateTime() {
        return date + " " + time;
    }

    /**
     * Returns the value of venue after parsing.
     * @return venue of event
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Returns the value of index after parsing.
     * @return index of event
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns an Event object after parsing its parameters.
     * @return an Event object
     * @throws PacException if both name and venue are empty
     */
    public Event getEvent() throws PacException {
        String datetime = date + " " + time;
        if (name.equals("") && venue.equals("")) {
            throw new PacException(NO_NAME_VENUE_FOR_EVENT_ERROR_MESSAGE);
        }
        return new Event(name, datetime, venue);
    }

    /**
     * Returns an Seminar object after parsing its parameters.
     * @return an Seminar object
     * @throws PacException if both name and venue are empty
     */
    public Seminar getSeminar() throws PacException {
        String datetime = date + " " + time;
        if (name.equals("") && venue.equals("")) {
            throw new PacException(NO_NAME_VENUE_FOR_SEMINAR_ERROR_MESSAGE);
        }
        return new Seminar(name, datetime, venue);
    }
}