package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Quiz;

/**
 * Command class for the Quiz Command.
 */
public class QuizCommand extends Command {
    public static final String COMMAND_WORD = "quiz";

    /**
     * Chooses a random card and displays it's question and answer.
     */
    @Override
    public void execute(CardList cards) throws Exception {
        Quiz.quizQuestion(cards);
    }
}
