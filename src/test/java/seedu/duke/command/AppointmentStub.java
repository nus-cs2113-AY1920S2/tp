package seedu.duke.command;

public class AppointmentStub {
    private final String date;
    private final String time;

    public AppointmentStub(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


    public String toString() {
        return "{" + "[Date]:" + " " + getDate() + " " + "|" + "[Time]:" + " " + getTime() + "}";
    }
}
