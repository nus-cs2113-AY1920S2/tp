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
     * Constructor for Patient Record class.
     * It creates a new patient medical record with the details provided by the user.
     *
     * @param nric      NRIC of patient
     * @param symptom   patient's symptom
     * @param diagnosis patient's diagnosis
     * @param date      date of medical treatment
     * @param time      time of medical treatment
     */
    public PatientRecord(String nric, String symptom, String diagnosis, String date, String time) {
        this.nric = nric;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.date = date;
        this.time = time;
    }

    /**
     * Getter function for NRIC of the patient.
     *
     * @return nric Represents the NRIC of the patient
     */
    public String getNric() {
        return nric;
    }

    /**
     * Getter function for symptom of the patient.
     *
     * @return symptom Represents the symptom of the patient
     */
    public String getSymptom() {
        return symptom;
    }

    /**
     * Setter function for symptom of the patient.
     *
     * @param symptom Represents the symptom of the patient
     */
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    /**
     * Getter function for diagnosis of the patient.
     *
     * @return diagnosis Represents the diagnosis of the patient
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Setter function for diagnosis of the patient.
     *
     * @param diagnosis Represents the diagnosis of the patient
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Getter function for date of the patient.
     *
     * @return date Represents the date of the medical treatment
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter function for date of the patient.
     *
     * @param date Represents the date of the medical treatment
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter function for time of the patient.
     *
     * @return time Represents the time of the medical treatment
     */
    public String getTime() {
        return time;
    }

    /**
     * Setter function for time of the patient.
     *
     * @param time Represents the time of the medical treatment
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns the information related to the patient medical record in form of a string.
     *
     * @return string which consists of the patient's detailed medical record information
     */
    @Override
    public String toString() {
        return "        NRIC : " + nric.trim().toUpperCase() + "\n"
                + "        Symptom : " + symptom.trim() + "\n"
                + "        Diagnosis : " + diagnosis.trim() + "\n"
                + "        Date : " + date.trim() + "\n"
                + "        time : " + time.trim() + "\n";
    }

    /**
     * Create a string with all the patient's record for storage to a text file.
     * Each variable is separated with | as a divider.
     *
     * @return a formatted string with patient's records
     */
    public String toSave() {
        return nric.trim().toUpperCase() + "|"
                + symptom.trim() + "|"
                + diagnosis.trim() + "|"
                + date.trim() + "|"
                + time.trim() + System.lineSeparator();
    }
}
