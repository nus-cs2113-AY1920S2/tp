package seedu.duke.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class converts the String time and date entered by the user into the String of the desired format.
 *
 * @author Samantha Goh
 */
public class TimeConverter {

    public static final int MAXLENGTH = 4;

    /**
     * This method converts the old date into a new date format.
     *
     * @param oldDate the date entered by the user.
     * @return newDate the date in the desired format.
     * @throws ParseException the error occurs if the date is not found.
     */
    public static String oldDate(String oldDate) throws ParseException {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat1.setLenient(false);
        Date date = simpleDateFormat1.parse(oldDate);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE dd MMM yyyy");
        simpleDateFormat2.setLenient(false);
        String newDate = simpleDateFormat2.format(date);
        return newDate;
    }

    /**
     * This method converts the old time into a new time format.
     *
     * @param oldTime the time entered by the user.
     * @return newTime the time in the desired format.
     * @throws ParseException the error occurs if the time is not found.
     */
    public static String oldTime(String oldTime) throws ParseException {
        if (oldTime.length() > MAXLENGTH) {
            throw new ParseException("", 1);
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HHmm");
        simpleDateFormat1.setLenient(false);
        Date time = simpleDateFormat1.parse(oldTime);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm a");
        simpleDateFormat2.setLenient(false);
        String newTime = simpleDateFormat2.format(time);
        return newTime;
    }

    /**
     * This method converts the date back into the format of dd/mm/yyyy.
     *
     * @param oldDate the time entered by the user.
     * @return convertedDate the time in the desired format.
     * @throws ParseException the error occurs if the time is not found.
     */
    public static String convertDate(String oldDate) throws ParseException {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEE dd MMM yyyy");
        simpleDateFormat1.setLenient(false);
        Date date = simpleDateFormat1.parse(oldDate);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        String convertedDate = simpleDateFormat2.format(date);
        return convertedDate;
    }

    /**
     * This method converts the old time back into the format of hh:mm.
     *
     * @param oldTime the time entered by the user.
     * @return convertedTime the time in the desired format.
     * @throws ParseException the error occurs if the time is not found.
     */
    public static String convertTime(String oldTime) throws ParseException {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a");
        simpleDateFormat1.setLenient(false);
        Date time = simpleDateFormat1.parse(oldTime);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmm");
        simpleDateFormat2.setLenient(false);
        String convertedTime = simpleDateFormat2.format(time);
        return convertedTime;
    }
}
