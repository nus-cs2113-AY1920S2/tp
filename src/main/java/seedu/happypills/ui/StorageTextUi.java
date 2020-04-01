package seedu.happypills.ui;

import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;

import java.util.Map;

public class StorageTextUi extends TextUi {

    public static String failToAddPatientMsg = "Fail to add patient to file.";
    public static String failToAddAppointmentMsg = "Fail to add appointment to file.";
    public static String failToWritePatientMsg = "Failed to write patients to file.";
    public static String failToWriteAppointmentMsg = "Failed to write patient's appointments to file.";

    /**
     * returns a list of patients' name and their details.
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
     * Retuens a list of patients' NRIC and appointment details.
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
}
