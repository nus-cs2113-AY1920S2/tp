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
import seedu.tp.studyplan.StudyPlanList;
import seedu.tp.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.LOCAL_DATE_1;
import static seedu.tp.utils.ExampleInputConstants.LOCAL_DATE_2;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_UPDATE_STUDY_PLAN_INPUT_1;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_UPDATE_STUDY_PLAN_INPUT_2;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;

public class UpdateStudyPlanCommandTest {

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
        new AbstractMap.SimpleEntry<>(LOCAL_DATE_2, Arrays.asList(1));
    private static final InputStream backupStdin = System.in;
    private FlashcardList fullFlashcardList;
    private StudyPlanList studyPlanList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
        studyPlanList = new StudyPlanList();
    }

    @AfterEach
    public void restoreStdin() {
        System.setIn(backupStdin);
    }

    @Test
    public void updateStudyPlanCommand_executeFromEmpty_success() throws HistoryFlashcardException {
        ByteArrayInputStream simulatedSystemIn =
            new ByteArrayInputStream(SIMULATED_UPDATE_STUDY_PLAN_INPUT_1.getBytes());
        System.setIn(simulatedSystemIn);
        UpdateStudyPlanCommand updateStudyPlanCommand = new UpdateStudyPlanCommand(new Ui(), studyPlanList,
            fullFlashcardList);
        updateStudyPlanCommand.execute();

        List<Map.Entry<LocalDate, List<Integer>>> expectedStudyPlanList = Collections.singletonList(STUDY_PLAN_ENTRY_1);
        StudyPlanList expectedStudyPlan = new StudyPlanList(expectedStudyPlanList);

        assertEquals(expectedStudyPlan, studyPlanList);
    }

    @Test
    public void updateStudyPlanCommand_execute_success() throws HistoryFlashcardException {
        ByteArrayInputStream simulatedSystemIn =
            new ByteArrayInputStream(SIMULATED_UPDATE_STUDY_PLAN_INPUT_2.getBytes());
        System.setIn(simulatedSystemIn);
        studyPlanList = new StudyPlanList(Collections.singletonList(STUDY_PLAN_ENTRY_1));
        UpdateStudyPlanCommand updateStudyPlanCommand = new UpdateStudyPlanCommand(new Ui(), studyPlanList,
            fullFlashcardList);
        updateStudyPlanCommand.execute();

        List<Map.Entry<LocalDate, List<Integer>>> expectedStudyPlanList =
            Arrays.asList(STUDY_PLAN_ENTRY_1, STUDY_PLAN_ENTRY_2);
        StudyPlanList expectedStudyPlan = new StudyPlanList(expectedStudyPlanList);

        assertEquals(expectedStudyPlan, studyPlanList);
    }
}
