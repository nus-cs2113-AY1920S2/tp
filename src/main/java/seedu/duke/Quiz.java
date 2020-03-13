package seedu.duke;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;
import seedu.duke.UI;

import java.util.Random;

public class Quiz {

    /**
     * Generate a random number with a set upper limit.
     * @param upperRange Upper limit of random number.
     * @return Random number generated.
     */
    public static int generateRandomInt(int upperRange) throws EscException {
        if (upperRange < 1) {
            throw new EscException("The card list is empty.");
        }
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    /**
     * Retrieves a random card from the card list.
     * @param cardlist Card list where card is taken from.
     * @return retrievedCard
     */
    public static Card retrieveCard(CardList cardlist) throws EscException {
        int size = cardlist.size();
        Card retrievedCard;
        try {
            int randomInt = generateRandomInt(size);
            retrievedCard = cardlist.getCard(randomInt + 1);
        } catch(EscException e) {
            throw e;
        }
        return retrievedCard;
    }

    /**
     * Outputs a random question, and outputs its answer after the user has entered an answer.
     * @param cardlist Card list where card is taken from.
     */
    public static void quizQuestion(CardList cardlist) {
        UI ui = new UI();
        try {
            Card questionCard = retrieveCard(cardlist);
            String question = questionCard.getQuestion();
            String answer = questionCard.getAnswer();
            System.out.println("Question: " + question);
            ui.readAnswer();
            System.out.println("Answer: " + answer);
        } catch (EscException e) {
            System.out.println(e.getMessage());
        }
    }
}
