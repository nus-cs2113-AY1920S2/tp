package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.DETAILS_BULLET_FORM;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.FLASHCARD_NAME;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.SUMMARY;

public class ShowCommandTest {

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
    private final PrintStream backupStdout = System.out;
    private FlashcardList fullFlashcardList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(capturedOut));
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
    }

    @AfterEach
    public void restoreStdout() {
        System.setOut(backupStdout);
    }

    @Test
    public void showCommand_execute_showEventFlashcardSuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedEventOutput = new StringBuilder();
        expectedEventOutput.append("These are the flashcard details:" + System.lineSeparator());
        expectedEventOutput.append("Event name: Event 1" + System.lineSeparator());
        expectedEventOutput.append("Event period: July 31, 1843-December 25, 1892" + System.lineSeparator());
        expectedEventOutput.append("Summary: This is an event summary" + System.lineSeparator());
        expectedEventOutput.append("Details:" + System.lineSeparator());
        expectedEventOutput.append(DETAILS_BULLET_FORM);

        ShowCommand showCommand = new ShowCommand(fullFlashcardList, 0);
        CommandFeedback showCommandFeedback = showCommand.execute();
        assertEquals(expectedEventOutput.toString(), showCommandFeedback.toString());
    }

    @Test
    public void showCommand_execute_showPersonFlashcardSuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedPersonOutput = new StringBuilder();
        expectedPersonOutput.append("These are the flashcard details:" + System.lineSeparator());
        expectedPersonOutput.append("Person name: Person 1" + System.lineSeparator());
        expectedPersonOutput.append("Born: July 31, 1843" + System.lineSeparator());
        expectedPersonOutput.append("Died: December 25, 1892" + System.lineSeparator());
        expectedPersonOutput.append("Summary: This is a person's summary" + System.lineSeparator());
        expectedPersonOutput.append("Details:" + System.lineSeparator());
        expectedPersonOutput.append(DETAILS_BULLET_FORM);

        ShowCommand showCommand = new ShowCommand(fullFlashcardList, 1);
        CommandFeedback showCommandFeedback = showCommand.execute();
        assertEquals(expectedPersonOutput.toString(), showCommandFeedback.toString());
    }

    @Test
    public void showCommand_execute_showOtherFlashcardSuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedOtherOutput = new StringBuilder();
        expectedOtherOutput.append("These are the flashcard details:" + System.lineSeparator());
        expectedOtherOutput.append("Title: Title 1" + System.lineSeparator());
        expectedOtherOutput.append("Summary: This is a summary" + System.lineSeparator());
        expectedOtherOutput.append("Details:" + System.lineSeparator());
        expectedOtherOutput.append(DETAILS_BULLET_FORM);

        ShowCommand showCommand = new ShowCommand(fullFlashcardList, 2);
        CommandFeedback showCommandFeedback = showCommand.execute();
        assertEquals(expectedOtherOutput.toString(), showCommandFeedback.toString());
    }

    @Test
    public void showCommand_getFlashcardFromInvalidIndex_throwsInvalidFlashcardIndexException()
        throws DuplicateFlashcardNameException {
        FlashcardList flashcardList = new FlashcardList();
        flashcardList.addFlashcard(new OtherFlashcard(FLASHCARD_NAME, SUMMARY, DETAILS));
        Ui ui = new Ui();
        ShowCommand showCommand = new ShowCommand(flashcardList, 1);
        assertThrows(
            InvalidFlashcardIndexException.class,
            showCommand::execute,
            "Expected InvalidFlashcardIndexException"
        );
    }
}
