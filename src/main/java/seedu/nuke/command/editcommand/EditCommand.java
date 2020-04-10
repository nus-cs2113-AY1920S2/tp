package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.directory.Directory;
import seedu.nuke.exception.DuplicateDataException;
import seedu.nuke.exception.ModuleNotProvidedException;

public abstract class EditCommand extends Command {
    protected abstract void edit(Directory toEdit) throws ModuleNotProvidedException, DuplicateDataException;
}
