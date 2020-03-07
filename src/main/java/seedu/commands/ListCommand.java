package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;

/**
 * Command class for the List command.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(CardList cards) throws Exception {
        Duke.listCards(cards.getCards());
    }
}
