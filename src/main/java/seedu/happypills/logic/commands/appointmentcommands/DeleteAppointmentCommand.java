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
        if (appointments.containsKey(appointmentId)) {
            return appointments.get(appointmentId);
        }
        return null;
    }

    /**
     * Delete an appointment of the patient from the shared map.
     *
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

    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        if(!Checker.isValidNric(nric)) {
            return TextUi.appendDivider(TextUi.INVALID_NRIC_MESSAGE);
        }
        String message = "";
        Patient delPatient = findPatient(patients);
        if (delPatient == null) {
            message = PatientTextUi.patientNotFoundMessage;
            return message;
        }
        Appointment delAppt = findAppointment(appointments);
        if (delAppt == null) {
            message = AppointmentTextUi.appointmentNotFoundMessage;
            return message;
        }
        Boolean isSuccess = deleteAppt(appointments,appointmentId) && deleteAppt(delPatient,appointmentId);
        if (isSuccess) {
            message = "    Appointment has been removed.\n";
            try {
                Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH,
                        StorageTextUi.getFormattedApptString(appointments));
            } catch (IOException e) {
                logger.info(StorageTextUi.failToWriteAppointmentMsg);
            }
        } else {
            message = AppointmentTextUi.appointmentNotFoundMessage;
        }
        return message;
    }
}