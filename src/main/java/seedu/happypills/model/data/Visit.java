package seedu.happypills.model.data;

import static java.lang.String.valueOf;

/**
 * Represents an vist.
 * It also functions as base class for visit from which specialised tasks are inherited from.
 */
public class Visit {
    /**
     * Stores the details of the appointment.
     */
    protected String nric;
    protected String reason;
    protected String date;
    protected String time;
    protected boolean isDone;

    /**
     * Constructor for Visit class.
     * It creates a new appointment with the details provided by the user.
     *
     * @param nric NRIC of patient.
     * @param reason reason for appointment.
     * @param date date of appointment.
     * @param time time of appointment.
     */
    public Visit(String nric, String reason, String date, String time) {
        this.nric = nric;
        this.reason = reason;
        this.date = date;
        this.time = time;
        this.isDone = false;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
