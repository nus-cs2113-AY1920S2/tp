package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String comments;

    /**
     * Task object representing a Event or Assignment object.
     * @param name name of event or assignment
     * @param comments comments to the event or assignment
     */
    public Task(String name, String comments) {
        this.name = name;
        this.comments = comments;
        this.isDone = false;
    }

    public String getName() {
        return name;
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

    public abstract LocalDateTime getDateAndTime();

    public abstract LocalDate getDate();

    public abstract LocalTime getTime();

    /**
     * Returns symbol representing if task is completed.
     * @return return tick if task is completed, else return cross
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[/]";
        } else {
            return "[X]";
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), name);
    }
}
