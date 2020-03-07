package seedu.duke;

public class Attendance extends Student {

    protected String description;
    protected String hasAttended;

    public Attendance(String description, String hasAttended, String StudentName) {
        super(StudentName);
        this.description = description;
        this.hasAttended = hasAttended;
    }

    public String getDescription(){
        return description;
    }

    public String getAttendance(){
        return hasAttended;
    }

}
