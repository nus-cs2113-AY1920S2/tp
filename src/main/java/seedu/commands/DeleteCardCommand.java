package seedu.commands;

import seedu.cards.CardList;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

public class DeleteCardCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deletecard";

    public static final String MESSAGE_USAGE = "\tTo delete card, type command: delete s/[SUBJECT INDEX] c/[CARD INDEX]";

    private int subjectIndex;

    private int cardIndex;

    public DeleteCardCommand(int subjectIndex, int cardIndex) {
        this.subjectIndex = subjectIndex;
        this.cardIndex = cardIndex;
    }

    /**
     * Returns the index of the subject to be deleted.
     */
    public int getSubjectIndexIndex() {
        return subjectIndex;
    }

    /**
     * Returns the index of the card to be deleted.
     */
    public int getCardIndex() {
        return cardIndex;
    }

    /** Removes a card from the application. */
    public void execute(SubjectList subjectList) throws EscException {
        Subject chosenSubject = subjectList.getSubject(this.subjectIndex);
        CardList cardList = chosenSubject.getCardList();
        cardList.removeCard(this.cardIndex);
        //ui display result list
    }
}
