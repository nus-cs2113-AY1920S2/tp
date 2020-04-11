package seedu.tp.exceptions;

/**
 * Unrecognized flashcard type exception.
 */
public class UnrecognizedFlashcardTypeException extends HistoryFlashcardException {
    public UnrecognizedFlashcardTypeException(String errorMessage) {
        super(errorMessage);
    }
}
