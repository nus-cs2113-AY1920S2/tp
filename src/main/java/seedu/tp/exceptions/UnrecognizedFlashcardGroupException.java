package seedu.tp.exceptions;

/**
 * Unrecognized flashcard group Exception.
 */
public class UnrecognizedFlashcardGroupException extends HistoryFlashcardException {
    public UnrecognizedFlashcardGroupException(String errorMessage) {
        super(errorMessage);
    }
}
