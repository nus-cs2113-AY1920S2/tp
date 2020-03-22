package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;
import seedu.duke.UI;
import seedu.subjects.SubjectList;

/**
 * Command class for the Help Command.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "To view help menu, type command: help";

    /**
     * Displays the help menu.
     */
    @Override
    public void execute(SubjectList subjectList) {
        UI.printHelp();
    }
}
