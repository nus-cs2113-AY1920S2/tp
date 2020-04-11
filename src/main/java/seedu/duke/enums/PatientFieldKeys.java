package seedu.duke.enums;

/**
 * This enum reflects the changeable fields for the Patient Class.
 *
 * @author Justin.
 * @see seedu.duke.record.Patient
 */
public enum PatientFieldKeys {

    INDEX,
    NAME,
    AGE,
    ADDRESS,
    CONTACT_NUMBER;

    /**
     * This toString method overrides the default method with a specified field key
     * and returns a string when called.
     */
    @Override
    public String toString() {
        switch (this) {
        case INDEX:
            return "index";
        case NAME:
            return "name";
        case AGE:
            return "age";
        case ADDRESS:
            return "address";
        case CONTACT_NUMBER:
            return "phone";
        default:
            return null;
        }
    }
}
