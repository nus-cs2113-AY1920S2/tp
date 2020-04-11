package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.generator.PatientIdManager;
import seedu.duke.record.Appointment;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * This class deals with the command relating to adding of appointments into the appointment list.
 * <p></p>
 * <p>
 * It achieves this by acting as a bridge to connect the functions of {@link seedu.duke.parser.Parser},
 * {@link Appointment}, {@link AppointmentList} and {@link Storage}.
 * </p>
 * <p></p>
 * <p>
 * To elaborate, it converts the output of {@link seedu.duke.parser.Parser} into a {@link Appointment} object, after
 * which the {@link Appointment} object is then
 * added into the {@link AppointmentList} list. Finally, the {@link Storage} class will be used to save the updated
 * list into offline data to reflect the change.
 * </p>
 *
 * @author Andy
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "adda";
    private static final String EXAMPLE = "adda \\date 20/12/2020 \\time 2300 \\pid 23";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add an appointment to the appointment's list.\n"
            + "Example: " + EXAMPLE;
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PATIENT_ID = "pid";
    public static final String PID = "pid";
    private String date;
    private String time;
    private int patientId;

    /**
     * Constructor for the appointment class.
     *
     * @param appointmentInfo the <code>Map</code> that contains the information relating to the appointment.
     */
    public AddAppointmentCommand(Map<String, String> appointmentInfo) throws InvalidFormatException {
        this.date = appointmentInfo.get(DATE);
        this.time = appointmentInfo.get(TIME);
        try {
            this.patientId = Integer.parseInt(appointmentInfo.get(PID));
            if (this.patientId < 0) {
                this.patientId = -1;
            }
            if (!PatientIdManager.checkPatientIdUsed(patientId)) {
                this.patientId = -2;
            }

        } catch (NumberFormatException e) {
            this.patientId = -1;
        }
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    /**
     * For this execution, the appointment information is added into the appointment list.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException this exception is thrown by the {@link Storage} class if it fails to save the current
     *                     appointment list into offline data.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, ParseException {

        if (this.patientId == -1) {
            Ui.showWrongPid();
            return;
        }

        if (this.patientId == -2) {
            Ui.showNoPidExist();
            return;
        }

        Appointment appointment = new Appointment(this.date, this.time, this.patientId);

        /* Hacky method to add appointments into the appointment list.*/
        AppointmentList.getAppointmentList().add(appointment);

        /* For Auto-saving */
        storage.saveAppointmentsList();

        ui.showAppointmentAddSuccess();
    }

}
