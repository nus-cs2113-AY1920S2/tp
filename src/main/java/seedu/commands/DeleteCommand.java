package seedu.commands;

import seedu.cards.CardList;
import seedu.exception.EscException;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "\tTo delete card, type command: delete [INDEX]";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the card to be deleted.
     */
    public int getIndex() {
        return index;
    }

    /** Removes a card from the application. */
    public void execute(CardList cards) throws EscException {
        //UI displays card before deletion
        cards.removeCard(this.index);
        //UI displays successful card deletion
    }
}
