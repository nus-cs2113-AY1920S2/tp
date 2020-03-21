package seedu.command;

import seedu.exception.DukeException;

public abstract class Command {
    public abstract void execute() throws DukeException;
}
