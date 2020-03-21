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
        this.subjectIndex = subjectIndex;
    }

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        Subject subject = subjectList.getSubject(this.subjectIndex);
        subjectList.listCardsInSubject(subject);
    }
}
