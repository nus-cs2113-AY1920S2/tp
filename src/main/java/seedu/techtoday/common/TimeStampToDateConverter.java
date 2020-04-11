package seedu.techtoday.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/** Class representing a method used to convert timestamp to date. */
public class TimeStampToDateConverter {

    /**
     * This method sets a date for an article.
     *
     * @param timeStamp A string representing the timestamp of the article
     */
    public static String execute(String timeStamp) {
        if (timeStamp.isEmpty()) {
            return timeStamp;
        } else {
            String pattern = "yyyy-MMM-dd E hh:mm a";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            int intTimeStamp = Integer.parseInt(timeStamp);
            String date = simpleDateFormat.format((new Date((long) intTimeStamp * 1000)));
            return date;
        }
    }

}
