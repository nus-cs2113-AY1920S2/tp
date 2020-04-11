package seedu.duke.enums;


/**
 * This enum reflects the changeable fields for the Appointment class under the package "record".
 *
 * @author Justin.
 * @see seedu.duke.record.Appointment
 */
public enum AppointmentFieldKeys {
    INDEX,
    DATE,
    TIME,
    PATIENT_ID;

    @Override
    public String toString() {
        switch (this) {
        case INDEX:
            return "index";
        case DATE:
            return "date";
        case TIME:
            return "time";
        case PATIENT_ID:
            return "pid";
        default:
            return null;
        }
    }
}
