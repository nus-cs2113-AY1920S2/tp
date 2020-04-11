package seedu.tp.commands;

import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.studyplan.StudyPlanList;
import seedu.tp.ui.Ui;

public class UpdateStudyPlanCommand extends ModifyingCommand {

    private Ui ui;
    private StudyPlanList studyPlanList;
    private FlashcardList flashcardList;

    /**
     * Constructor for UpdateStudyPlanCommand.
     *
     * @param ui            the UI class to be used in the command
     * @param studyPlanList the StudyPlanList to be updated
     * @param flashcardList the FlashcardList to be used in the command
     */
    public UpdateStudyPlanCommand(Ui ui, StudyPlanList studyPlanList, FlashcardList flashcardList) {
        assert ui != null : "Invalid null Ui!";
        assert studyPlanList != null : "Invalid null StudyPlan!";
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.ui = ui;
        this.studyPlanList = studyPlanList;
        this.flashcardList = flashcardList;
    }

    @Override
    public CommandFeedback execute() throws HistoryFlashcardException {
        LOGGER.info("Executing UpdateStudyPlanCommand...");
        studyPlanList.updateStudyPlan(ui, flashcardList);
        LOGGER.info("UpdateStudyPlanCommand executed!");
        return save(studyPlanList);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UpdateStudyPlanCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        UpdateStudyPlanCommand otherUpdateStudyPlanCommand = (UpdateStudyPlanCommand) obj;
        return this.ui.equals(otherUpdateStudyPlanCommand.ui)
            & this.studyPlanList.equals(otherUpdateStudyPlanCommand.studyPlanList)
            & this.flashcardList.equals(otherUpdateStudyPlanCommand.flashcardList);
    }
}
