package seedu.happypills.controller.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.view.ui.TextUi;

public class ListAppointmentCommand extends AppointmentCommand {

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, VisitMap visits) {
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
