package seedu.nuke.command;

import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.exception.CorruptedFileException;

import java.io.IOException;
import java.util.EmptyStackException;

import static seedu.nuke.util.Message.MESSAGE_UNDO_SUCCESS;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_UNDO_UNSUCCESSFUL = "Something goes wrong here!\n";
    public static final String MESSAGE_UNDO_AT_BEGINNING = "you are already at the initial state!\n";

    @Override
    public CommandResult execute() {
        try {
            ScreenShotManager.revertToLastScreenShot();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } catch (IOException | CorruptedFileException e) {
            return new CommandResult(MESSAGE_UNDO_UNSUCCESSFUL);
        } catch (EmptyStackException e) {
            return new CommandResult(MESSAGE_UNDO_AT_BEGINNING);
        }
    }
}
