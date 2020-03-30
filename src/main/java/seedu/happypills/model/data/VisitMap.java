package seedu.happypills.model.data;

import seedu.happypills.model.exception.HappyPillsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisitMap {
    private final Map<String, ArrayList<Visit>> argMultimap = new HashMap<String, ArrayList<Visit>>();

    /**
     * Adds an Visit into the VisitMap.
     *
     * @param visit the appointment to be added into the map.
     * @throws HappyPillsException throws exception if there is already an appointment with the given datetime.
     */
    public void addVisit(Visit visit) throws HappyPillsException {
        ArrayList<Visit> visits = argMultimap.get(visit.getNric());

        // if list does not exist create it
        if (visits == null) {
            visits = new ArrayList<Visit>();
            visits.add(visit);
            argMultimap.put(visit.getNric(), visits);
        } else {
            // add if item is not already in list
            if (!visits.contains(visit.nric)) {
                visits.add(visit);
                argMultimap.put(visit.getNric(), visits);
            } else {
                throw new HappyPillsException("    The visit is already in the database.\n"
                        + "    Please try again.");
            }
        }
    }

    public Iterable<? extends Map.Entry> entrySet() {
        return argMultimap.entrySet();
    }

    public int size() {
        return argMultimap.size();
    }

    public boolean isEmpty() {
        return argMultimap.isEmpty();
    }

    public ArrayList<Visit> get(String nric) {
        return argMultimap.get(nric);
    }

    public boolean containsKey(String appointmentId) {
        return argMultimap.containsKey(appointmentId);
    }

    public void remove(String nric) {
        argMultimap.remove(nric);
    }

    public void removeVisit(String nric, int index) {
        ArrayList<Visit> visits = argMultimap.get(nric);
        visits.remove(index - 1);
    }
}