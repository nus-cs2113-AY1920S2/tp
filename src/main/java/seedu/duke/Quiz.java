package seedu.duke;

import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;
import seedu.score.ScoreList;
import seedu.subjects.Subject;

import java.util.HashSet;
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
            retrievedCard = cardlist.getCard(randomInt);
        } catch (EscException e) {
            throw e;
        }
        return retrievedCard;
    }

    /**
     * Outputs a random question, and outputs its answer after the user has entered an answer.
     * @param subject Subject to be tested.
     * @throws EscException when chosen subject has no questions.
     */
    public static void quizQuestion(Subject subject) throws EscException {
        HashSet<Card> set = new HashSet<>();
        CardList cards = subject.getCardList();
        if (cards.size() == 0) {
            throw new EscException("No questions for this subject.");
        }
        ScoreList scores = subject.getScoreList();
        int max = cards.size();
        int attempted = 0;
        double score = 0;
        while (attempted < max) {
            try {
                score += quizNext(cards, set);
                attempted++;
            } catch (EscException e) {
                System.out.println(e.getMessage());
            }
        }
        double percentage = score / attempted;
        double percentageScore = (double) Math.round(percentage * 10000) / 100;
        scores.add(percentageScore);
        System.out.println("Quiz Finished!");
        System.out.println("You Scored: " + percentageScore + "%");
    }

    /**
     * Outputs a random question that hasn't been quizzed.
     * @param cards Stack of cards to quiz from.
     * @param set Set of cards that has already been quizzed.
     * @return Score obtained from question.
     * @throws EscException when input format is incorrect.
     */
    public static double quizNext(CardList cards, HashSet<Card> set) throws EscException {
        double score;
        Card questionCard = retrieveCard(cards);
        while (set.contains(questionCard)) {
            questionCard = retrieveCard(cards);
        }
        set.add(questionCard);
        UI ui = new UI();
        ui.showLine();
        String question = questionCard.getQuestion();
        System.out.println("Question: " + question);
        ui.readAnswer();
        String answer = questionCard.getAnswer();
        System.out.println("Your Answer: " + answer);
        String check = ui.checkCorrectness();
        if (check.equals("Y")) {
            score = 1.0;
            System.out.println("Answer marked as correct.");
        } else if (check.equals("N")) {
            score = 0;
            System.out.println("Answer marked as incorrect.");
        } else {
            throw new EscException("Wrong input format.");
        }
        return score;
    }

}
