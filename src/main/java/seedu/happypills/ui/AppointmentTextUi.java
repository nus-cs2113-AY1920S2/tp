package seedu.happypills.ui;

import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;

import java.util.ArrayList;
import java.util.Map;

public class AppointmentTextUi extends TextUi {

    public static final String GET_EMPTY_APPOINTMENT_LIST_MESSAGE = ""
            + "    There are no appointments in the list.\n" + DIVIDER;
    public static final String APPOINTMENT_NOT_FOUND_MESSAGE = ""
            + "    The appointment cannot be found. Please try again.";

    /**
     * Shows list of appointments in the program.
     * @param appointments The list of appointments.
     * @return message to be displayed to the user.
     */
    public static String getAppointmentList(AppointmentMap appointments) {
        String message = "    Here is your list of appointments:\n"
                + "    ID    | date       | time      | NRIC\n";
        for (Map.Entry appointment : appointments.entrySet()) {
            Appointment a = (Appointment)appointment.getValue();
            String nric = a.getNric();
            String id = a.getAppointmentId()
                    + repeat(6 - a.getAppointmentId().length());
            String date = a.getDate()
                    + repeat(11 - a.getDate().length());
            String time = a.getTime()
                    + repeat(10 - a.getTime().length());
            message += "    " + id + "| " + date + "| " + time + "| " + nric + "\n";
        }
        message += DIVIDER;
        return message;
    }

    /**
     * Generate a success message upon adding the appointment.
     * @param appointment the appointment which the program added.
     * @return Appointment details if any, and a notification message otherwise.
     */
    public static String addAppointmentSuccessMessage(Appointment appointment) {
        String message = "    Appointment has been added with Appointment ID "
                + appointment.getAppointmentId() + ".\n"
                + TextUi.DIVIDER;
        return message;
    }

    /**
     * Generate a success message upon finding the correct appointment.
     * @param patient the patient which the program have to find the appointment for.
     * @return Appointment details if any, and a notification message otherwise.
     */
    public static String getAppointmentSuccessMessage(Patient patient) {
        String returnMessage = "    Here are the patient's appointments:\n"
                + "    ID    | NRIC      | Date       | Time      | Reason      \n";
        String content = "";
        ArrayList<Appointment> tempList = patient.getAppointments();
        for (Appointment appointment : tempList) {
            String id = appointment.getAppointmentId()
                    + repeat(6 - appointment.getAppointmentId().length());
            String nric = appointment.getNric()
                    + repeat(10 - appointment.getNric().length());
            String reason = appointment.getReason();
            String date = appointment.getDate()
                    + repeat(11 - appointment.getDate().length());
            String time = appointment.getTime()
                    + repeat(10 - appointment.getTime().length());
            content += "    " + id + "| " + nric + "| " + date + "| " + time + "| " + reason + "\n";
        }
        if (content.isEmpty()) {
            returnMessage = "    The patient does not have any appointments scheduled.\n" + TextUi.DIVIDER;
        } else {
            returnMessage += content + DIVIDER;
        }

        return returnMessage;
    }

    /**
     * Display updated patient's appointment.
     *
     * @param appointment The appointment that was updated.
     * @return The patient's updated appointment.
     */
    public static String editAppointmentSuccessMessage(Appointment appointment) {
        String message = "    Patient appointment have been updated as follows:\n"
                + appointment + DIVIDER;
        return message;
    }

    /**
     * Display updated patient's appointment.
     *
     * @param appointment The appointment that was updated.
     * @return The patient's updated appointment.
     */
    public static String doneAppointmentSuccessMessage(Appointment appointment) {
        String message = "    The following appointment has been marked done:\n"
                + appointment + DIVIDER;
        return message;
    }
}
