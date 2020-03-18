package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;

/**
 * Command Class for the ListCard command.
 */
public class ListCardCommand extends ListCommand {

    public static final String COMMAND_WORD = "listcard";

    public static final String MESSAGE_USAGE = "To list cards, type command: listcard s/[SUBJECT INDEX] ";

    private int index;

    /**
     * Initialises the parameter for list card.
     */
    public ListCardCommand(int index) {
        this.index = index;
    }

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(CardList cards) {
        Duke.listCards(cards.getCards());
    }
}
