package seedu.happypills.logic.parser;

public class Checker {

    /**
     * Checks the format for phone number.
     *
     * @param phoneNumber String to be validated.
     * @return Boolean true if the time format is correct otherwise false.
     */
    public static boolean isValidPhoneNum(String phoneNumber) {
        String pattern = "([8-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])";
        return phoneNumber.matches(pattern);
    }

    /**
     * Checks string if fits date format. Only allows for year 2000 and beyond.
     *
     * @param date Date to be validated.
     * @return True if correct date format, false otherwise.
     */
    public static boolean isValidDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean isDate = false;
        if (date.matches(pattern)) {
            isDate = true;
        }
        return isDate;
    }

    /**
     * Checks string if fits time format (HH:MM).
     *
     * @param time Time to be validated.
     * @return True if correct date format, false otherwise.
     */
    public static boolean isValidTime(String time) {
        String pattern = "([0-1][0-9]|2[0-3]):([0-5][0-9])";
        return time.matches(pattern);
    }

    /**
     * Checks string if is valid blood type.
     *
     * @param blood Blood type to be validated.
     * @return True if is valid, false otherwise.
     */
    public static boolean isValidBloodType(String blood) {
        String pattern = "(A|B|AB|O)(\\+|-)";
        return blood.matches(pattern);
    }

    /**
     * Checks string if is numeric.
     *
     * @param number Number to be validated.
     * @return True if is numeric, false otherwise.
     */
    public static boolean isNumeric(String number) {
        String pattern = "^[0-9]*$";
        return number.matches(pattern);
    }

    /**
     * Checks string if is valid NRIC.
     *
     * @param nric Nric to be validated.
     * @return True if is valid, false otherwise.
     */
    public static boolean isValidNric(String nric) {
        String pattern = "(?i)^[STFG]\\d{7}[A-Z]$";
        return nric.matches(pattern);
    }

    /**
     * Checks if the String can be converted to Integer.
     *
     * @param input Value to check if is positive integer.
     * @return True if is an integer, false otherwise.
     */
    public static boolean isPositiveInteger(String input) {
        try {
            int x = Integer.parseInt(input);
            return x >= 1;
        } catch (Exception e) {
            return false;
        }
    }
}
