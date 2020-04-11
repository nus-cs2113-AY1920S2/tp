package seedu.happypills.model.data;

import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.Messages;

import java.util.HashMap;
import java.util.Map;

//@@author itskesin
public class PatientMap {

    private final Map<String, Patient> argMultimap = new HashMap<String, Patient>();

    /**
     * Associates the specified argument value with key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param patient  Patient's details.
     * @throws HappyPillsException If NRIC already exist in the patient list.
     */
    public void add(Patient patient) throws HappyPillsException {
        if (!argMultimap.containsKey(patient.nric)) {
            argMultimap.put(patient.nric, patient);
        } else {
            throw new HappyPillsException(Messages.MESSAGE_PATIENT_IS_IN_THE_MAP);
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
