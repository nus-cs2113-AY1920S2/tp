package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

/**
 * Command Class for the AddCard command.
 */
public class AddCardCommand extends AddCommand {

    public static final String COMMAND_WORD = "addcard";

    public static final String MESSAGE_USAGE = "To add card, type command:​addcard s/[SUBJECT INDEX] "
            + "q/[QUESTION] a/[ANSWER]";

    private Card card;

    private int subjectIndex;

    /**
     * Initialises the parameters for card creation.
     */
    public AddCardCommand(int subjectIndex, Card card) {
        this.card = card;
        this.subjectIndex = subjectIndex - 1;
    }

    /**
     * Returns card to be added.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Returns index of subject.
     */
    public int getSubjectIndex() {
        return subjectIndex;
    }

    /**
     * Adds a card into the application.
     */
    public void execute(SubjectList subjectList) throws EscException {
        Subject chosenSubject = subjectList.getSubject(this.subjectIndex);
        CardList cardList = chosenSubject.getCardList();
        cardList.addCard(this.card);
    }
}
