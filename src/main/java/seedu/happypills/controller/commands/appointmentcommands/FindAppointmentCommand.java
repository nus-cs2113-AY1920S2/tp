package seedu.happypills.controller.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.view.ui.TextUi;

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
    public String execute(PatientMap patients, AppointmentMap appointments) {
        assert !patientNric.isEmpty() : "No NRIC was provided";
        if (patients.containsKey(patientNric)) {
            return TextUi.getAppointmentSuccessMessage(patients.get(patientNric));
        } else {
            return TextUi.patientNotExist(patientNric);
        }
    }

}
