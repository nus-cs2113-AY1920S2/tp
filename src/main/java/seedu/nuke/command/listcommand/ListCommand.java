package seedu.nuke.command.listcommand;

import seedu.nuke.command.Command;
import seedu.nuke.directory.Directory;

import java.util.ArrayList;

public abstract class ListCommand extends Command {
    protected abstract ArrayList<Directory> createFilteredList();
}
