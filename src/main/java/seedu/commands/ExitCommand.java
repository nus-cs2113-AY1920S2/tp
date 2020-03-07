package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;

/**
 * Command class for the Exit Command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    /**
     * Lists the cards currently stored in the application.
     */
    public void execute(CardList cards) {
        Duke.exitEsc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
