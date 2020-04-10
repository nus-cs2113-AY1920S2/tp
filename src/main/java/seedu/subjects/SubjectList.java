package seedu.subjects;

import seedu.events.Event;
import seedu.duke.UI;
import seedu.exception.EscException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubjectList {
    private ArrayList<Subject> subjects;
    private ArrayList<Event> events;
    UI ui = new UI();

    public SubjectList() {
        this.subjects = new ArrayList<Subject>();
        this.events = new ArrayList<Event>();
    }

    /**
     * Constructor for loading SubjectList.
     * @param returnObj Arraylist of subjects and exams.
     */
    public SubjectList(ArrayList returnObj) {
        this.subjects = (ArrayList<Subject>) returnObj.get(0);
        this.events = (ArrayList<Event>) returnObj.get(1);
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
    public void addSubject(Subject subject) throws EscException {
        if (checkSubjectDuplicate(subject) == true) {
            System.out.println("This subject already exists.");
            subjectDuplicateRemind(subject);
        } else {
            subjects.add(subject);
            System.out.println(subject.getSubject() + " has been added.");
            listSubjects(subjects);
        }
    }

    /**
     *Checks for duplicate subject.
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
     *Checks for duplicate subject and reminds user of existing subjects.
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

    /**
     * Adds an event to the deck.
     * @param event Event to be added.
     */
    public void addEvent(Event event) throws EscException {
        events.add(event);
        System.out.println(event.getTopic() + " has been added.");
        listUpcoming();
    }

    /**
     * Removes an event from the deck.
     * @param index Index of event to be removed.
     * @throws EscException if list of events is empty or event item at index does not exit.
     */
    public void removeEvent(int index) throws EscException {
        if (this.events.size() == 0) {
            throw new EscException("The event list is empty.");
        }
        try {
            System.out.println(events.get(index).getTopic() + " has been removed.");
            events.remove(index);
            listUpcoming();
        } catch (IndexOutOfBoundsException e) {
            throw new EscException("The event item does not exist.");
        }
    }

    /**
     * Lists all upcoming events.
     * @param dateRange Date range of upcoming events to show.
     */
    public void listUpcoming(int... dateRange) {
        if (this.events.size() == 0) {
            System.out.println("No stored events.");
        }
        ArrayList<Event> listToShow;
        if (dateRange.length == 0) {
            System.out.println("Here is the list of events.");
            listToShow = this.events;
        } else {
            System.out.println("Here is the list of upcoming events within " + dateRange[0] + " day(s).");
            listToShow = findUpcoming(dateRange[0]);
        }

        if (listToShow.isEmpty()) {
            System.out.println("No upcoming event within " + dateRange[0] + " day(s).");
        } else {
            for (int i = 0; i < listToShow.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + listToShow.get(i).toString());
            }
        }

    }

    /**
     * Finds upcoming events within the specified date range.
     * @param dateRange Date range to find upcoming events.
     * @return Upcoming events within the date range.
     */
    public ArrayList<Event> findUpcoming(int dateRange) {
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Event event : events) {
            LocalDate date = event.getDate();
            long period = today.until(date, ChronoUnit.DAYS);
            if (period <= dateRange && period >= 0) {
                upcomingEvents.add(event);
            }
        }

        Collections.sort(upcomingEvents);
        return upcomingEvents;
    }

    public int size() {
        return this.subjects.size();
    }
}
