package seedu.subjects;

import java.io.Serializable;

/**
 * Flashcard object used to store a question and its answer.
 */
public class Subject implements Serializable {
    private final String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.name;
    }
}
