package seedu.commands;

import seedu.cards.CardList;
import seedu.exception.EscException;

/**
 * Parent command class for the other commands.
 */
public class Command {

    public static final String COMMAND_WORD = null;

    public static final String MESSAGE_USAGE = null;

    /**
     * Executes the command.
     */
    public void execute(CardList cards) throws EscException {
        throw new EscException("This method is to be implemented by child classes");
    }

    /** Check if it is an exit command.
     * @return true or false depending on whether it is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
