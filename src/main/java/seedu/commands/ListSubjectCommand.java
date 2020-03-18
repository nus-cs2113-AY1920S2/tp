package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;

/**
 * Command Class for the AddCard command.
 */
public class ListSubjectCommand extends ListCommand {

    public static final String COMMAND_WORD = "listsubject";

    public static final String MESSAGE_USAGE = "To list subjects, type command: listsubject";

    /**
     * Lists the cards currently stored in the application.
     */
    @Override
    public void execute(CardList cards) {
        Duke.listCards(cards.getCards());
    }
}
