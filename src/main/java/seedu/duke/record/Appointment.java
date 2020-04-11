package seedu.duke.record;

import seedu.duke.converter.TimeConverter;

import java.text.ParseException;

/**
 * This class contains the date and time for each patient.
 */

public class Appointment {
    private String date;
    private String time;
    private int patientId;

    /**
     * This constructor converts the input date and time into the desired format. During initialization, the date
     * and time will be in the right format.
     *
     * @param date      the date input by user.
     * @param time      the time input by user.
     * @param patientId the patientid of the patient of this appointment.
     * @throws ParseException this error occurs when date or string is empty.
     */
    public Appointment(String date, String time, int patientId) throws ParseException {
        assert date != null;
        assert time != null;

        this.date = TimeConverter.oldDate(date);
        this.time = TimeConverter.oldTime(time);
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    /**
     * Update the name if it is not null.
     *
     * @param date date that needs to be updated
     */
    public void setDate(String date) throws ParseException {
        if (!date.isBlank()) {
            this.date = TimeConverter.oldDate(date);
        }
    }

    public String getTime() {
        return time;
    }

    /**
     * This part is implemented for the EditPatientCommand class by Duc
     */

    /**
     * Update the age if it is a positive integer.
     *
     * @param time time that needs to be updated
     */
    public void setTime(String time) throws ParseException {
        if (!time.isBlank()) {
            this.time = TimeConverter.oldTime(time);
        }
    }

    /**
     * Overrides the default toString command so that the patient's appointment.
     * details can be printed in a specific string format.
     *
     * @return newToString The formatted string
     */
    @Override
    public String toString() {
        String newToString = null;
        newToString =
                "{" + "[Date]:" + " " + getDate() + " " + "|" + "[Time]:" + " " + getTime() + " | " + "[PatientId]: "
                        + getPatientId() + "}";
        return newToString;
    }

    /**
     * Method to update all the patient's information.
     *
     * @param date date that needs to be updated
     * @param time time that needs to be updated
     */
    public void setAppointmentInfo(String date, String time) throws ParseException {
        setDate(date);
        setTime(time);
    }
}
