package seedu.nuke.command;

import seedu.nuke.data.ScreenShot;
import seedu.nuke.data.ScreenShotManager;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "un";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    private ScreenShot targetScreenShot;

    public UndoCommand() {

    }

    @Override
    public CommandResult execute() {
        //change to the screen one step before
        targetScreenShot = screenShotManager.unDo();
        return null;
    }
}
