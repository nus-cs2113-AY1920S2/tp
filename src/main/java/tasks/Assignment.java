package tasks;

import seedu.duke.Parser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
                + ")"
                + System.lineSeparator()
                + "   "
                + comments;
    }
}
