package seedu.nuke.command;

import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskFileManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.*;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.TraverseDirectoryOutOfBoundsException;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_DIRECTORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_FAILED_DIRECTORY_TRAVERSAL;

public class ChangeDirectoryCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <next directory name> to traverse up and " +
            COMMAND_WORD + " .. to traverse down";

    private String nextDirectoryName;

    public ChangeDirectoryCommand(String nextDirectoryName) {
        this.nextDirectoryName = nextDirectoryName;
    }

    public ChangeDirectoryCommand() {
        this(null);
    }

    @Override
    public CommandResult execute() {
//        //context switch
//        setCurrentDirectory(this.directoryToChange);
//        if (directoryToChange instanceof Root) {
//            return new CommandResult(MESSAGE_ROOT_CHANGE_SUCCESSFUL);
//        }
//        return new CommandResult(MESSAGE_MODULE_CHANGE_SUCCESSFUL);
        try {
            if ((nextDirectoryName != null)) {
                Directory nextDirectory = DirectoryTraverser.findNextDirectory(nextDirectoryName);
                DirectoryTraverser.traverseDown(nextDirectory);
            } else {
                DirectoryTraverser.traverseUp();
            }
            // No feedback for successful traversal
            return new CommandResult("");
        } catch (TraverseDirectoryOutOfBoundsException e) {
            return new CommandResult(MESSAGE_FAILED_DIRECTORY_TRAVERSAL);
        } catch (DataNotFoundException e) {
            return new CommandResult(MESSAGE_DIRECTORY_NOT_FOUND);
        }
    }
}
