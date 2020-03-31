package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
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
        String message = "";
        Patient delPatient = findPatient(patients);
        if (delPatient.equals(null)) {
            message = "    Appointment has been removed.\n";
            return message;
        }
        Boolean isSuccess = deleteAppt(appointments,appointmentId) && deleteAppt(delPatient,appointmentId);
        if (isSuccess) {
            message = "    Appointment has been removed.\n";
            try {
                Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH, TextUi.getFormattedApptString(appointments));
            } catch (IOException e) {
                logger.info("Adding patient list to file failed.");
            }
        } else {
            message = "    Appointment does not exist. Please try again.\n";
        }
        return message;
    }
}