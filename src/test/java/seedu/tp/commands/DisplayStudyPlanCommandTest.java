package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.studyplan.StudyPlanList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.LOCAL_DATE_1;
import static seedu.tp.utils.ExampleInputConstants.LOCAL_DATE_2;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;

public class DisplayStudyPlanCommandTest {

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

    private static final Map.Entry<LocalDate, List<Integer>> STUDY_PLAN_ENTRY_1 =
        new AbstractMap.SimpleEntry<>(LOCAL_DATE_1, Arrays.asList(0, 1));
    private static final Map.Entry<LocalDate, List<Integer>> STUDY_PLAN_ENTRY_2 =
        new AbstractMap.SimpleEntry<>(LOCAL_DATE_2, Arrays.asList(0, 2));

    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream backupStdout = System.out;
    private FlashcardList fullFlashcardList;
    private StudyPlanList studyPlanList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() throws InvalidFlashcardIndexException {
        System.setOut(new PrintStream(capturedOut));
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
        List<Map.Entry<LocalDate, List<Integer>>> studyPlans = Arrays.asList(STUDY_PLAN_ENTRY_1, STUDY_PLAN_ENTRY_2);
        studyPlanList = new StudyPlanList(studyPlans);
    }

    @AfterEach
    public void restoreStdout() {
        System.setOut(backupStdout);
    }

    @Test
    public void displayStudyPlanCommand_execute_success() {
        StringBuilder expectedEventOutput = new StringBuilder();
        expectedEventOutput.append("Date: 2020-01-18" + System.lineSeparator());
        expectedEventOutput.append("1: " + EVENT_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedEventOutput.append("3: " + OTHER_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedEventOutput.append("Date: 2020-02-27" + System.lineSeparator());
        expectedEventOutput.append("1: " + EVENT_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedEventOutput.append("2: " + PERSON_FLASHCARD.getShortDescription());

        DisplayStudyPlanCommand displayStudyPlanCommand = new DisplayStudyPlanCommand(studyPlanList, fullFlashcardList);
        CommandFeedback displayStudyPlanCommandFeedback = displayStudyPlanCommand.execute();
        assertEquals(expectedEventOutput.toString(), displayStudyPlanCommandFeedback.toString());
    }
}
