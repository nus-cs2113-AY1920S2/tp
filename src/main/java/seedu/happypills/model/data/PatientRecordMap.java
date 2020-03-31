package seedu.happypills.model.data;

import seedu.happypills.model.exception.HappyPillsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PatientRecordMap {
    private final Map<String, ArrayList<PatientRecord>> argMultimap = new HashMap<String, ArrayList<PatientRecord>>();

    /**
     * Adds an Visit into the VisitMap.
     *
     * @param patientRecord the appointment to be added into the map.
     * @throws HappyPillsException throws exception if there is already an appointment with the given datetime.
     */
    public void addPersonalRecord(PatientRecord patientRecord, String nric) throws HappyPillsException {
        ArrayList<PatientRecord> patientRecords = argMultimap.get(nric);

        // if list does not exist create it
        if (patientRecords == null) {
            patientRecords = new ArrayList<PatientRecord>();
            patientRecords.add(patientRecord);
            argMultimap.put(patientRecord.getNric(), patientRecords);
        } else {
            // add if item is not already in list
            if (!patientRecords.contains(patientRecord.nric)) {
                patientRecords.add(patientRecord);
                argMultimap.put(patientRecord.getNric(), patientRecords);
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

    public ArrayList<PatientRecord> get(String nric) {
        return argMultimap.get(nric);
    }

    public boolean containsKey(String appointmentId) {
        return argMultimap.containsKey(appointmentId);
    }

    public void remove(String nric) {
        argMultimap.remove(nric);
    }

    public void removeVisit(String nric, int index) {
        ArrayList<PatientRecord> patientRecords = argMultimap.get(nric);
        patientRecords.remove(index - 1);
    }

    public void removePersonalRecord(ArrayList<PatientRecord> patientRecord, String patientNric) {
        argMultimap.put(patientNric, patientRecord);
    }
}