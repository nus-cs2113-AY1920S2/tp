package seedu.happypills.data;

import seedu.happypills.exception.HappyPillsException;
import java.util.HashMap;
import java.util.Map;

public class PatientMap {

    private final Map<String, Patient> argMultimap = new HashMap<String, Patient>();

    /**
     * Associates the specified argument value with key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param patient  Patient's details
     * @throws HappyPillsException exception if NRIC already exist
     */
    public void add(Patient patient) throws HappyPillsException {
        if (!argMultimap.containsKey(patient.nric)) {
            argMultimap.put(patient.nric, patient);
        } else {
            throw new HappyPillsException("    Patient is already in the list. Please use help command.");
        }
    }

    public int size() {
        return argMultimap.size();
    }

    public Patient get(String nric) {
        return argMultimap.get(nric);
    }

    public boolean containsKey(String patientNric) {
        return argMultimap.containsKey(patientNric);
    }

    public boolean isEmpty() {
        return argMultimap.isEmpty();
    }

    public Iterable<? extends Map.Entry> entrySet() {
        return argMultimap.entrySet();
    }

    public void remove(String nric) {
        argMultimap.remove(nric);
    }
}
