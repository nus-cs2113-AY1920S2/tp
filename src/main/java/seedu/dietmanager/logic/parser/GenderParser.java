package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidGenderException;

public class GenderParser {

    private static final String MALE = "male";
    private static final String FEMALE = "female";

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidGenderException If gender is not recognized by our system.
     */

    public static String parseGender(String description) throws InvalidGenderException {
        String gender = description.trim().toLowerCase();
        if (!(gender.equals(MALE) || gender.equals(FEMALE))) {
            throw new InvalidGenderException();
        }
        testAssertions(gender);
        return gender;
    }

    public static void testAssertions(String gender) {
        assert ((gender.equals(MALE)) || (gender.equals(FEMALE)));
    }

}
