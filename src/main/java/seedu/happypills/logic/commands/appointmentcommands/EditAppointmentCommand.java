package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.AppointmentTextUi;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.TextUi;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.HelpTextUi;

import java.io.IOException;
import java.util.logging.Logger;


//@@author janicetyy
public class EditAppointmentCommand extends AppointmentCommand {
    protected String nric;
    protected String newContent;
    protected  String apptID;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Constructor for EditAppointmentCommand Class.
     * It creates a new EditAppointmentCommand Object with the information provided.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     * @param apptID Contains the id of the appointment that is to be edited
     */
    public EditAppointmentCommand(String nric, String apptID, String newContent) {
        this.nric = nric.toUpperCase();
        this.newContent = newContent;
        this.apptID = apptID;
    }

    /**
     * Retrieve the patient from the NRIC of the Edit command.
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
        if (appointments.containsKey(apptID)
                && appointments.get(apptID).getNric().equalsIgnoreCase(nric)) {
            return appointments.get(apptID);
        }
        return null;
    }

    /**
     * Edit the date of the appointment in the list within the patient object.
     *
     * @param patient Contains the patient that to get appointment from.
     * @param newDate The new date to be edited into.
     * @return the appointment with the specified apptID or null if not found
     */
    private Boolean editDate(Patient patient, String newDate) {
        if (!Checker.isValidDate(newDate)) {
            return false;
        }
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentId().equals(apptID)) {
                appointment.setDate(newDate);
                return true;
            }
        }
        return false;
    }

    /**
     * Edit the date of the appointment in the shared appointment map.
     *
     * @param appointment The appointment which date is to be edited.
     * @param newDate The new date to be edited into.
     * @return true if successful, false otherwise.
     */
    private Boolean editDate(Appointment appointment, String newDate) {
        if (!Checker.isValidDate(newDate)) {
            TextUi.print(HelpTextUi.EDIT_APPOINTMENT_HELP_MESSAGE);
            return false;
        } else {
            appointment.setDate(newDate);
            return true;
        }
    }

    /**
     * Edit the time of the appointment in the shared appointment map.
     *
     * @param patient Contains the patient that to get appointment from.
     * @param newTime The new time to be edited into.
     * @return the appointment with the specified apptID or null if not found.
     */
    private Boolean editTime(Patient patient, String newTime) {
        if (!Checker.isValidTime(newTime)) {
            return false;
        }
        newTime += ":00";
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentId().equals(apptID)) {
                appointment.setTime(newTime);
                return true;
            }
        }
        return false;
    }

    /**
     * Edit the time of the appointment in the shared appointment map.
     *
     * @param appointment The appointment which time is to be edited.
     * @param newTime The new date to be edited into.
     * @return true if successful, false otherwise.
     */
    private Boolean editTime(Appointment appointment, String newTime) {
        if (Checker.isValidTime(newTime)) {
            appointment.setTime(newTime);
            return true;
        }
        return false;
    }

    /**
     * Edit the reason of the appointment in the shared appointment map.
     *
     * @param patient Contains the patient that to get appointment from.
     * @param newReason The new reason to be edited into.
     * @return the appointment with the specified apptID or null if not found.
     */
    private Boolean editReason(Patient patient, String newReason) {
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getAppointmentId().equals(apptID)) {
                appointment.setReason(newReason);
                return true;
            }
        }
        return false;
    }

    /**
     * Edit the remark of the appointment in the shared appointment map.
     *
     * @param appointment The appointment which reason is to be edited.
     * @param newReason The appointment's updated reason.
     * @return true if successful, false otherwise
     */
    private Boolean editReason(Appointment appointment, String newReason) {
        appointment.setReason(newReason);
        return true;
    }

    /**
     * Edit the appointment details with the information provided by calling.
     *
     * @param patients Contains the list of patients on which the commands are executed on.
     * @param appointments Contains the list of appointments on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if the edit field is not valid.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        if (!Checker.isValidNric(nric)) {
            return TextUi.appendDivider(TextUi.INVALID_NRIC_MESSAGE);
        }
        if (!Checker.isPositiveInteger(apptID)) {
            return TextUi.appendDivider(TextUi.INVALID_APPTID_MESSAGE);
        }
        if (newContent.length() < 3) {
            return HelpTextUi.EDIT_APPOINTMENT_HELP_MESSAGE + TextUi.DIVIDER;
        }
        String content = newContent.substring(2).trim();
        if (content.length() == 0) {
            return HelpTextUi.EDIT_APPOINTMENT_HELP_MESSAGE + TextUi.DIVIDER;
        }
        String field = newContent.substring(0,2).trim();
        Patient editPatient = findPatient(patients);
        if (editPatient == null) {
            throw new HappyPillsException(PatientTextUi.PATIENT_NOT_FOUND_MESSAGE);
        }
        Appointment editAppt = findAppointment(appointments); //from the shared appointment map
        if (editAppt == null) {
            throw new HappyPillsException(AppointmentTextUi.APPOINTMENT_NOT_FOUND_MESSAGE);
        }
        Boolean output = false;
        String errorMsg = "    Something went wrong, the edit could not be made.\n";
        if (field.equals("/d")) {
            output = editDate(editPatient, content) && editDate(editAppt,content);
            errorMsg = output ? errorMsg : Messages.MESSAGE_INVALID_DATE;
        } else if (field.equals("/t")) {
            output = editTime(editPatient, content) && editTime(editAppt,content);
            errorMsg = output ? errorMsg : Messages.MESSAGE_INVALID_TIME;
        } else if (field.equals("/r")) {
            output = editReason(editPatient, content) && editReason(editAppt,content);
        } else {
            throw new HappyPillsException("    Please try again. To learn more about the Edit appointment command, "
            + "\n    enter \"help appt edit\"");
        }
        if (output) {
            try {
                Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH,
                        StorageTextUi.getFormattedApptString(appointments));
            } catch (IOException e) {
                logger.info(StorageTextUi.FAIL_TO_WRITE_APPOINTMENT_MSG);
            }
        }
        errorMsg = TextUi.appendDivider(errorMsg);
        return output ? AppointmentTextUi.editAppointmentSuccessMessage(editAppt) : errorMsg;
    }
}
