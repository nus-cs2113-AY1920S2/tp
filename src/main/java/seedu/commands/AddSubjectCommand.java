package seedu.commands;

import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

public class AddSubjectCommand extends AddCommand {

    public static final String COMMAND_WORD = "addsubject";

    public static final String MESSAGE_USAGE = "To add subject, type command:​ add s/[SUBJECT NAME]";

    private String subjectName;

    /**
     * Initialises the parameters for subject creation.
     */
    public AddSubjectCommand(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Returns the subject name.
     */
    public String getSubjectName() {
        return this.subjectName;
    }

    /**
     * Adds a subject into the application.
     */
    public void execute(SubjectList subjects) {
        Subject newSubject = new Subject(this.subjectName);
        subjects.addSubject(newSubject);
    }
}
