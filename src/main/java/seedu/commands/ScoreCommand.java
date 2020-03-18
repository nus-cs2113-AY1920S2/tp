package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Duke;

public class ScoreCommand extends Command {

    public static final String COMMAND_WORD = "score";

    public static final String MESSAGE_USAGE = "To view score history of a subject, type command: score s/[SUBJECT INDEX]";

    private int index;

    /**
     * Initialises the parameters for score command.
     */
    public ScoreCommand(int index) {
        this.index = index;
    }

    /**
     * View all the scores attained for a subject.
     */
    @Override
    public void execute(CardList cards) {
        Duke.listCards(cards.getCards());
    }
}
