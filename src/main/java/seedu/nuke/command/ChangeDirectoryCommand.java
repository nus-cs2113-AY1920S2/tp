package seedu.nuke.command;

import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Root;

import static seedu.nuke.util.Message.MESSAGE_MODULE_CHANGE_SUCCESSFUL;
import static seedu.nuke.util.Message.MESSAGE_ROOT_CHANGE_SUCCESSFUL;

public class ChangeDirectoryCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <module code>";
    private Directory directoryToChange;

    public ChangeDirectoryCommand(Directory toChange) {
        this.directoryToChange = toChange;
    }

    @Override
    public CommandResult execute() {
        //context switch
        setCurrentDirectory(this.directoryToChange);
        if (directoryToChange instanceof Root) {
            return new CommandResult(MESSAGE_ROOT_CHANGE_SUCCESSFUL);
        }
        return new CommandResult(MESSAGE_MODULE_CHANGE_SUCCESSFUL);
    }
}
