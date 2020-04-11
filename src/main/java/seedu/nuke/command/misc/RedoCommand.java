package seedu.nuke.command.misc;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.exception.CorruptedFileException;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.io.IOException;
import java.util.EmptyStackException;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_TRAVERSE_ERROR;
import static seedu.nuke.util.Message.MESSAGE_REDO_SUCCESS;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = String.format(
            "%s - Redo the undo command\n"
            + "Format: %s\n",
            COMMAND_WORD, FORMAT);

    public static final String MESSAGE_REDO_UNSUCCESSFUL = "Sorry, there was an IO error when redoing the state.\n";
    public static final String MESSAGE_REDO_AT_END = "You are already at the newest state!\n";

    @Override
    public CommandResult execute() {
        try {
            ScreenShotManager.redo();
            return new CommandResult(MESSAGE_REDO_SUCCESS);
        } catch (IOException | CorruptedFileException e) {
            return new CommandResult(MESSAGE_REDO_UNSUCCESSFUL);
        } catch (EmptyStackException e) {
            return new CommandResult(MESSAGE_REDO_AT_END);
        }
    }
}

