package seedu.commands;

import seedu.cards.CardList;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

public class DeleteSubjectCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deletesubject";

    public static final String MESSAGE_USAGE = "\tTo delete subject, type command: delete s/[SUBJECT INDEX]";

    private int subjectIndex;

    public DeleteSubjectCommand(int subjectIndex) {
        this.subjectIndex = subjectIndex;
    }

    /**
     * Returns the index of the subject to be deleted.
     */
    public int getSubjectIndex() {
        return subjectIndex;
    }

    /** Removes a card from the application. */
    public void execute(SubjectList subjectList) throws EscException {
        subjectList.removeSubject(this.subjectIndex);
        //ui display
    }
}
