package seedu.score;

import seedu.exception.EscException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains the scores for all quizzes taken.
 */
public class ScoreList implements Serializable {
    private ArrayList<Double> scores = new ArrayList<>();

    /**
     * Adds a score to the ScoreList.
     * @param score Score to be added.
     */
    public void add(double score) {
        this.scores.add(score);
    }

    /**
     * Returns the average of all stored scores.
     * @return avgScore Average of all scores.
     */
    public double getAvg() {
        double totalScore = 0;
        for (double score : scores) {
            totalScore += score;
        }
        double avgScore = totalScore / scores.size();
        double roundedAvg = (double) Math.round(avgScore * 100) / 100;
        return roundedAvg;
    }

    /**
     * Prints out all the past score history for selected subject.
     * @throws EscException if there is not past test history for this subject.
     */
    public void listScores() throws EscException {
        if (scores.isEmpty()) {
            throw new EscException("No test history yet.");
        }
        System.out.println("Past test history:");
        String msg;
        int counter = 1;
        for (double score : scores) {

            // To check that all scores are less than or equal to 100%
            assert score <= 100.0 : "scores should be less than or equal to 100%";

            System.out.println(counter + ") " + score + "%");
            counter++;
        }
        System.out.println("Average Score: " + getAvg() + "%");
    }

}
