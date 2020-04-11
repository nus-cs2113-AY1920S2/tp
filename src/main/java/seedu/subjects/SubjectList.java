package seedu.subjects;

import seedu.events.Event;
import seedu.events.EventList;
import seedu.exception.EscException;

import java.util.ArrayList;


public class SubjectList {
    private ArrayList<Subject> subjects;
    private EventList events;

    public SubjectList() {
        this.subjects = new ArrayList<Subject>();
        this.events = new EventList();
    }

    /**
     * Constructor for loading SubjectList from file.
     * @param returnObj ArrayList of 2 ArrayLists; subjects (0) and events (1).
     */
    @SuppressWarnings("unchecked")
    public SubjectList(ArrayList returnObj) {
        this.subjects = (ArrayList<Subject>) returnObj.get(0);
        this.events = new EventList((ArrayList<Event>) returnObj.get(1));
    }

    public SubjectList(ArrayList<Subject> subjects, EventList events) {
        this.subjects = subjects;
        this.events = events;
    }

    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    public EventList getEventList() {
        return this.events;
    }

    /**
     * Adds a subject to the deck.
     * @param subject Subject to be added.
     */
    public void addSubject(Subject subject) throws EscException {
        if (checkSubjectDuplicate(subject) == true) {
            System.out.println("This subject already exists.");
            subjectDuplicateRemind(subject);
        } else {
            subjects.add(subject);
            System.out.println(subject.getSubject() + " has been added.\n");
            listSubjects(subjects);
        }
    }

    /**
     * Checks for duplicate subject.
     * @param subject Subject to be checked.
     */
    public boolean checkSubjectDuplicate(Subject subject) {
        String name = subject.getSubject().replace(" ","");
        for (Subject oldSubject : subjects) {
            if (name.toLowerCase().equals(oldSubject.getSubject().replace(" ","").toLowerCase())) {
                return true;
            }
        }
        return  false;
    }

    /**
     * Checks for duplicate subject and reminds user of existing subjects.
     * @param subject Subject to be checked.
     */
    public void subjectDuplicateRemind(Subject subject) {
        String name = subject.getSubject().replace(" ","");
        for (Subject oldSubject : subjects) {
            if (name.toLowerCase().equals(oldSubject.getSubject().replace(" ","").toLowerCase())) {
                System.out.println("Do you mean {" + oldSubject.getSubject() + "}");
            }
        }
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
            System.out.println(subjects.get(index).getSubject() + " has been deleted");
            subjects.remove(index);
            listSubjects(subjects);
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

    public int size() {
        return this.subjects.size();
    }
}
