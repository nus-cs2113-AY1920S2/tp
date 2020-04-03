package seedu.subjects;

import seedu.events.Event;
import seedu.exception.EscException;

import java.util.ArrayList;

public class SubjectList {
    private ArrayList<Subject> subjects;
    private ArrayList<Event> events;

    public SubjectList() {
        this.subjects = new ArrayList<Subject>();
        this.events = new ArrayList<Event>();
    }

    public SubjectList(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        this.events = new ArrayList<Event>();
    }

    public SubjectList(ArrayList<Subject> subjects, ArrayList<Event> eventDates) {
        this.subjects = subjects;
        this.events = eventDates;
    }

    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
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
     * Adds an event to the deck.
     * @param event Event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an event from the deck.
     * @param index Index of event to be removed.
     */
    public void removeEvent(int index) throws EscException {
        if (this.events.size() == 0) {
            throw new EscException("The event list is empty.");
        }
        try {
            events.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The event item does not exist.");
        }
    }

    /**
     * Lists all upcoming events.
     * @param events List of upcoming events.
     */
    public static void listUpcoming(ArrayList<Event> events) {
        if (events.size() == 0) {
            System.out.println("No upcoming events.");
        } else {
            System.out.println("Here is the list of upcoming events.");
            for (int i = 0; i < events.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + events.get(i).toString());
            }
        }
    }

    public int size() {
        return this.subjects.size();
    }
}
