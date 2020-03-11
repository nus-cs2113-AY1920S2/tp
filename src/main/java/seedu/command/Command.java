package seedu.command;

import seedu.exception.DukeException;
import seedu.storage.Storage;
import seedu.ui.UI;

public abstract class Command {

    public abstract void execute(UI ui, Storage storage) throws DukeException;

}
