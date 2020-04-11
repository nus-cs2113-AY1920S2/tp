package seedu.duke.data;

import seedu.duke.exceptions.InvalidFormatException;

//@@author thanhduc2000
/**
 * Class to validate age.
 */
public class Age {

    public static final String MESSAGE_NAME_CONSTRAINTS = "The format of age is positive integer within 0 and 150";
    public static final int AGE_LOWER_BOUND = 0;
    public static final int AGE_UPPER_BOUND = 150;
    public static final int DEFAULT_INVALID_AGE = -1;

    public int age;

    /**
     * Constructor for Age.
     *
     * @param age getting from Parser
     * @throws InvalidFormatException when age is invalid
     */
    public Age(String age) throws InvalidFormatException {
        boolean isAgeEqualNull = age.isBlank();
        if (isAgeEqualNull) {
            this.age = DEFAULT_INVALID_AGE;
        } else {
            try {
                this.age = Integer.parseInt(age);
            } catch (NumberFormatException e) {
                this.age = DEFAULT_INVALID_AGE;
            }
        }
        if (!isValidAge(this.age) && !isAgeEqualNull) {
            throw new InvalidFormatException(MESSAGE_NAME_CONSTRAINTS);
        }
    }

    /**
     * Method to check the validation of patient's age.
     *
     * @param age getting from Parser
     * @return true if age is within the bound, false otherwise
     */
    public static boolean isValidAge(int age) {
        return age >= AGE_LOWER_BOUND && age <= AGE_UPPER_BOUND;
    }

    public int getAge() {
        return age;
    }
}
