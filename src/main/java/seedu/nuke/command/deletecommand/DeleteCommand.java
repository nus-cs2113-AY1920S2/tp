package seedu.nuke.command.deletecommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Module;

import java.util.ArrayList;

public abstract class DeleteCommand extends Command {
    protected abstract CommandResult executeInitialDelete(ArrayList<Directory> filteredList);
}
