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
     * @throws EscException if card list of chosen subject is empty.
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
     * @throws EscException if card list of chosen subject is empty.
     */
    public static Card retrieveCard(CardList cardlist) throws EscException {
        int size = cardlist.size();
        Card retrievedCard;
        try {
            int randomInt = generateRandomInt(size);
            // To check that the randomInt is smaller than the given size.
            assert randomInt < size : "randomly generated index should be smaller than cardlist size";
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
    public static void quizQuestion(Subject subject, int numToQuiz) throws EscException {
        HashSet<Card> set = new HashSet<>();
        CardList cards = subject.getCardList();

        if (cards.size() == 0) {
            throw new EscException("No questions for this subject.");
        }
        int checkedNumToQuiz = checkNumber(numToQuiz, cards.size());
        // To check that checkedNumToQuiz is less than the stored number of questions.
        assert checkedNumToQuiz <= cards.size() : "number to quiz should be less than or equal to number of cards";
        int attempted = 0;
        double score = 0;
        while (attempted < checkedNumToQuiz) {
            try {
                double obtainedScore = quizNext(cards, set);
                if (obtainedScore == -1.0) {
                    // Means that user has input an exitquiz command.
                    break;
                }
                score += obtainedScore;
                attempted++;
            } catch (EscException e) {
                System.out.println(e.getMessage());
            }
        }
        double percentage = score / attempted;
        double percentageScore = (double) Math.round(percentage * 10000) / 100;
        ScoreList scores = subject.getScoreList();
        scores.add(percentageScore);
        System.out.println("Quiz Finished!");
        System.out.println("You Scored: " + percentageScore + "%");
    }

    /**
     * Ensures that the number of questions to quiz is less than or equal to the number of stored questions.
     * @param numToQuiz Number of questions that user requested to quiz.
     * @param cardListSize Number of questions stored in the selected subject.
     * @return Checked number to quiz.
     */
    public static int checkNumber(int numToQuiz, int cardListSize) {
        if (numToQuiz > cardListSize) {
            System.out.println("Insufficient stored questions for this subject.");
            System.out.println("Setting number of questions to all stored questions: "
                    + cardListSize + " question(s).");
            return cardListSize;
        } else if (numToQuiz == -1) {
            return cardListSize;
        } else {
            return numToQuiz;
        }
    }

    /**
     * Outputs a random question that hasn't been quizzed.
     * @param cards Stack of cards to quiz from.
     * @param set Set of cards that has already been quizzed.
     * @return Score obtained from question.
     * @throws EscException if card list of chosen subject is empty.
     */
    public static double quizNext(CardList cards, HashSet<Card> set) throws EscException {
        Card questionCard = retrieveCard(cards);
        while (set.contains(questionCard)) {
            questionCard = retrieveCard(cards);
        }
        set.add(questionCard);
        UI ui = new UI();
        ui.showLine();
        String question = questionCard.getQuestion();
        System.out.println("Question: " + question);
        String userAnswer = ui.readAnswer();
        if (userAnswer.equals("exitquiz")) {
            return -1.0;
        }
        // To check that quiz terminates once user inputs the termination command.
        assert !userAnswer.equals("exitquiz") : "method should have terminated already";

        String answer = questionCard.getAnswer();
        System.out.println("Correct Answer: " + answer);
        double score = markCorrectness();
        return score;
    }

    /**
     * Mark the correctness of the answer.
     * @return Score of the question.
     * @throws EscException if marking format is wrong.
     */
    public static double markCorrectness() throws EscException {
        UI ui = new UI();
        double score;
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
