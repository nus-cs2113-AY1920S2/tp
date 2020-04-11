package seedu.commands;

import seedu.exception.EscException;
import seedu.subjects.SubjectList;

/**
 * Command class for the DeleteSubjectCommand.
 */
public class DeleteSubjectCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deletesubject";

    public static final String MESSAGE_USAGE = "To delete subject, type command: deletesubject s/[SUBJECT INDEX]";

    private int subjectIndex;

    public DeleteSubjectCommand(int subjectIndex) {
        this.subjectIndex = subjectIndex - 1;
    }

    /**
     * Returns the index of the subject to be deleted.
     */
    public int getSubjectIndex() {
        return subjectIndex;
    }

    /** Removes a subject from the application. */
    public void execute(SubjectList subjectList) throws EscException {
        subjectList.removeSubject(this.subjectIndex);
    }
}
