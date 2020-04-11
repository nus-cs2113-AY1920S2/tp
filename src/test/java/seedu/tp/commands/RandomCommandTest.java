package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.NEWLINE;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_RANDOM_COMMAND_INPUT;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;

public class RandomCommandTest {

    private static final EventFlashcard EVENT_FLASHCARD = new EventFlashcard(
        "Event 1",
        START_LOCAL_DATE,
        END_LOCAL_DATE,
        "This is an event summary",
        DETAILS
    );
    private static final PersonFlashcard PERSON_FLASHCARD = new PersonFlashcard(
        "Person 1",
        START_LOCAL_DATE,
        END_LOCAL_DATE,
        "This is a person's summary",
        DETAILS
    );
    private static final OtherFlashcard OTHER_FLASHCARD = new OtherFlashcard(
        "Title 1",
        "This is a summary",
        DETAILS
    );
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final InputStream backupStdin = System.in;
    private final PrintStream backupStdout = System.out;
    private FlashcardList fullFlashcardList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    public void restoreStdinStdout() {
        System.setIn(backupStdin);
        System.setOut(backupStdout);
    }

    @Test
    public void randomCommand_execute_Successfully() throws HistoryFlashcardException {
        FlashcardList expectedRandomList = new FlashcardList(fullFlashcardList);
        Collections.shuffle(expectedRandomList.getFlashcards(), new Random(System.currentTimeMillis() / 1000));
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append(expectedRandomList.getFlashcardAtIdx(0).toString()).append(System.lineSeparator());

        expectedOutput.append("Do you want to mark this flashcard as reviewed?").append(System.lineSeparator());
        expectedOutput.append(NEWLINE);
        expectedOutput.append("You have marked the following flashcard as Reviewed:").append(System.lineSeparator());
        expectedOutput.append(expectedRandomList.getFlashcardAtIdx(0).getName()).append(System.lineSeparator());
        expectedOutput.append(expectedRandomList.getFlashcardAtIdx(1).toString()).append(System.lineSeparator());

        expectedOutput.append("Do you want to mark this flashcard as reviewed?").append(System.lineSeparator());
        expectedOutput.append(NEWLINE);
        expectedOutput.append(expectedRandomList.getFlashcardAtIdx(2).toString()).append(System.lineSeparator());

        expectedOutput.append("Do you want to mark this flashcard as reviewed?").append(System.lineSeparator());
        expectedOutput.append(NEWLINE);
        expectedOutput.append("You have marked the following flashcard as Reviewed:").append(System.lineSeparator());
        expectedOutput.append(expectedRandomList.getFlashcardAtIdx(2).getName()).append(System.lineSeparator());
        expectedOutput.append("You have just gone through all the flashcard(s).").append(System.lineSeparator());
        expectedOutput.append("You have marked 2 flashcard(s) as reviewed this time.").append(System.lineSeparator());
        expectedOutput.append("You still have 1 unreviewed flashcard(s).")
            .append(System.lineSeparator()).append((NEWLINE));

        ByteArrayInputStream simulatedSystemIn = new ByteArrayInputStream(SIMULATED_RANDOM_COMMAND_INPUT.getBytes());
        System.setIn(simulatedSystemIn);
        RandomCommand randomCommand = new RandomCommand(fullFlashcardList, new Ui());
        randomCommand.execute();
        assertEquals(expectedOutput.toString(), capturedOut.toString());
    }
}
