package seedu.duke.data;

import seedu.duke.exceptions.InvalidFormatException;

//@@author thanhduc2000
/**
 * Used to validate the format of address.
 */
public class Address {

    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Patient's addresses is limited to 64 characters "
            + "including of whitespaces";
    public static final int ADDRESS_STRING_LIMIT = 64;

    public String address;

    /**
     * Constructor for address.
     *
     * @param address address from the hash map created in Parser
     * @throws InvalidFormatException when the address is invalid
     */
    public Address(String address) throws InvalidFormatException {
        if (!isValidAddress(address)) {
            throw new InvalidFormatException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.address = address;
    }

    /**
     * Method to verify whether the address is validated.
     *
     * @param address getting from Parser
     * @return true if the length of address is within the limit or no modification, false otherwise
     */
    private static boolean isValidAddress(String address) {
        return address.length() <= ADDRESS_STRING_LIMIT || address.isBlank();
    }

    @Override
    public String toString() {
        return address;
    }
}
