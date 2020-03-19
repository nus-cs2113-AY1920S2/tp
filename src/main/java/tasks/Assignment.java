package tasks;

import common.Messages;
import seedu.atas.Parser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

public class Assignment extends Task {
    public static final String ASSIGNMENT_ICON = "A";
    protected String module;
    protected LocalDateTime deadline;

    /**
     * Assignment object.
     * @param name name of Assignment
     * @param module module for Assignment
     * @param deadline deadline of Assignment
     * @param comments comments for Assignment
     */
    public Assignment(String name, String module, LocalDateTime deadline, String comments) {
        super(name, comments);
        this.module = module;
        this.deadline = deadline;
    }

    public String getModule() {
        return module;
    }

    @Override
    public LocalDateTime getDateAndTime() {
        return deadline;
    }

    @Override
    public LocalDate getDate() {
        return deadline.toLocalDate();
    }

    @Override
    public LocalTime getTime() {
        return deadline.toLocalTime();
    }

    @Override
    public String toString() {
        return "[" + ASSIGNMENT_ICON + "]"
                + super.toString()
                + " (by: "
                + deadline.format(Parser.PRINT_DATE_FORMAT)
                + " | mod: "
                + module
                + ")"
                + System.lineSeparator()
                + Messages.COMMENTS_INDENT
                + comments;
    }

    @Override
    public String encodeTask() {
        StringJoiner sj = new StringJoiner(STORAGE_DELIMITER);
        sj.add(ASSIGNMENT_ICON);
        sj.add(isDone ? "true" : "false");
        sj.add(name);
        sj.add(module);
        sj.add(deadline.format(Parser.INPUT_DATE_FORMAT));
        sj.add(comments);
        return sj.toString();
    }

    /**
     * Converts an encoded Assignment back to an Assignment object.
     * @param encodedTask Assignment encoded using encodedTask()
     * @return Assignment with the correct attributes set
     * @throws DateTimeParseException if encoded deadline cannot be parsed
     * @throws IndexOutOfBoundsException if encodedTask is not a String returned by calling encodeTask() on
     *              an Assignment
     */
    public static Assignment decodeTask(String encodedTask)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] tokens = encodedTask.split("\\" + STORAGE_DELIMITER);
        assert tokens[0].equals(ASSIGNMENT_ICON);
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        String name = tokens[2];
        String module = tokens[3];
        LocalDateTime deadline = Parser.parseDate(tokens[4]);
        String comments = tokens[5];
        assert tokens.length == 6;
        Assignment assignment = new Assignment(name, module, deadline, comments);
        if (isDone) {
            assignment.setDone();
        }
        return assignment;
    }
}
