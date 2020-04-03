package seedu.happypills.logic.parser;

public class Validation {

    /**
     * To check format for phone.
     *
     * @param s string to be validated
     * @return boolean true if the time format is correct otherwise false
     */
    public static boolean isValidPhoneNum(String s) {
        String pattern = "^[0-9]{9}$";
        return s.matches(pattern);
    }

    /**
     * check string if fits date format. Only allows for year 2000 and beyond.
     * @param date string to be validated
     * @return true if correct date format, false otherwise
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
     * check string if fits time format. (HH:MM)
     * @param time string to be validated
     * @return true if correct date format, false otherwise.
     */
    public static boolean isValidTime(String time) {
        String pattern = "([01][0-9]|2[0-3]):([0-5][0-9])";
        return time.matches(pattern);
    }

    /**
     * Check string if is valid blood type.
     * @param s String to be validated
     * @return true if is valid, false otherwise.
     */
    public static boolean isValidBloodType(String s) {
        String pattern = "([A|B|AB|O][+-])";
        return s.matches(pattern);
    }

    /**
     * Check string if is numeric.
     * @param s String to be validated
     * @return true if is numeric, false otherwise.
     */
    public static boolean isNumeric(String s) {
        String pattern = "^[0-9]*$";
        return  s.matches(pattern);
    }

    /**
     * Check string if is valid NRIC.
     * @param s String to be validated
     * @return true if is valid, false otherwise.
     */
    public static boolean isValidNric(String s) {
        String pattern = "(?i)^[STFG]\\d{7}[A-Z]$";
        return  s.matches(pattern);
    }

    /**
     * Check if the String can be converted to Integer.
     *
     * @param input value to check if is integer
     * @return true if is an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        try {
            int x = Integer.parseInt(input);
            if (x < 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
