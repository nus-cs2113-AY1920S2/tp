package seedu.happypills.data;

import seedu.happypills.exception.HappyPillsException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentMap {

    private final Map<Date, Appointment> argMultimap = new HashMap<Date, Appointment>();

    public void addAppointment(Appointment appointment) throws HappyPillsException {
        if (!isDatetimeTaken(appointment)) {
            argMultimap.put(appointment.getDatetime(), appointment);
        } else {
            throw new HappyPillsException("    There is already an Appointment at that date and time.\n"
                                                + "    Please try again.");
        }
    }


    public boolean isDatetimeTaken(Appointment appointment) {
        for (Map.Entry mapElement : argMultimap.entrySet()) {
            Appointment value = ((Appointment)mapElement.getValue());
            if (value.getDatetime() == appointment.getDatetime()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(String appointmentId) {
        return argMultimap.containsKey(appointmentId);
    }

    public void remove(String appointmentId) {
        argMultimap.remove(appointmentId);
    }
}
