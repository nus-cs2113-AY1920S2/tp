package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.ui.AppointmentTextUi;
import seedu.happypills.ui.PatientTextUi;

public class FindAppointmentCommand extends AppointmentCommand {
    protected String patientNric;

    /**
     * Constructor for FindAppointmentCommand Class.
     * It creates a new FindAppointmentCommand Object with the information provided.
     *
     * @param patientNric        Contains the NRIC of the patient that is to be retrieved.
     */
    public FindAppointmentCommand(String patientNric) {
        this.patientNric = patientNric;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap visits) {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return AppointmentTextUi.getAppointmentSuccessMessage(patients.get(patientNric));
        } else {
            return PatientTextUi.patientNotFoundMessage;
        }
    }

}
