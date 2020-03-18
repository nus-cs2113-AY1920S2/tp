package seedu.commands;

import seedu.cards.CardList;
import seedu.exception.EscException;

/**
 * Parent command class for the other commands.
 */
public abstract class Command {

    public static final String COMMAND_WORD = null;

    public static final String MESSAGE_USAGE = null;

    /**
     * Executes the command.
     */
    public abstract void execute(CardList cards) throws EscException;

    /** Check if it is an exit command.
     * @return true or false depending on whether it is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
