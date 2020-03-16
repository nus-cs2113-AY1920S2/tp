package seedu.nuke.command;

import seedu.nuke.data.ScreenShot;
import seedu.nuke.data.ScreenShotManager;

import static seedu.nuke.util.Message.MESSAGE_UNDO_SUCCESSFUL;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "un";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public UndoCommand() {

    }

    @Override
    public CommandResult execute() {
        //change to the screen one step before
        return new CommandResult(MESSAGE_UNDO_SUCCESSFUL);
    }
}
