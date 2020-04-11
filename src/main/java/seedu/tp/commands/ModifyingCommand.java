package seedu.tp.commands;

import seedu.tp.storage.Savable;
import seedu.tp.storage.Storage;

import java.io.IOException;

/**
 * Abstract class for commands that modify an object and need to save to disk after executing.
 */
public abstract class ModifyingCommand extends Command {
    Storage storage;

    protected ModifyingCommand() {
        this.storage = Storage.getInstance();
    }

    protected CommandFeedback save(Savable savable) {
        try {
            LOGGER.info("Attempting to save " + savable.getFileName() + " to disk...");
            storage.save(savable);
            LOGGER.info("Successfully saved " + savable.getFileName() + " to disk.");
            return new CommandFeedback();
        } catch (IOException e) {
            LOGGER.warning("Save to disk failed for " + savable.getFileName());
            return new CommandFeedback("Uh Oh. Couldn't save this change. Sorry.");
        }
    }
}
