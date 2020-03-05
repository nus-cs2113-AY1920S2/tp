package seedu.duke;

import seedu.cards.Card;
import seedu.cards.CardList;

import java.util.ArrayList;
import java.util.Random;

public class Quiz {

    /**
     * Generate a random number with a set upper limit.
     * @param upperRange Upper limit of random number.
     * @return Random number generated.
     */
    public static int generateRandomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    /**
     * Retrieves a random card from the card list.
     * @param cardlist Card list where card is taken from.
     * @return retrievedCard
     */
    public static Card retrieveCard(CardList cardlist) {
        int size = cardlist.size();
        int randomInt = generateRandomInt(size);
        Card retrievedCard = cardlist.getCard(randomInt);
        return retrievedCard;
    }

    /**
     * Outputs a random question, and outputs its answer after the user has entered an answer.
     * @param cardlist Card list where card is taken from.
     */
    public static void quizQuestion(CardList cardlist) {
        Card questionCard = retrieveCard(cardlist);
        String question = questionCard.getQuestion();
        String answer = questionCard.getAnswer();
        // To be printed out using ui
        // ui.println("Question: " + question);
        // ui.getUserInput
        // ui.println("Answer: " + answer);
    }
}
