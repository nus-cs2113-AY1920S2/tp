package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /** Removes a card from the application. */
    public void execute(CardList cards) throws Exception {
        //UI displays card before deletion
        cards.removeCard(this.index);
        //UI displays successful card deletion
    }
}
