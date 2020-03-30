package seedu.happypills.controller.commands.appointmentcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.view.ui.TextUi;

import java.io.IOException;
import java.util.logging.Logger;

public class DeleteAppointmentCommand extends AppointmentCommand {

    protected String appointmentId;

    Logger logger = Logger.getLogger(HappyPills.class.getName());

    public DeleteAppointmentCommand(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
        String message = "";
        if (appointments.containsKey(appointmentId)) {
            appointments.remove(appointmentId);
            try {
                Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH, TextUi.getFormattedApptString(appointments));
            } catch (IOException e) {
                logger.info("Adding patient list to file failed.");
            }
            message = "    Appointment has been removed.\n";
        } else {
            message = "    Appointment does not exist. Please try again.\n";
        }
        return message;
    }
}
