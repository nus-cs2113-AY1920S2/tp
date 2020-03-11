package seedu.command;

import seedu.exception.DukeException;
import seedu.storage.Storage;
import seedu.ui.Ui;

public abstract class Command {

    public abstract void execute(Ui ui, Storage storage) throws DukeException;

}
