package seedu.happypills.ui;

import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;


import java.util.ArrayList;
import java.util.Map;

public class StorageTextUi extends TextUi {

    public static final String FAIL_TO_ADD_PATIENT_MSG = "Fail to add patient to file.";
    public static final String FAIL_TO_ADD_APPOINTMENT_MSG = "Fail to add appointment to file.";
    public static final String FAIL_TO_WRITE_PATIENT_MSG = "Failed to write patients to file.";
    public static final String FAIL_TO_WRITE_APPOINTMENT_MSG = "Failed to write patient's appointments to file.";
    public static final String FAIL_TO_ADD_PR_MSG = "Fail to add patient record to file.";
    public static final String FAIL_TO_WRITE_PR_MSG = "Failed to write patient records to file.";

    /**
     * returns a list of patients' name and their details.
     * @author janicetyy
     * @param patients A patient list with all existing patients.
     * @return a message to be displayed to user.
     */
    public static String getFormattedPatientString(PatientMap patients) {
        String formattedPatientString = "";
        for (Map.Entry patient : patients.entrySet()) {
            Patient p = (Patient)patient.getValue();
            formattedPatientString += p.toSave();
        }
        return formattedPatientString;
    }

    /**
     * Returns a list of patients' NRIC and appointment details.
     * @author janicetyy
     * @param appointments An appointment list with all existing patients.
     * @return a message to be displayed to user.
     */
    public static String getFormattedApptString(AppointmentMap appointments) {
        String formattedAppointmentString = "";
        for (Map.Entry appointment : appointments.entrySet()) {
            Appointment a = (Appointment) appointment.getValue();
            formattedAppointmentString += a.toSave();
        }
        return formattedAppointmentString;
    }

    /**
     * Returns a list of patients' NRIC and records.
     * @author janicetyy
     * @param patientRecords A list with all existing patient record.
     * @param patientMap A shared map of patients
     * @return a string to be used in storage.
     */
    public static String getFormattedPrString(PatientRecordMap patientRecords, PatientMap patientMap) {
        String formattedPrString = "";
        for (Map.Entry patients : patientMap.entrySet()) {
            Patient patient = (Patient) patients.getValue();
            if (patientRecords.containsKey(patient.getNric())
                    && (patientRecords.get(patient.getNric()) != null)) {
                ArrayList<PatientRecord> prs = patientRecords.get(patient.getNric());
                for (int index = 0; index < prs.size(); index++) {
                    formattedPrString += prs.get(index).toSave();
                }
            }
        }
        return formattedPrString;
    }
}
