package seedu.commands;

import seedu.exams.Exam;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

import java.util.ArrayList;

/**
 * Command Class for the ListCard command.
 */
public class ListExamCommand extends ListCommand {

    public static final String COMMAND_WORD = "listevents";

    public static final String MESSAGE_USAGE = "To list cards, type command: listevents";

    /**
     * Lists all exams currently stored in the application.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        ArrayList<Exam> exams = subjectList.getExamDates();
        subjectList.listUpcoming(exams);
    }

}
