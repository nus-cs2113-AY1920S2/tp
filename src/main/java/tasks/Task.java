package tasks;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Task {

    protected String name;
    protected String details;
    protected boolean isDone;
    protected LocalDateTime dateAndTime;
    protected String comments;

    /**
     * Task object representing a Event or Assignment object.
     * @param name name of event or assignment
     * @param details module code or location
     * @param dateAndTime date and time of event or deadline
     * @param comments comments to the event or assignment
     */
    public Task(String name, String details, LocalDateTime dateAndTime, String comments) {
        this.name = name;
        this.details = details;
        this.dateAndTime = dateAndTime;
        this.comments = comments;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public LocalDate getDate() {
        return dateAndTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateAndTime.toLocalTime();
    }

    public String getComments() {
        return comments;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns symbol representing if task is completed.
     * @param isDone boolean value to check if task is completed
     * @return return tick if task is completed, else return cross
     */
    public String getStatusIcon(boolean isDone) {
        if (isDone) {
            return "[/]";
        } else {
            return "[X]";
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s (by: %s)\n    %s",
                getStatusIcon(isDone), getDetails(), getDateAndTime(), getComments());
    }
}
