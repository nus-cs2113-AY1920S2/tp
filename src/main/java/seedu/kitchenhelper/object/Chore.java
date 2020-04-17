package seedu.kitchenhelper.object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Allows users to add tasks to complete by certain deadline.
 */
public class Chore {
    public String description;
    public String dateStr = null;
    public Date date = null;
    public boolean isDone;

    /**
     * Constructor for Chore object.
     *
     * @param description the task description.
     * @param dateStr the deadline specified in String.
     */
    public Chore(String description, String dateStr) {
        this.description = description;
        this.dateStr = dateStr;
        this.isDone = false;
    }

    /**
     * Constructor for Chore object.
     *
     * @param description the task description.
     * @param date the deadline specified by Date Java class.
     */
    public Chore(String description, Date date) {
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    /**
     * Marks the chore as done.
     *
     * @return isDone class variable set to true.
     */
    public boolean markAsDone() {
        return isDone = true;
    }

    /**
     * Displays icon that represents whether chore is done or not.
     *
     * @return tick if chore is done and cross otherwise.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "/";
        } else {
            return "x";
        }
    }

    /**
     * Checks if there is only one chore in the chorelist.
     *
     * @param choreList the list of Chores.
     * @return "s" to append to "chore" in feedback to user if zero or multiple chores.
     */
    public String checkSingular(ArrayList<Chore> choreList) {
        if (choreList.size() == 1) {
            return "";
        } else {
            return "s";
        }
    }

    /**
     * Converts deadline to a String in specified format
     * if it is Java Date object.
     *
     * @return the deadline of type String.
     */
    public String convertDateToString() {
        if (dateStr == null) {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
        } else {
            return dateStr;
        }
    }

    /**
     * Distinguishes the deadline saved in text file between
     * String or Date object.
     *
     * @param description the task description.
     * @param dateStr the deadline saved in text file.
     * @return a new Chore object with either Date or String deadline.
     */
    public static Chore createChoreWhenLoadFile(String description, String dateStr) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = dateFormat.parse(dateStr);
            return new Chore(description, date);
        } catch (ParseException e) {
            return new Chore(description, dateStr);
        }
    }

    /**
     * To compare two Chore objects based on their attributes.
     *
     * @return boolean return false if any of the attributes are not equal to each other.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Chore) {
            Chore i = (Chore) o;
            if (dateStr == null) {
                return this.description.equals(i.description)
                        && this.isDone == isDone
                        && this.date.equals(i.date);
            } else {
                return this.description.equals(i.description)
                        && this.isDone == isDone
                        && this.dateStr.equals(i.dateStr);
            }
        } else {
            return false;
        }
    }

    /**
     * To format each Chore as a String for display to user.
     *
     * @return String consisting of completion status, task description and deadline.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " (by: " + convertDateToString() + ")";
    }

}
