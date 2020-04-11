package seedu.tp.flashcard;

import seedu.tp.parser.Parser;
import seedu.tp.ui.Ui;

import java.time.LocalDate;
import java.util.List;

import static seedu.tp.utils.Constants.BIRTH_DATE_FIELD;
import static seedu.tp.utils.Constants.DEATH_DATE_FIELD;
import static seedu.tp.utils.Constants.DIVIDER;
import static seedu.tp.utils.Constants.NAME_FIELD;
import static seedu.tp.utils.Constants.SUMMARY_FIELD;

/**
 * Person flashcard.
 */
public class PersonFlashcard extends Flashcard {
    private LocalDate birthDate;
    private LocalDate deathDate;

    /**
     * Constructs a <code>PersonFlashcard</code>.
     */
    public PersonFlashcard(String name, LocalDate birthDate, LocalDate deathDate, String summary,
                           List<String> details) {
        super(name, summary, details);
        assert !name.isEmpty() : "Invalid empty name!";
        assert birthDate != null : "Invalid null birthDate!";
        assert deathDate != null : "Invalid null deathDate!";
        assert !summary.isEmpty() : "Invalid empty summary!";

        this.birthDate = birthDate;
        this.deathDate = deathDate;
        LOGGER.info("Constructed new PersonFlashcard: " + this);
    }

    /**
     * Creates a <code>PersonFlashcard</code> by prompting the user to enter info.
     *
     * @param ui used to prompt the user
     * @return the created <code>PersonFlashcard</code>
     */
    public static PersonFlashcard createPersonFlashcard(Ui ui) {
        String name = ui.promptUserForRequiredField(NAME_FIELD);
        LocalDate birthDate = ui.promptUserForRequiredLocalDate(BIRTH_DATE_FIELD);
        LocalDate deathDate = ui.promptUserForRequiredLocalDate(DEATH_DATE_FIELD);
        while (birthDate.compareTo(deathDate) > 0) {
            ui.sendReversedDateOrderResponse();
            birthDate = ui.promptUserForRequiredLocalDate(BIRTH_DATE_FIELD);
            deathDate = ui.promptUserForRequiredLocalDate(DEATH_DATE_FIELD);
        }
        String summary = ui.promptUserForRequiredField(SUMMARY_FIELD);
        List<String> details = ui.promptUserForDetails();
        return new PersonFlashcard(name, birthDate, deathDate, summary, details);
    }

    /**
     * Get the birth date of this person flashcard.
     *
     * @return the birth date of this person flashcard
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Get the death date of this person flashcard.
     *
     * @return the death date of this person flashcard
     */
    public LocalDate getDeathDate() {
        return deathDate;
    }

    /**
     * Gets the string representation of this flashcard.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Person name: ").append(name).append(System.lineSeparator());
        stringBuilder.append("Born: ").append(Parser.localDateToString(birthDate)).append(System.lineSeparator());
        stringBuilder.append("Died: ").append(Parser.localDateToString(deathDate)).append(System.lineSeparator());
        stringBuilder.append("Summary: ").append(summary).append(System.lineSeparator());
        stringBuilder.append("Details:").append(System.lineSeparator());
        stringBuilder.append(getDetailsString(details));
        return stringBuilder.toString();
    }

    /**
     * Gets a short description of the flashcard, without summary or details.
     *
     * @return String of shortDescription of the flashcard
     */
    @Override
    public String getShortDescription() {
        String shortDescription = this.name + DIVIDER + "Time Period: " + this.birthDate + " to " + this.deathDate
            + DIVIDER + "Reviewed: " + this.getReviewIcon() + DIVIDER + "Priority: " + this.getPriorityAsString();
        return shortDescription;
    }

    /**
     * Check if the current instance is equal to the object passed in.
     *
     * @param obj The object to be compared against the current instance
     * @return whether or not the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PersonFlashcard)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        // Will have to make sure in the future to check for null here if we make other optional fields.
        PersonFlashcard otherEventFlashcard = (PersonFlashcard) obj;
        return super.equals(obj) && birthDate.equals(otherEventFlashcard.getBirthDate())
            && deathDate.equals(otherEventFlashcard.getDeathDate());
    }

    @Override
    public int compareTo(Flashcard flashcard) {
        if (flashcard instanceof EventFlashcard) {
            EventFlashcard eventFlashcard = (EventFlashcard) flashcard;
            return birthDate.compareTo(eventFlashcard.getStartDate());
        } else if (flashcard instanceof PersonFlashcard) {
            PersonFlashcard personFlashcard = (PersonFlashcard) flashcard;
            return birthDate.compareTo(personFlashcard.getBirthDate());
        } else {
            return -1;
        }
    }
}
