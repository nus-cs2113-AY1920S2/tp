package seedu.commands;

import seedu.cards.CardList;
import seedu.exception.EscException;

public class DeleteSubjectCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deletesubject";

    public static final String MESSAGE_USAGE = "\tTo delete subject, type command: delete s/[SUBJECT INDEX]";

    private int subjectIndex;

    public DeleteSubjectCommand(int subjectIndex) {
        this.subjectIndex = subjectIndex;
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
