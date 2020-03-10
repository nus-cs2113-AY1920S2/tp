package jikan.ui;

import jikan.activity.ActivityList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Ui {
    public static final String GREETING = "___________________________________________\n"
            + "   0101 01010101 01   01  .010.  101   01 \n"
            + "   `10'   `10'   10 ,10' 10' `10 101o  01 \n"
            + "    01     01    01,01   01   01 0101o 10 \n"
            + "    10     10    10`10   10ooo10 10 11o01 \n"
            + "01. 01    .01.   01 `01. 01   01 01  1010 \n"
            + "010101  01010101 10   10 10   10 10   110 \n"
            + "___________________________________________\n"
            + " Hello! I'm Jikan\n"
            + " What can I do for you today?";

    public static final String DIVIDER = "-------------------------------------------";

    /** Prints the logo and greeting so users know the app is working. */
    public void printGreeting() {
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    /** Prints exit message and exits the app. */
    public void exitFromApp() {
        System.out.println(DIVIDER);
        System.out.println(" Bye! See you again.");
        System.out.println(DIVIDER);
        System.exit(0);
    }

    /** Prints divider between user input and app feedback. */
    public void printDivider(String line) {
        System.out.println(DIVIDER);
        System.out.println(line);
        System.out.println(DIVIDER);
    }

    /** Prints all the activities in the list. */
    public void printList(ActivityList activityList) {
        System.out.println(DIVIDER);
        System.out.println("Your completed activities:");
        for (int i = 0; i < activityList.getSize(); i++) {
            long durationInNanos = (activityList.get(i).duration).toNanos();
            String duration = String.format("%02d:%02d:%02d",
                    TimeUnit.NANOSECONDS.toHours(durationInNanos),
                    TimeUnit.NANOSECONDS.toMinutes(durationInNanos)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(durationInNanos)),
                    TimeUnit.NANOSECONDS.toSeconds(durationInNanos)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(durationInNanos)));
            System.out.print(i + 1 + ". " + activityList.get(i).name + " " + duration);
            if (activityList.get(i).tags != null) {
                System.out.println(" " + Arrays.toString(activityList.get(i).tags));
            } else {
                System.out.print("\n");
            }
        }
        System.out.println(DIVIDER);
    }
}
