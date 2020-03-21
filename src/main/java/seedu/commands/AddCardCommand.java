package seedu.commands;

import seedu.cards.Card;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

/**
 * Command Class for the AddCard command.
 * SubjectList has a CardList called cardOperation, please call the CardList from there.
 */
public class AddCardCommand extends AddCommand {

    public static final String COMMAND_WORD = "addcard";

    public static final String MESSAGE_USAGE = "\tTo add card, type command:​addcard s/[SUBJECT INDEX] q/[QUESTION] a/[ANSWER]";

    private Card card;

    private int subjectIndex;

    /**
     * Initialises the parameters for card creation.
     */
    public AddCardCommand(int subjectIndex, Card card) {
        this.card = card;
        this.subjectIndex = subjectIndex;
    }

    /**
     * Returns card to be added.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Adds a card into the application.
     */
    public void execute(SubjectList subjectList) throws EscException {
        Subject chosenSubject = subjectList.getSubject(this.subjectIndex);
    }
}
