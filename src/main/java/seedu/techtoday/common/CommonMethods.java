package seedu.techtoday.common;

/** Class containing methods that can be used across classes in the application. */
public class CommonMethods {

    /**
     * Retutns a Boolean value to see if s is an integer or not.
     *
     * @param longer - String that can possibly be turned into an long type.
     * @return - Boolean value isValidInteger.
     */
    public static boolean checkIsLong(String longer) {
        boolean isValidInt = false;
        try {
            Long.parseLong(longer);
            isValidInt = true;
        } catch (NumberFormatException ex) {
            // Case where a string representing an interger isn't fed as longer.
        }
        return isValidInt;
    }

    /**
     * Returns upto the first 100 characters of a sting.
     *
     * @param string - String that is to be formatted.
     * @return - String that is reduced in size.
     */
    public static String returnUptoNcharacters(String string) {
        String upToNCharacters;
        if (string.length() <= 100) {
            upToNCharacters = string;
        } else {
            upToNCharacters = string.substring(0, Math.min(string.length(), 100)) + "...";
        }
        return upToNCharacters;
    }
}
