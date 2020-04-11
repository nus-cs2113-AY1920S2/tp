package seedu.happypills.ui;

import static seedu.happypills.ui.TextUi.DIVIDER;

//@@ itskesin
/**
 * Contains user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "    Command is invalid. "
            + "Enter help to know how to use HappyPills.";
    public static final String MESSAGE_INCOMPLETE_COMMAND = "    Command is incomplete. Please use the help command.";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "    Patient does not exist. Please try again.";
    public static final String MESSAGE_PATIENT_RECORD_ADDED = "    Patient Record has been added.\n"
            + DIVIDER;
    public static final String MESSAGE_MISSING_FIELD =
            "    Please input all necessary fields for the edit command.";
    public static final String MESSAGE_INVALID_INDEX = "    Invalid index.";
    public static final String MESSAGE_INVALID_NRIC = "    Invalid NRIC format.";
    public static final String MESSAGE_NRIC_NOT_PROVIDED = "    NRIC of the patient not provided";
    public static final String MESSAGE_PATIENT_RECORD_DETAILS_NOT_PROVIDED =
            "    Patient's record details are not provided.";
    public static final String MESSAGE_PATIENT_DETAILS_NOT_PROVIDED = "    Patient's detail is empty.";
    public static final String MESSAGE_INFORM_MISSING = "    Please input your missing detail listed below";
    public static final String MESSAGE_NRIC_FORMAT = "      /ic NRIC (Format: [S/T][7-digits][A-Z])";
    public static final String MESSAGE_SYMPTOM_FORMAT = "      /sym SYMPTOMS";
    public static final String MESSAGE_DIAGNOSIS_FORMAT = "      /diag DIAGNOSIS";
    public static final String MESSAGE_DATE_FORMAT = "      /d DATE (Format: DD/MM/YYYY)";
    public static final String MESSAGE_TIME_FORMAT = "      /t TIME (Format: HH:mm)";
    public static final String MESSAGE_COMMAND_ABORTED = DIVIDER
            + "\n    Command has been aborted.";
    public static final String MESSAGE_PATIENT_RECORD_NOT_ADDED = "    The current information is not added.\n"
            + "    Please add all the details again! Thank you.";
    public static final String MESSAGE_USER_CONFIRMATION = "    Please enter [y] for yes or [n] for no";
    public static final String MESSAGE_PATIENT_RECORD_NOT_FOUND = "    Patient record not found. Please try again.";
    public static final String MESSAGE_EDIT_ERROR = "    Something went wrong, the edit could not be made.";
    public static final String MESSAGE_INVALID_DATE = "    Invalid date or date format(DD/MM/YYYY).\n";
    public static final String MESSAGE_INVALID_DATE_OF_BIRTH = "    Please ensure that the DATE is in DD/MM/YYYY ";
    public static final String MESSAGE_INVALID_BLOOD_TYPE =
            "    Please ensure that the BLOOD TYPE is in [A|B|AB|O][+-] ";
    public static final String MESSAGE_INVALID_TIME = "    Invalid time or time format(HH:MM).\n";
    public static final String MESSAGE_INVALID_PHONE_NUMBER = "    Please ensure that all the phone number is 8 digit";
    public static final String MESSAGE_INVALID_EDIT_APPOINTMENT =
            "    Please try again. To learn more about the Edit appointment command, "
                    + "\n    enter \"help edit appt\"";
    public static final String MESSAGE_INVALID_EDIT_PR =
            "    Please try again. To learn more about the Edit patient record command, "
                    + "\n    enter \"help edit pr\"";
    public static final String MESSAGE_INVALID_EDIT_PATIENT =
            "    Please try again. To learn more about the Edit patient command, "
                    + "\n    enter \"help edit patient\"";

    public static final String MESSAGE_EMPTY_PATIENT = "    There is no patient in the list.\n" + DIVIDER;
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "    No record found with given index";
    public static final String MESSAGE_PATIENT_IS_IN_THE_MAP =
            "    Patient is already in the list. Please use help command.";
    public static final String MESSAGE_CONTENT_IS_EMPTY = "    Please do not leave the field as empty string";
    public static final String MESSAGE_NAME_FORMAT = "      /n NAME";
    public static final String MESSAGE_PHONE_NUMBER_FORMAT =
            "      /p PHONE (Format: 8-digit number starting with 8/9)";
    public static final String MESSAGE_BLOOD_TYPE_FORMAT = "      /b BLOOD TYPE (Format: A/B/O/AB with +/-)";
    public static final String MESSAGE_INCORRECT_INPUT_FORMAT = "    Incorrect input format. Sorry please try again";
    public static final String MESSAGE_DATE_OF_BIRTH_FORMAT = "      /dob DATE OF BIRTH (Format: DD/MM/YYYY)";
    public static final String MESSAGE_CLEAR_COMMAND = "\n    To abort, enter \"clear\"";
    public static final String MESSAGE_EMPTY_PATIENT_RECORD = "    There is no patient record in the list.";
}

