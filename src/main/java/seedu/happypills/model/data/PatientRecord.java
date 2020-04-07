package seedu.happypills.model.data;

import static java.lang.String.valueOf;

//@@author NyanWunPaing
/**
 * Represents an patient record.
 * It also functions as base class for visit from which specialised tasks are inherited from.
 */
public class PatientRecord {
    /**
     * Stores the details of the appointment.
     */
    protected String nric;
    protected String symptom;
    protected String diagnosis;
    protected String date;
    protected String time;

    /**
     * Constructor for Visit class.
     * It creates a new appointment with the details provided by the user.
     *
     * @param nric NRIC of patient.
     * @param symptom patient's symptom.
     * @param diagnosis patient's diagnosis.
     * @param date date of appointment.
     * @param time time of appointment.
     */
    public PatientRecord(String nric, String symptom, String diagnosis, String date, String time) {
        this.nric = nric;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.date = date;
        this.time = time;
    }

    public String getNric() {
        return nric;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return  "        NRIC : " + nric.trim().toUpperCase() + "\n"
                + "        Symptom : " + symptom.trim() + "\n"
                + "        Diagnosis : " + diagnosis.trim() + "\n"
                + "        Date : " + date.trim() + "\n"
                + "        time : " + time.trim() + "\n";
    }

    /**
     * Create a string with all the patient's record for storage to a text file.
     * Each variable is separated with | as a divider.
     *
     * @return a formatted string with patient's records.
     */
    public String toSave() {
        return  nric.trim().toUpperCase() + "|"
                + symptom.trim() + "|"
                + diagnosis.trim() + "|"
                + date.trim() + "|"
                + time.trim() + System.lineSeparator();
    }
}
