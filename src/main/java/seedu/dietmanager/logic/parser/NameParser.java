package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidNameException;

public class NameParser {

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidNameException If input is not a valid height value.
     */

    public static String parseName(String description) throws InvalidNameException {
        String name = "";
        name = description.trim();
        if (name.equals("")) {
            throw new InvalidNameException();
        }
        testAssertions(name);
        return name;
    }

    public static void testAssertions(String name) {
        assert (!(name.equals("")));
    }

}
