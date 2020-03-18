package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;

/**
 * Command class for the List command.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "To list cards, type command: list";

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(CardList cards) {
        assert true : "This method is to be implemented by child ListCommand classes";
    }
}
