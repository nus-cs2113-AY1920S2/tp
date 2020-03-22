package seedu.nuke.command.filtercommand.deletecommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.filtercommand.FilterCommand;
import seedu.nuke.directory.Directory;

import java.util.ArrayList;

/**
 * <h3>Delete Command</h3>
 * A general <b>Command</b> to delete Directory/Directories.
 *
 * @see Command
 */
public abstract class DeleteCommand extends FilterCommand {
    protected abstract CommandResult executeInitialDelete(ArrayList<Directory> filteredList);
}
