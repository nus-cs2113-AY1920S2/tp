package seedu.commands;

import seedu.cards.CardList;
import seedu.duke.Quiz;
import seedu.exception.EscException;
import seedu.subjects.Subject;
import seedu.subjects.SubjectList;

/**
 * Command class for the Quiz Command.
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = "To quiz, type command:​quiz s/[SUBJECT INDEX] n/[NUMBER OF QUESTIONS]";

    private int subjectIndex;

    private int numToQuiz;

    public QuizCommand(int subjectIndex, int numToQuiz) {
        this.subjectIndex = subjectIndex - 1;
        this.numToQuiz = numToQuiz;
    }

    /**
     * Chooses a random card and displays it's question and answer.
     */
    @Override
    public void execute(SubjectList subjectList) throws EscException {
        Subject chosenSubject = subjectList.getSubject(this.subjectIndex);
        Quiz.quizQuestion(chosenSubject, numToQuiz);
    }
}
