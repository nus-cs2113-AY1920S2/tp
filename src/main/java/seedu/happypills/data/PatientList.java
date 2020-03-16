package seedu.happypills.data;

import java.util.ArrayList;

public class PatientList extends ArrayList<Patient> {
    /**
     * Stores all the tasks provided.
     */
    private ArrayList<Patient> patients;

    /**
     * Constructor for TaskList Class.
     * It creates a new TaskList.
     */
    public PatientList() {
        this.patients = new ArrayList<Patient>();
    }

    /**
     * Instructs {} to list the tasks with the search keyword
     * if the correct format is used.
     *
     * @param patients Current list of tasks..
     * @param keyword  The word used for search.
     * @return true of the keyword is found, false otherwise.
     */
    public boolean findKeyword(String keyword, PatientList patients) {
        for (Patient patient : patients) {
            if (patient.getName().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}