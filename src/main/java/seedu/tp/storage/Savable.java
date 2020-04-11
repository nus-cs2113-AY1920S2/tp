package seedu.tp.storage;

/**
 * Interface for savable objects.
 */
public interface Savable {
    /**
     * Get the file name to save the object to. In the case of flashcards and groups will be title.
     *
     * @return the file name
     */
    String getFileName();
}
