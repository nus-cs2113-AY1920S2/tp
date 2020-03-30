package seedu.exams;

import java.time.LocalDate;

public class Exam implements Comparable<Exam> {
    private String topic;
    private LocalDate examDate;

    public Exam(String topic, LocalDate examDate) {
        this.topic = topic;
        this.examDate = examDate;
    }

    public String getTopic() {
        return this.topic;
    }

    public LocalDate getDate() {
        return this.examDate;
    }

    /**
     * Custom comparable between two exams. Sorted by date.
     *
     * @param o Other exam to compare against.
     * @return Integer depicting result of comparison.
     */
    @Override
    public int compareTo(Exam o) {
        return this.examDate.compareTo(o.getDate());
    }

    public String toString() {
        return this.topic + ": "  + this.examDate.toString();
    }

}
