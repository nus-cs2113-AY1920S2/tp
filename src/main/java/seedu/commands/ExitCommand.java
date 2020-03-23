package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;
import seedu.duke.UI;
import seedu.subjects.SubjectList;

/**
 * Command class for the Exit Command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = "To exit, type command: exit";

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(SubjectList subjectList) {
        UI.exitEsc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
