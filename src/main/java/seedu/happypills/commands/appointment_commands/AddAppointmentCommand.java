package seedu.happypills.commands.appointment_commands;

import seedu.happypills.data.Appointment;
import seedu.happypills.data.AppointmentMap;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;

import java.io.IOException;

public class AddAppointmentCommand extends AppointmentCommand {
    protected String nric;
    protected String date;
    protected String time;
    protected String reason;

    public AddAppointmentCommand(String nric, String date, String time, String reason) {
        this.nric = nric;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    @Override
    public String execute(PatientMap patients, AppointmentMap appointments) throws HappyPillsException {
//        assert !patients.containsKey(nric) : "New nric can be added";
        Appointment appointment = new Appointment(nric, reason, date, time);
        appointments.addAppointment(appointment);
//        try {
//            Storage.addSingleItemToFile(Storage.PATIENT_FILEPATH, tempPatient.toSave());
//        } catch (IOException e) {
//            logger.info("Patient not added to file.");
//        }
        String message = "";
        message = "    Appointment has been added.\n";
        return message;
    }
}
