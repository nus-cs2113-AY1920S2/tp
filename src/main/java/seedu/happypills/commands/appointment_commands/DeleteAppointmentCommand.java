package seedu.happypills.commands.appointment_commands;

import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

public class DeleteAppointmentCommand extends AppointmentCommand {

    protected String appointmentId;

    public DeleteAppointmentCommand(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
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
