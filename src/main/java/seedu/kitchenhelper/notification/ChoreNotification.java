package seedu.kitchenhelper.notification;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Checks if the chore deadline is approaching and alerts users.
 */
public class ChoreNotification {

    public static final String DEADLINE_NOTIFICATION = "The deadline for these chores are approaching!\n";
    public static final String EMPTY_NOTIFICATION = "You have no deadlines upcoming in the next 3 days.\n";
    public static final String OVERDUE_NOTIFICATION = "The deadline for these chores are overdue!\n";

    /**
     * For those chores containing deadline specified by date,
     * check for expired deadlines or deadlines upcoming in 3 days,
     * then notifies the users.
     *
     * @param choreList the list of Chores.
     * @return String containing type of notification for users.
     */
    public String getNotifications(ArrayList<Chore> choreList) {
        String notification = "";
        String upcomingChores = "";
        String overdueChores = "";
        for (Chore chore:choreList) {
            if (hasDateAsDeadline(chore) && isApproachingDeadline(chore)) {
                upcomingChores += (chore + "\n");
            }
            if (hasDateAsDeadline(chore) && isOverdue(chore)) {
                overdueChores += (chore + "\n");
            }
        }

        if (!overdueChores.isEmpty()) {
            notification += OVERDUE_NOTIFICATION + overdueChores + Ui.LS;
        }
        if (upcomingChores.isEmpty()) {
            notification += EMPTY_NOTIFICATION + Ui.DIVIDER;
        } else {
            notification += DEADLINE_NOTIFICATION + upcomingChores + Ui.DIVIDER;
        }
        return notification;
    }

    /**
     * Checks if the deadline is approaching in 3 days.
     *
     * @param chore the chore to check.
     * @return True if deadline is in 3 days or less.
     */
    public boolean isApproachingDeadline(Chore chore) {
        Date deadline = chore.date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(deadline);
        calendar.add(Calendar.DATE, -3);
        Date threeDaysBefore = calendar.getTime();
        Date currentDate = new Date();

        if (threeDaysBefore.before(currentDate) && deadline.after(currentDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the chore specifies deadline by date.
     *
     * @param chore the chore to check.
     * @return True if chore deadline is Date object.
     */
    public boolean hasDateAsDeadline(Chore chore) {
        if (chore.date != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if deadline of chore is overdue.
     *
     * @param chore the chore to check.
     * @return True if deadline over.
     */
    public boolean isOverdue(Chore chore) {
        Date deadline = chore.date;
        Date currentDate = new Date();

        if (deadline.before(currentDate)) {
            return true;
        } else {
            return false;
        }
    }


}
