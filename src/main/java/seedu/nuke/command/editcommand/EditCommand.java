package seedu.nuke.command.editcommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;

public abstract class EditCommand extends Command {
    protected abstract CommandResult executeEdit();
}
