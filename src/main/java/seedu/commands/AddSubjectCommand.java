package seedu.commands;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

public class AddSubjectCommand extends AddCommand {

    public static final String COMMAND_WORD = "addsubject";

    public static final String MESSAGE_USAGE = "To add subject, type command:​ add s/[SUBJECT NAME]";

    private String name;

    /**
     * Initialises the parameters for subject creation.
     */
    public AddSubjectCommand(String name) {
        this.name = name;
    }

    /**
     * Adds a subject into the application.
     */
    public void execute(SubjectList subjects) {
        Subject newSubject = new Subject(this.name);
        subjects.addSubject(newSubject);
    }
}
