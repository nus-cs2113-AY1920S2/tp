package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.AppointmentTextUi;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.logging.Logger;

public class DeleteAppointmentCommand extends AppointmentCommand {

    protected String nric;
    protected String appointmentId;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Retrieve the patient's confirmation.
     *
     * @param nric  y to confirm deletion, n to stop deletion.
     */
    public DeleteAppointmentCommand(String nric, String appointmentId) {
        this.nric = nric;
        this.appointmentId = appointmentId;
    }

    /**
     * Retrieve the patient from the NRIC of the delete command.
     *
     * @param patients Contains the list of patients to be searched.
     */
    private Patient findPatient(PatientMap patients) {
        if (patients.containsKey(nric)) {
            return patients.get(nric);
        } else {
            return null;
        }
    }

    /**
     * Retrieve the appointment from the patient provided.
     *
     * @param appointments Contains the appointment map to get appointment from.
     * @return the appointment with the specified apptID or null if not found
     */
    private Appointment findAppointment(AppointmentMap appointments) {
        if (appointments.containsKey(appointmentId)
                && appointments.get(appointmentId).getNric().equalsIgnoreCase(nric)) {
            return appointments.get(appointmentId);
        }
        return null;
    }

    /**
     * Delete an appointment of the patient from the shared map.
     *
     * @author sitinadiah25
     * @param appointments The shared appointment map.
     * @param appointmentId The id of the appointment to be deleted.
     */
    private Boolean deleteAppt(AppointmentMap appointments, String appointmentId) {
        if (appointments.containsKey(appointmentId)) {
            appointments.remove(appointmentId);
            return true;
        }
        return false;
    }

    /**
     * Delete an appointment of the patient in the patient object.
     *
     * @author janicetyy
     * @param patient The patient whose appointment is to be deleted.
     * @param apptID The id of the appointment to be deleted.
     */
    private Boolean deleteAppt(Patient patient, String apptID) {
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentId().equals(apptID)) {
                patient.getAppointments().remove(appointment);
                return true;
            }
        }
        return false;
    }

    /**
     * Carry out the procedures for deleting appointment.
     * @author janicetyy, sitinadiah25
     * @param patients Shared map of patients
     * @param appointments Shared map of appointments
     * @param visits Shared map of patient records
     * @return errorMessage or successMessage
     * @throws HappyPillsException not used but required as part of command
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        if (!Checker.isValidNric(nric)) {
            return TextUi.appendDivider(TextUi.INVALID_NRIC_MESSAGE);
        }
        String message = "";
        Patient delPatient = findPatient(patients);
        if (delPatient == null) {
            message = PatientTextUi.PATIENT_NOT_FOUND_MESSAGE;
            return message;
        }
        Appointment delAppt = findAppointment(appointments);
        if (delAppt == null) {
            message = AppointmentTextUi.APPOINTMENT_NOT_FOUND_MESSAGE;
            return message;
        }
        Boolean isSuccess = deleteAppt(appointments,appointmentId) && deleteAppt(delPatient,appointmentId);
        if (isSuccess) {
            message = "    Appointment has been removed.\n";
            try {
                Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH,
                        StorageTextUi.getFormattedApptString(appointments));
            } catch (IOException e) {
                logger.info(StorageTextUi.FAIL_TO_WRITE_APPOINTMENT_MSG);
            }
        } else {
            message = AppointmentTextUi.APPOINTMENT_NOT_FOUND_MESSAGE;
        }
        return message;
    }
}