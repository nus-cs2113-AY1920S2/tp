package seedu.happypills.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.valueOf;

public class Appointment {

    private int id = 1;

    protected String nric;
    protected String reason;
    protected String date;
    protected String time;
    protected Date datetime;
    protected String appointmentId;
    protected boolean isDone;

    public Appointment(String nric, String reason, String date, String time) {
        try {
            this.nric = nric;
            this.reason = reason;
            this.date = date;
            this.time = time;
            this.datetime = convertDate(date, time);
            this.appointmentId = valueOf(id);
            this.isDone = false;
            id++;
        } catch (Exception e) {
            System.out.println("    Date is invalid.\n");
        }
    }

    private Date convertDate(String date, String time) throws Exception {
        String dt = date + 'T' + time;
        Date datetime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dt);
        return datetime;
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
        try {
            this.datetime = convertDate(date, this.time);
            this.date = date;
        } catch (Exception e) {
            System.out.println("    Date is invalid. Please try again.");
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        try {
            this.datetime = convertDate(this.date, time);
            this.time = time;
        } catch (Exception e) {
            System.out.println("    Time is invalid. Please try again.");
        }
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
