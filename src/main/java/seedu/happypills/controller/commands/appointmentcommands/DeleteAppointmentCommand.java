package seedu.happypills.controller.commands.appointmentcommands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.model.exception.HappyPillsException;

public class DeleteAppointmentCommand extends AppointmentCommand {

    protected String appointmentId;

    public DeleteAppointmentCommand(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, VisitMap visits) throws HappyPillsException {
        String message = "";
        if (appointments.containsKey(appointmentId)) {
            appointments.remove(appointmentId);
            message = "    Appointment has been removed.\n";
        } else {
            message = "    Appointment does not exist. Please try again.\n";
        }
        return message;
    }
}
