package seedu.happypills.logic.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.ui.AppointmentTextUi;

public class ListAppointmentCommand extends AppointmentCommand {

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap visits) {
        String message;
        if (appointments.size() == 0) {
            message = AppointmentTextUi.getEmptyAppointmentListMessage;
        } else {
            assert !appointments.isEmpty();
            message = AppointmentTextUi.getAppointmentList(appointments);
        }
        return message;
    }
}
