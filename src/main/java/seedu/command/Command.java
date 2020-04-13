package seedu.command;

import seedu.exception.PacException;

public abstract class Command {
    public abstract void execute() throws PacException;
}
