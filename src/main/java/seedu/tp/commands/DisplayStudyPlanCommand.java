package seedu.tp.commands;

import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.studyplan.StudyPlanList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DisplayStudyPlanCommand extends Command {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private StudyPlanList studyPlanList;
    private FlashcardList flashcardList;

    /**
     * Constructor for DisplayStudyPlanCommand.
     *
     * @param studyPlanList the study plan to be displayed
     * @param flashcardList the flashcard list to be used by the command
     */
    public DisplayStudyPlanCommand(StudyPlanList studyPlanList, FlashcardList flashcardList) {
        assert studyPlanList != null : "Invalid null StudyPlan!";
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.studyPlanList = studyPlanList;
        this.flashcardList = flashcardList;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing DisplayStudyPlanCommand...");

        StringBuilder feedback = new StringBuilder();
        List<Map.Entry<LocalDate, List<Integer>>> studyPlans = studyPlanList.getStudyPlanList();
        if (studyPlans.isEmpty()) {
            LOGGER.info("Returning no study plan command feedback...");
            feedback.append("You have no study plan at this moment.");
            return new CommandFeedback(feedback.toString());
        }

        for (Map.Entry<LocalDate, List<Integer>> studyPlanForDay : studyPlans) {
            feedback.append("Date: ").append(studyPlanForDay.getKey()).append(LINE_SEPARATOR);
            for (int index : studyPlanForDay.getValue()) {
                try {
                    Flashcard flashcard = flashcardList.getFlashcardAtIdx(index);
                    feedback.append(index + 1).append(": ")
                        .append(flashcard.getShortDescription()).append(LINE_SEPARATOR);
                } catch (IndexOutOfBoundsException e) {
                    index++;
                    feedback.append("Flashcard with index ").append(index).append(" not found. ")
                        .append("Did you delete this flashcard?").append(LINE_SEPARATOR);
                }
            }
        }

        LOGGER.info("Returning display study plan command feedback...");
        return new CommandFeedback(feedback.toString().trim());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DisplayStudyPlanCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        DisplayStudyPlanCommand otherDisplayStudyPlanCommand = (DisplayStudyPlanCommand) obj;
        return this.studyPlanList.equals(otherDisplayStudyPlanCommand.studyPlanList)
            & this.flashcardList.equals(otherDisplayStudyPlanCommand.flashcardList);
    }
}
