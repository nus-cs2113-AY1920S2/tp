package seedu.command;

import seedu.exception.PACException;

public abstract class Command {
    public abstract void execute() throws PACException;
}
