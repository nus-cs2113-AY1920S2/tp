package seedu.tp.flashcard;

import seedu.tp.ui.Ui;

import java.util.List;

import static seedu.tp.utils.Constants.DIVIDER;
import static seedu.tp.utils.Constants.NAME_FIELD;
import static seedu.tp.utils.Constants.SUMMARY_FIELD;

/**
 * Other flashcard.
 */
public class OtherFlashcard extends Flashcard {

    /**
     * Constructs an <code>OtherFlashcard</code>.
     */
    public OtherFlashcard(String name, String summary, List<String> details) {
        super(name, summary, details);
        assert !name.isEmpty() : "Invalid empty name!";
        assert !summary.isEmpty() : "Invalid empty summary!";

        LOGGER.info("Constructed new OtherFlashcard: " + this);
    }

    /**
     * Creates an <code>OtherFlashcard</code> by prompting the user to enter info.
     *
     * @param ui used to prompt the user
     * @return the created <code>OtherFlashcard</code>
     */
    public static OtherFlashcard createOtherFlashcard(Ui ui) {
        String name = ui.promptUserForRequiredField(NAME_FIELD);
        String summary = ui.promptUserForRequiredField(SUMMARY_FIELD);
        List<String> details = ui.promptUserForDetails();
        return new OtherFlashcard(name, summary, details);
    }

    /**
     * Gets a short description of the flashcard, without summary or details.
     *
     * @return String of shortDescription of the flashcard
     */
    @Override
    public String getShortDescription() {
        String shortDescription =
            this.name + DIVIDER + "Reviewed: " + this.getReviewIcon()
                + DIVIDER + "Priority: " + this.getPriorityAsString();
        return shortDescription;
    }

    /**
     * Gets the string representation of this flashcard.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Title: ").append(name).append(System.lineSeparator());
        stringBuilder.append("Summary: ").append(summary).append(System.lineSeparator());
        stringBuilder.append("Details:").append(System.lineSeparator());
        stringBuilder.append(getDetailsString(details));
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Flashcard flashcard) {
        if (flashcard instanceof OtherFlashcard) {
            return 0;
        } else {
            return 1;
        }
    }
}
