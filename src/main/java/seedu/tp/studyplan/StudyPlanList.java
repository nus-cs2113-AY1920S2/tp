package seedu.tp.studyplan;

import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.storage.Savable;
import seedu.tp.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.DATE_FIELD;
import static seedu.tp.utils.Constants.EMPTY_SPACE;
import static seedu.tp.utils.Constants.INDEXES_FIELD;
import static seedu.tp.utils.Constants.LOG_FOLDER;

public class StudyPlanList implements Savable {

    public static final String STUDY_PLAN_LIST_FOLDER = "studyplans";
    public static final String STUDY_PLAN_LIST_FILE_NAME = "studyplanlist";

    protected static final Logger LOGGER = Logger.getLogger(StudyPlanList.class.getName());

    private static final String FILE_PATH = LOG_FOLDER + "study_plan_list.log";
    private TreeMap<LocalDate, List<Integer>> studyPlanList;

    /**
     * Constructor for StudyPlanList.
     */
    public StudyPlanList() {
        this.studyPlanList = new TreeMap<>();
    }

    /**
     * Constructor for StudyPlanList.
     *
     * @param listOfStudyPlans the list of daily study plans to be added
     */
    public StudyPlanList(List<Map.Entry<LocalDate, List<Integer>>> listOfStudyPlans) {
        this();
        assert listOfStudyPlans != null : "Invalid null study plan list!";

        for (Map.Entry<LocalDate, List<Integer>> entry : listOfStudyPlans) {
            this.studyPlanList.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Constructor for StudyPlanList.
     *
     * @param studyPlanListTreeMap the TreeMap to construct the StudyPlanList from
     */
    public StudyPlanList(TreeMap<LocalDate, List<Integer>> studyPlanListTreeMap) {
        this();
        assert studyPlanListTreeMap != null : "Invalid null study plan list tree map!";
        this.studyPlanList = studyPlanListTreeMap;
    }

    /**
     * Set up the StudyPlanList logger. Call once at the start of the program.
     *
     * @throws IOException when logger set up failed
     */
    public static void setupLogger() throws IOException {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(FILE_PATH, true);
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }

    /**
     * Updates the study plan list.
     *
     * @param ui            the Ui class to be used for interaction with user
     * @param flashcardList the flashcard list used for checking index boundary
     * @throws InvalidFlashcardIndexException exception thrown when user input invalid indexes
     */
    public void updateStudyPlan(Ui ui, FlashcardList flashcardList) throws InvalidFlashcardIndexException {
        assert ui != null : "Invalid null Ui!";
        assert flashcardList != null : "Invalid null FlashcardList!";

        LOGGER.info("Updating study plan...");
        LocalDate date = ui.promptUserForRequiredLocalDate(DATE_FIELD);
        String[] indexesStr = ui.promptUserForRequiredField(INDEXES_FIELD).split(EMPTY_SPACE);
        List<Integer> indexes = new ArrayList<>();
        try {
            for (String dateStr : indexesStr) {
                int index = Integer.parseInt(dateStr) - 1;
                if (index < 0 | index >= flashcardList.getTotalFlashcardNum()) {
                    throw new IndexOutOfBoundsException();
                }
                indexes.add(index);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LOGGER.info("Throwing InvalidFlashcardIndexException in updateStudyPlan()...");
            throw new InvalidFlashcardIndexException();
        }
        studyPlanList.put(date, indexes);
        ui.confirmStudyPlanUpdate();
        LOGGER.info("Study plan updated!");
    }

    /**
     * Deletes a study plan from the study plan list.
     *
     * @param ui the Ui class to be used for interaction with user
     */
    public void deleteStudyPlan(Ui ui) {
        assert ui != null : "Invalid null Ui!";

        LOGGER.info("Deleting study plan...");
        LocalDate date = ui.promptUserForRequiredLocalDate(DATE_FIELD);
        if (studyPlanList.remove(date) != null) {
            ui.confirmStudyPlanDeletion(date);
            LOGGER.info("Study plan for " + date + " deleted!");
        } else {
            ui.sendStudyPlanDeletionFailedMessage(date);
            LOGGER.info("Study plan for " + date + " does not exist!");
        }
    }

    /**
     * Gets the list of study plans.
     *
     * @return a list containing all the study plans
     */
    public List<Map.Entry<LocalDate, List<Integer>>> getStudyPlanList() {
        LOGGER.info("Getting study plan list...");
        return new ArrayList<>(studyPlanList.entrySet());
    }

    /**
     * Gets the TreeMap from StudyPlanList.
     *
     * @return a TreeMap containing all the study plans
     */
    public TreeMap<LocalDate, List<Integer>> getTreeMap() {
        return this.studyPlanList;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StudyPlanList)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        StudyPlanList otherStudyPlan = (StudyPlanList) obj;
        List<Map.Entry<LocalDate, List<Integer>>> otherStudyPlanList = otherStudyPlan.getStudyPlanList();
        List<Map.Entry<LocalDate, List<Integer>>> studyPlanList = this.getStudyPlanList();
        if (otherStudyPlanList.size() != studyPlanList.size()) {
            return false;
        }
        for (int i = 0; i < studyPlanList.size(); i++) {
            Map.Entry<LocalDate, List<Integer>> entry = studyPlanList.get(i);
            Map.Entry<LocalDate, List<Integer>> otherEntry = otherStudyPlanList.get(i);

            if (!entry.getKey().equals(otherEntry.getKey())) {
                return false;
            }
            if (!entry.getValue().equals(otherEntry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the file name of the study plan lit.
     *
     * @return the file name of the study plan list.
     */
    @Override
    public String getFileName() {
        return STUDY_PLAN_LIST_FOLDER + "/" + STUDY_PLAN_LIST_FILE_NAME;
    }
}
