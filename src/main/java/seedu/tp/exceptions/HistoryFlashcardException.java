package seedu.tp.exceptions;

/**
 * Exceptions specific to the History Flashcard app.
 */
public abstract class HistoryFlashcardException extends Exception {
    public HistoryFlashcardException(String errorMessage) {
        super(errorMessage);
    }

    public HistoryFlashcardException() {
        super();
    }
}
