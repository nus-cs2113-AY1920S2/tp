package seedu.score;

import seedu.exception.EscException;

import java.util.ArrayList;

/**
 * Contains the scores for all quizzes taken.
 */
public class ScoreList {
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
        return avgScore;
    }

    /**
     * Prints out all the past score history for selected subject.
     */
    public void listScores() throws EscException {
        if (scores.isEmpty()) {
            throw new EscException("No test history yet.");
        }
        System.out.println("Past test history:");
        String msg;
        int counter = 1;
        for (double score : scores) {
            System.out.println(counter + ") " + score);
            counter++;
        }
        System.out.println("Average Score: " + getAvg());
    }

}
