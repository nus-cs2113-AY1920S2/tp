package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

public class EditCardCommand extends AddCommand {

    public static final String COMMAND_WORD = "editcard";

    public static final String MESSAGE_USAGE = "To edit card, type command:​editcard s/[SUBJECT INDEX]  c/[CARD INDEX] "
            + "q/[QUESTION] a/[ANSWER]";

    private Card card;

    private int subjectIndex;
    private int cardIndex;

    /**
     * Initialises the parameters for card creation.
     */
    public EditCardCommand(int subjectIndex, int cardIndex, Card card) {
        this.card = card;
        this.subjectIndex = subjectIndex - 1;
        this.cardIndex = cardIndex - 1;
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
        cardList.removeCardSilent(cardIndex);
        cardList.addCardSilent(card,chosenSubject,cardIndex);
        System.out.println("The selected card has been edited");
        System.out.println("Q: " + cardList.getCard(cardIndex).getQuestion() + " / "
                            + "A: " + cardList.getCard(cardIndex).getQuestion());
    }
}


