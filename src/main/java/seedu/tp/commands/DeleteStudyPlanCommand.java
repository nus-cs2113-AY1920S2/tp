package seedu.tp.commands;

import seedu.tp.studyplan.StudyPlanList;
import seedu.tp.ui.Ui;

public class DeleteStudyPlanCommand extends ModifyingCommand {

    private Ui ui;
    private StudyPlanList studyPlanList;

    /**
     * Constructor for UpdateStudyPlanCommand.
     *
     * @param ui            the UI class to be used in the command
     * @param studyPlanList the StudyPlanList to be updated
     */
    public DeleteStudyPlanCommand(Ui ui, StudyPlanList studyPlanList) {
        assert ui != null : " Invalid null Ui!";
        assert studyPlanList != null : "Invalid null StudyPlan!";

        this.ui = ui;
        this.studyPlanList = studyPlanList;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing DeleteStudyPlanCommand...");
        studyPlanList.deleteStudyPlan(ui);
        LOGGER.info("DeleteStudyPlanCommand executed!");
        return save(studyPlanList);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeleteStudyPlanCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        DeleteStudyPlanCommand otherDeleteStudyPlanCommand = (DeleteStudyPlanCommand) obj;
        return this.ui.equals(otherDeleteStudyPlanCommand.ui)
            & this.studyPlanList.equals(otherDeleteStudyPlanCommand.studyPlanList);
    }
}
