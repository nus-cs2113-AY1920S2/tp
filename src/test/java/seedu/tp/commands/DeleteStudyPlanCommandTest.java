package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.HistoryFlashcardException;
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
import static seedu.tp.utils.ExampleInputConstants.LOCAL_DATE_1;
import static seedu.tp.utils.ExampleInputConstants.LOCAL_DATE_2;
import static seedu.tp.utils.ExampleInputConstants.SIMULATED_DELETE_STUDY_PLAN_INPUT;

public class DeleteStudyPlanCommandTest {

    private static final Map.Entry<LocalDate, List<Integer>> STUDY_PLAN_ENTRY_1 =
        new AbstractMap.SimpleEntry<>(LOCAL_DATE_1, Arrays.asList(0, 1));
    private static final Map.Entry<LocalDate, List<Integer>> STUDY_PLAN_ENTRY_2 =
        new AbstractMap.SimpleEntry<>(LOCAL_DATE_2, Arrays.asList(0, 2));

    private static final InputStream backupStdin = System.in;

    private StudyPlanList studyPlanList;
    private StudyPlanList emptyStudyPlanList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        emptyStudyPlanList = new StudyPlanList();
        List<Map.Entry<LocalDate, List<Integer>>> studyPlans = Arrays.asList(STUDY_PLAN_ENTRY_1, STUDY_PLAN_ENTRY_2);
        studyPlanList = new StudyPlanList(studyPlans);
    }

    @AfterEach
    public void restoreStdin() {
        System.setIn(backupStdin);
    }

    @Test
    public void deleteStudyPlanCommand_execute_success() throws HistoryFlashcardException {
        ByteArrayInputStream simulatedSystemIn =
            new ByteArrayInputStream(SIMULATED_DELETE_STUDY_PLAN_INPUT.getBytes());
        System.setIn(simulatedSystemIn);
        DeleteStudyPlanCommand deleteStudyPlanCommand = new DeleteStudyPlanCommand(new Ui(), studyPlanList);
        deleteStudyPlanCommand.execute();

        List<Map.Entry<LocalDate, List<Integer>>> expectedStudyPlanList = Collections.singletonList(STUDY_PLAN_ENTRY_2);
        StudyPlanList expectedStudyPlan = new StudyPlanList(expectedStudyPlanList);

        assertEquals(expectedStudyPlan, studyPlanList);
    }

    @Test
    public void deleteStudyPlanCommand_executeEmpty_success() throws HistoryFlashcardException {
        ByteArrayInputStream simulatedSystemIn =
            new ByteArrayInputStream(SIMULATED_DELETE_STUDY_PLAN_INPUT.getBytes());
        System.setIn(simulatedSystemIn);
        DeleteStudyPlanCommand deleteStudyPlanCommand = new DeleteStudyPlanCommand(new Ui(), emptyStudyPlanList);
        deleteStudyPlanCommand.execute();

        List<Map.Entry<LocalDate, List<Integer>>> expectedStudyPlanList = Collections.emptyList();
        StudyPlanList expectedStudyPlan = new StudyPlanList(expectedStudyPlanList);

        assertEquals(expectedStudyPlan, emptyStudyPlanList);
    }
}
