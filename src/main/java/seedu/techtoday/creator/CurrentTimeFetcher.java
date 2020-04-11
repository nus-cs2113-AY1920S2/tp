package seedu.techtoday.creator;

import java.time.Instant;

/** Class representing the method used to fetch the current time. */
public class CurrentTimeFetcher {
    /**
     * Retutns the timestamp associated with the current time.
     *
     * @return - Current timestamp (epochSecond).
     */
    public static String execute() {
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        String epochSecond = Long.toString(timeStampSeconds);
        return epochSecond;
    }
}
