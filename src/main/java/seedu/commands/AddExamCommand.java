package seedu.commands;

import seedu.exams.Exam;
import seedu.exception.EscException;
import seedu.subjects.SubjectList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Command class for the AddExamCommand.
 */
public class AddExamCommand extends Command {

    public static final String COMMAND_WORD = "addevent";

    public static final String MESSAGE_USAGE = "To add an upcoming event, "
            + "type command: addevent e/[DESCRIPTION] d/[DATE]";

    private Exam exam;

    /**
     * Initialises the parameters for score command.
     */
    public AddExamCommand(Exam exam) {
        this.exam = exam;
    }

    /**
     * View all the scores attained for a subject.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        ArrayList<Exam> exams = subjectList.getExamDates();
        exams.add(this.exam);
    }


}
