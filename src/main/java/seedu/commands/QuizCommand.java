package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Quiz;
import seedu.exception.EscException;

/**
 * Command class for the Quiz Command.
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = "\tTo quiz, type command:​quiz s/[SUBJECT INDEX]";

    /**
     * Chooses a random card and displays it's question and answer.
     */
    @Override
    public void execute(CardList cards) {
        Quiz.quizQuestion(cards);
    }
}
