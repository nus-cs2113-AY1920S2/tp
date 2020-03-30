package seedu.happypills.commands.appointmentcommands;

import seedu.happypills.data.Appointment;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

public class EditAppointmentCommand extends AppointmentCommand {
    protected String nric;
    protected String newContent;
    protected  String apptID;

    /**
     * Constructor for EditAppointmentCommand Class.
     * It creates a new EditAppointmentCommand Object with the information provided.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditAppointmentCommand(String nric, String apptID, String newContent) {
        this.nric = nric;
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
        if (appointments.containsKey(apptID)) {
            return appointments.get(apptID);
        }
        return null;
    }

    /**
     * check string if fits date format.
     * @param date date in String type
     * @return true if correct date format, false otherwise
     */
    static boolean checkDate(String date) {
        String pattern = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((2[0-9])[0-9]{2})";
        return date.matches(pattern);
    }

    /**
     * Edit the date of the appointment in the list within the patient object.
     *
     * @param patient Contains the patient that to get appointment from.
     * @param newDate The new date to be edited into.
     * @return the appointment with the specified apptID or null if not found
     */
    private Boolean editDate(Patient patient, String newDate) {
        if (!checkDate(newDate)) {
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
        if (!checkDate(newDate)) {
            TextUi.print(TextUi.editAptHelpMessage());
            return false;
        } else {
            appointment.setDate(newDate);
            return true;
        }
    }

    /**
     * check string if fits time format.
     * @param time time in String type.
     * @return true if correct date format, false otherwise.
     */
    static boolean checkTime(String time) {
        String pattern = "([01][0-9]|2[0-3]):([0-5][0-9])";
        return time.matches(pattern);
    }

    /**
     * Edit the time of the appointment in the shared appointment map.
     *
     * @param patient Contains the patient that to get appointment from.
     * @param newTime The new time to be edited into.
     * @return the appointment with the specified apptID or null if not found.
     */
    private Boolean editTime(Patient patient, String newTime) {
        if (!checkTime(newTime)) {
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
        if (checkTime(newTime)) {
            newTime += ":00";
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
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
        if (newContent.length() < 3) {
            return TextUi.editAptHelpMessage();
        }
        String content = newContent.substring(2).trim();
        if (content.length() == 0) {
            return TextUi.editAptHelpMessage();
        }
        String field = newContent.substring(0,2);
        Patient editPatient = findPatient(patients);
        if (editPatient == null) {
            throw new HappyPillsException("    Patient not found. Please try again.");
        }
        Appointment editAppt = findAppointment(appointments); //from the shared appointment map
        if (editAppt == null) {
            throw new HappyPillsException("    Appointment not found. Please try again.");
        }
        Boolean output = false;
        String errorMsg = "    Something went wrong, the edit could not be made.\n";
        if (field.equals("/d")) {
            output = editDate(editPatient, content) && editDate(editAppt,content);
            errorMsg = output ? errorMsg : "    Invalid date or date format(DD/MM/YYYY).\n";
        } else if (field.equals("/t")) {
            output = editTime(editPatient, content) && editTime(editAppt,content);
            errorMsg = output ? errorMsg : "    Invalid time or time format(HH:MM).\n";
        } else if (field.equals("/r")) {
            output = editReason(editPatient, content) && editReason(editAppt,content);
        } else {
            throw new HappyPillsException("    Please try again. To learn more about the Edit appointment command, "
            + "\n    enter \"help appt edit\"");
        }
        /*try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH,TextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info("Adding patient list to file failed.");
        }*/
        errorMsg = TextUi.appendDivider(errorMsg);
        return output ? TextUi.editAppointmentSuccessMessage(editAppt) : errorMsg;
    }
}
