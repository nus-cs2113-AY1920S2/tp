package seedu.nuke.command;

import seedu.nuke.data.ScreenShot;
import seedu.nuke.data.ScreenShotManager;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "un";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    private ScreenShotManager screenShotManager;

    public UndoCommand(ScreenShotManager screenShotManager) {
        this.screenShotManager = screenShotManager;
    }

    @Override
    public CommandResult execute() {
        //change to the screen

        //update new screen shot

        return null;
    }
}
