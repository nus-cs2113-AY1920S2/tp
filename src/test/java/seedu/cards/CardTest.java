package seedu.cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    private Card flashcard1;
    private Card flashcard2;

    @BeforeEach
    public void setUp() {
        flashcard1 = new Card("What does HTTP stand for?", "HyperText Transfer Protocol");
        flashcard2 = new Card("What does HTML stand for?", "Hypertext Markup Language");
    }

    @Test
    public void getQuestion() {
        assertTrue(flashcard1.getQuestion().equals("What does HTTP stand for?"));
        assertTrue(flashcard2.getQuestion().equals("What does HTML stand for?"));
    }

    @Test
    public void getAnswer() {
        assertTrue(flashcard1.getAnswer().equals("HyperText Transfer Protocol"));
        assertTrue(flashcard2.getAnswer().equals("HyperText Markup Language"));
    }

}
