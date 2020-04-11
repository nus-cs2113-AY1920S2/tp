package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.HappyPills;
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

import java.io.IOException;
import java.util.logging.Logger;

//@@author sitinadiah25
public class AddAppointmentCommand extends AppointmentCommand {
    protected String nric;
    protected String date;
    protected String time;
    protected String reason;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    /**
     * Constructor for AddPatientCommand Class.
     * It creates a new AddPatientCommand Object with the information provided.
     *
     * @param nric   patient's nric.
     * @param date   date of appointment.
     * @param time   time of appointment.
     * @param reason reason for appointment.
     */
    public AddAppointmentCommand(String nric, String date, String time, String reason) {
        this.nric = nric.toUpperCase();
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    /**
     * Add appointment into the Appointment list and the patient's appointment list.
     *
     * @param patients     the list of patients.
     * @param appointments the list of appointments.
     * @return the message to be displayed to the user.
     * @throws HappyPillsException throws exception if there is already an appointment at the same datetime.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        String message = "";
        if (!patients.containsKey(nric)) {
            throw new HappyPillsException(PatientTextUi.PATIENT_NOT_FOUND_MESSAGE);
        } else {
            Appointment appointment = new Appointment(nric, reason, date, time);
            appointments.addAppointment(appointment);
            Patient patient = (Patient) patients.get(nric);
            patient.addAppointment(appointment);
            try {
                Storage.addSingleItemToFile(Storage.APPOINTMENT_FILEPATH, appointment.toSave());
            } catch (IOException e) {
                logger.warning(StorageTextUi.FAIL_TO_ADD_APPOINTMENT_MSG);
            }
            message = AppointmentTextUi.addAppointmentSuccessMessage(appointment);
        }
        return message;
    }
}
