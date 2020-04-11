package seedu.tp.commands;

import seedu.tp.exceptions.InvalidDateFormatException;
import seedu.tp.exceptions.ReversedDateOrderException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.parser.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.tp.utils.Constants.BULLET_POINT;

/**
 * Command to show a timeline for the existing flashcards.
 */
public class TimelineCommand extends Command {

    private FlashcardList flashcardList;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for TimelineCommand.
     *
     * @param flashcardList list containing all flashcards
     */
    public TimelineCommand(FlashcardList flashcardList) {
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.flashcardList = flashcardList;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Constructor for TimelineCommand.
     *
     * @param flashcardList list containing all flashcards
     * @param startDate     the date to start listing flashcards from (inclusive)
     * @param endDate       the date after which to stop listing flashcards from
     */
    public TimelineCommand(FlashcardList flashcardList, String startDate, String endDate)
        throws InvalidDateFormatException, ReversedDateOrderException {
        assert flashcardList != null : "Invalid null FlashcardList!";
        assert startDate != null : "Invalid null startDate!";
        assert endDate != null : "Invalid null endDate!";

        this.startDate = Parser.parseDate(startDate);
        this.endDate = Parser.parseDate(endDate);
        if (startDate.compareTo(endDate) > 0) {
            throw new ReversedDateOrderException();
        }
        List<Flashcard> filteredFlashcardList = flashcardList.getFlashcards().stream()
            .filter(flashcard -> isValidFlashcard(flashcard)).collect(Collectors.toList());
        this.flashcardList = new FlashcardList(filteredFlashcardList);
    }

    private boolean isValidFlashcard(Flashcard f) {
        LocalDate cardStartDate;
        if (f instanceof EventFlashcard) {
            EventFlashcard eventFlashcard = (EventFlashcard) f;
            cardStartDate = eventFlashcard.getStartDate();
        } else if (f instanceof PersonFlashcard) {
            PersonFlashcard personFlashcard = (PersonFlashcard) f;
            cardStartDate = personFlashcard.getBirthDate();
        } else {
            return false;
        }

        return cardStartDate.compareTo(this.startDate) >= 0 && cardStartDate.compareTo(this.endDate) <= 0;
    }

    private String getFeedback(FlashcardList flashcardList) {
        boolean isRestricted = startDate != null && endDate != null;
        if (flashcardList.isEmpty()) {
            if (isRestricted) {
                return "You have no flashcards from " + startDate + " to " + endDate;
            } else {
                return "You have no flashcard at this moment!";
            }
        }

        List<Flashcard> flashcards = new ArrayList<>(flashcardList.getFlashcards());
        Collections.sort(flashcards);
        String summaryMessage = isRestricted ? "Listing flashcards from " + startDate + " to " + endDate + "..."
            : "Flashcards sorted by date:";
        StringBuilder feedback = new StringBuilder(summaryMessage);
        feedback.append(System.lineSeparator());
        for (Flashcard flashcard : flashcards) {
            feedback.append(BULLET_POINT + flashcard.getShortDescription());
            feedback.append(System.lineSeparator());
        }
        return feedback.toString().trim();
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Listing the flashcards in time order...");
        String feedback = getFeedback(flashcardList);
        return new CommandFeedback(feedback);
    }
}
