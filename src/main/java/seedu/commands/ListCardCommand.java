package seedu.commands;

import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

/**
 * Command Class for the ListCard command.
 */
public class ListCardCommand extends ListCommand {

    public static final String COMMAND_WORD = "listcard";

    public static final String MESSAGE_USAGE = "To list cards, type command: listcard s/[SUBJECT INDEX] ";

    private int subjectIndex;

    /**
     * Initialises the parameter for list card.
     */
    public ListCardCommand(int subjectIndex) {
        this.subjectIndex = subjectIndex - 1;
    }

    /**
     * Returns the subject index.
     */
    public int getSubjectIndex() {
        return subjectIndex;
    }

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        Subject chosenSubject = subjectList.getSubject(this.subjectIndex);
        chosenSubject.getCardList().listCards();
    }
}
