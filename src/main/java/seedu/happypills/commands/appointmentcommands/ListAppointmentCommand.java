package seedu.happypills.commands.appointmentcommands;

import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.ui.TextUi;

public class ListAppointmentCommand extends AppointmentCommand {

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) {
        String message;
        if (appointments.size() == 0) {
            message = TextUi.getEmptyAppointmentList();
        } else {
            assert !appointments.isEmpty();
            message = TextUi.getAppointmentList(appointments);
        }
        return message;
    }
}
