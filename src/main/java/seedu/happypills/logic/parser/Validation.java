package seedu.happypills.logic.parser;

public class Validation {

    /**
     * To check format for phone.
     *
     * @param s string to be validated
     * @return boolean true if the time format is correct otherwise false
     */
    static boolean isValidPhoneNum(String s) {
        String pattern = "^[0-9]{9}$";
        return s.matches(pattern);
    }

    /**
     * check string if fits date format. Only allows for year 2000 and beyond.
     * @param s string to be validated
     * @return true if correct date format, false otherwise
     */
    static boolean isValidDate(String s) {
        String pattern = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([2-9][0-9]{3})";
        return s.matches(pattern);
    }

    /**
     * check string if fits time format. (HH:MM)
     * @param s string to be validated
     * @return true if correct date format, false otherwise.
     */
    static boolean isValidTime(String s) {
        String pattern = "([01][0-9]|2[0-3]):([0-5][0-9])";
        return s.matches(pattern);
    }

    /**
     * Check string if is valid blood type.
     * @param s String to be validated
     * @return true if is valid, false otherwise.
     */
    static boolean isvalidBloodType(String s) {
        String pattern = "([A|B|AB|O][+-])";
        return s.matches(pattern);
    }

    /**
     * Check string if is numeric
     * @param s String to be validated
     * @return true if is numeric, false otherwise.
     */
    static boolean isNumeric(String s) {
        String pattern = "^[0-9]*$";
        return  s.matches(pattern);
    }

    /**
     * Check string if is valid NRIC
     * @param s String to be validated
     * @return true if is valid, false otherwise.
     */
    static boolean isNric(String s) {
        String pattern = "(?i)^[STFG]\\d{7}[A-Z]$";
        return  s.matches(pattern);
    }

}
