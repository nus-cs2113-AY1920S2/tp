package seedu.tp.flashcard;

import seedu.tp.parser.Parser;
import seedu.tp.ui.Ui;

import java.time.LocalDate;
import java.util.List;

import static seedu.tp.utils.Constants.DIVIDER;
import static seedu.tp.utils.Constants.END_DATE_FIELD;
import static seedu.tp.utils.Constants.NAME_FIELD;
import static seedu.tp.utils.Constants.START_DATE_FIELD;
import static seedu.tp.utils.Constants.SUMMARY_FIELD;

/**
 * Event flashcard.
 */
public class EventFlashcard extends Flashcard {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an <code>EventFlashcard</code>.
     */
    public EventFlashcard(String name, LocalDate startDate, LocalDate endDate, String summary, List<String> details) {
        super(name, summary, details);
        assert !name.isEmpty() : "Invalid empty name!";
        assert startDate != null : "Invalid null startDate!";
        assert endDate != null : "Invalid null endDate!";
        assert !summary.isEmpty() : "Invalid empty summary!";

        this.startDate = startDate;
        this.endDate = endDate;
        LOGGER.info("Constructed new EventFlashcard: " + this);
    }

    /**
     * Creates an <code>EventFlashcard</code>  by prompting the user to enter info.
     *
     * @param ui used to prompt the user
     * @return the created <code>EventFlashcard</code>
     */
    public static EventFlashcard createEventFlashcard(Ui ui) {
        String name = ui.promptUserForRequiredField(NAME_FIELD);
        LocalDate startDate = ui.promptUserForRequiredLocalDate(START_DATE_FIELD);
        LocalDate endDate = ui.promptUserForRequiredLocalDate(END_DATE_FIELD);
        while (startDate.compareTo(endDate) > 0) {
            ui.sendReversedDateOrderResponse();
            startDate = ui.promptUserForRequiredLocalDate(START_DATE_FIELD);
            endDate = ui.promptUserForRequiredLocalDate(END_DATE_FIELD);
        }
        String summary = ui.promptUserForRequiredField(SUMMARY_FIELD);
        List<String> details = ui.promptUserForDetails();
        return new EventFlashcard(name, startDate, endDate, summary, details);
    }

    /**
     * Gets the start date of this event flashcard.
     *
     * @return the start date of this event flashcard
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of this event flashcard.
     *
     * @return the end date of this event flashcard
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets a short description of the flashcard, without summary or details.
     *
     * @return String of shortDescription of the flashcard
     */
    @Override
    public String getShortDescription() {
        String shortDescription = this.name + DIVIDER + "Time Period: " + this.startDate + " to " + this.endDate
            + DIVIDER + "Reviewed: " + this.getReviewIcon() + DIVIDER + "Priority: " + this.getPriorityAsString();
        return shortDescription;
    }

    /**
     * Gets the string representation of event flashcard.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Event name: ").append(name).append(System.lineSeparator());
        stringBuilder.append("Event period: ").append(Parser.localDateToString(startDate)).append("-")
            .append(Parser.localDateToString(endDate)).append(System.lineSeparator());
        stringBuilder.append("Summary: ").append(summary).append(System.lineSeparator());
        stringBuilder.append("Details:").append(System.lineSeparator());
        stringBuilder.append(getDetailsString(details));
        return stringBuilder.toString();
    }

    /**
     * Checks if the current instance is equal to the object passed in.
     *
     * @param obj The object to be compared against the current instance
     * @return whether or not the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventFlashcard)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        // Will have to make sure in the future to check for null here if we make other optional fields.
        EventFlashcard otherEventFlashcard = (EventFlashcard) obj;
        return super.equals(obj) && startDate.equals(otherEventFlashcard.getStartDate())
            && endDate.equals(otherEventFlashcard.getEndDate());
    }

    /**
     * Compares the current instance with the flashcard passed in.
     *
     * @param flashcard the flashcard to be compared with
     * @return the comparison result
     */
    @Override
    public int compareTo(Flashcard flashcard) {
        if (flashcard instanceof EventFlashcard) {
            EventFlashcard eventFlashcard = (EventFlashcard) flashcard;
            return startDate.compareTo(eventFlashcard.getStartDate());
        } else if (flashcard instanceof PersonFlashcard) {
            PersonFlashcard personFlashcard = (PersonFlashcard) flashcard;
            return startDate.compareTo(personFlashcard.getBirthDate());
        } else {
            return -1;
        }
    }
}
