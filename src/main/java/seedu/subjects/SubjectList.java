package seedu.subjects;

import seedu.exams.Exam;
import seedu.exception.EscException;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class SubjectList {
    private ArrayList<Subject> subjects;
    private ArrayList<Exam> exams;

    public SubjectList() {
        this.subjects = new ArrayList<Subject>();
        this.exams = new ArrayList<Exam>();
    }

    public SubjectList(ArrayList<Subject> subjects, ArrayList<Exam> examDates) {
        this.subjects = subjects;
        this.exams = examDates;
    }

    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    public ArrayList<Exam> getExamDates() {
        return this.exams;
    }

    /**
     * Adds a subject to the deck.
     * @param subject Subject to be added.
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    /**
     * Removes a subject from the deck.
     * @param index Index of subject to be removed.
     */
    public void removeSubject(int index) throws EscException {
        if (this.size() == 0) {
            throw new EscException("The subject list is empty.");
        }
        try {
            subjects.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The subject item does not exist.");
        }
    }

    /**
     * Returns a card based on its index number.
     * @param index Index of subject to retrieve.
     * @return subject Subject corresponding to index.
     */
    public Subject getSubject(int index) throws EscException {
        if (this.size() == 0) {
            throw new EscException("The subject list is empty.");
        }

        Subject subject;
        try {
            subject = subjects.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The subject item does not exist.");
        }
        return subject;
    }

    /**
     * Lists all the subjects in the list.
     * @param subjects A list of subjects to be displayed.
     */
    public static void listSubjects(ArrayList<Subject> subjects) {
        if (subjects.size() == 0) {
            System.out.println("You haven't added anything yet.");
        } else {
            System.out.println("Here is the list of subjects.");
            for (int i = 0; i < subjects.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + subjects.get(i).getSubject());
            }
        }
    }

    /**
     * Lists all upcoming events.
     * @param exams List of upcoming exams.
     */
    public static void listUpcoming(ArrayList<Exam> exams) {
        if (exams.size() == 0) {
            System.out.println("No upcoming events.");
        } else {
            System.out.println("Here is the list of upcoming events.");
            for (int i = 0; i < exams.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + exams.get(i).toString());
            }
        }
    }

    public int size() {
        return this.subjects.size();
    }
}
