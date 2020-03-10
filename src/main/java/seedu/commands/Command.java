package seedu.commands;

import seedu.cards.CardList;

/**
 * Parent command class for the other commands.
 */
public class Command {

    /**
     * Executes the command.
     */
    public void execute(CardList cards) throws Exception {
        throw new Exception("This method is to be implemented by child classes");
    }

    /** Check if it is an exit command.
     * @return true or false depending on whether it is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
