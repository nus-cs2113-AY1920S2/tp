package seedu.nuke.command;

import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DirectoryTraversalOutOfBoundsException;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_DIRECTORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_FAILED_DIRECTORY_TRAVERSAL;

public class ChangeDirectoryCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <next directory name> to traverse up and "
            + COMMAND_WORD + " .. to traverse down";

    private String nextDirectoryName;

    /**
     * Constructs the command to traverse down into the next directory with the given name.
     *
     * @param nextDirectoryName
     *  The name of the next directory
     */
    public ChangeDirectoryCommand(String nextDirectoryName) {
        this.nextDirectoryName = nextDirectoryName;
    }

    /**
     * Constructs the command to traverse up from the current directory.
     */
    public ChangeDirectoryCommand() {
        this(null);
    }

    /**
     * Executes the Change Directory Command to traverse up or down the current Directory.
     *
     * @return
     *  The Command result of the execution
     */
    @Override
    public CommandResult execute() {
        ////context switch
        //setCurrentDirectory(this.directoryToChange);
        //if (directoryToChange instanceof Root) {
        //    return new CommandResult(MESSAGE_ROOT_CHANGE_SUCCESSFUL);
        //}
        //return new CommandResult(MESSAGE_MODULE_CHANGE_SUCCESSFUL);
        try {
            if ((nextDirectoryName != null)) {
                Directory nextDirectory = DirectoryTraverser.findNextDirectory(nextDirectoryName);
                DirectoryTraverser.traverseDown(nextDirectory);
            } else {
                DirectoryTraverser.traverseUp();
            }
            // No feedback for successful traversal
            return new CommandResult("");
        } catch (DirectoryTraversalOutOfBoundsException e) {
            return new CommandResult(MESSAGE_FAILED_DIRECTORY_TRAVERSAL);
        } catch (DataNotFoundException e) {
            return new CommandResult(MESSAGE_DIRECTORY_NOT_FOUND);
        }
    }
}
