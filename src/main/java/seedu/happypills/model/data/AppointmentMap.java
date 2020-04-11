package seedu.happypills.model.data;

import seedu.happypills.model.exception.HappyPillsException;

import java.util.HashMap;
import java.util.Map;

public class AppointmentMap {

    private final Map<String, Appointment> argMultimap = new HashMap<String, Appointment>();

    /**
     * Adds an appointment into the AppointmentMap.
     *
     * @param appointment the appointment to be added into the map.
     * @throws HappyPillsException throws exception if there is already an appointment with the given datetime.
     */
    public void addAppointment(Appointment appointment) throws HappyPillsException {
        int current = argMultimap.size() + 1;
        //appointment.setAppointmentId(valueOf(current));
        if (!argMultimap.containsKey(appointment.appointmentId)) {
            argMultimap.put(appointment.appointmentId,appointment);
        }
    }

    public Iterable<? extends Map.Entry> entrySet() {
        return argMultimap.entrySet();
    }

    public int size() {
        return argMultimap.size();
    }

    public boolean isEmpty() {
        return argMultimap.isEmpty();
    }

    public Appointment get(String appointmentID) {
        return argMultimap.get(appointmentID);
    }

    public boolean containsKey(String appointmentId) {
        return argMultimap.containsKey(appointmentId);
    }

    public void remove(String appointmentId) {
        argMultimap.remove(appointmentId);
    }
}
