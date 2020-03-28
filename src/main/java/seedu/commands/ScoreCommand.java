package seedu.commands;

import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

/**
 * Command class for the ScoreCommand.
 */
public class ScoreCommand extends Command {

    public static final String COMMAND_WORD = "score";

    public static final String MESSAGE_USAGE = "To view score history of a subject, "
            + "type command: score s/[SUBJECT INDEX]";

    private int subjectIndex;

    /**
     * Initialises the parameters for score command.
     */
    public ScoreCommand(int subjectIndex) {
        this.subjectIndex = subjectIndex - 1;
    }

    /**
     * View all the scores attained for a subject.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        Subject subject = subjectList.getSubject(subjectIndex);
        subject.showScores();
    }

}
