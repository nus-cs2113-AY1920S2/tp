package seedu.happypills.ui;

import static seedu.happypills.ui.TextUi.DIVIDER;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "    Command is invalid. "
            + "Enter help to know how to use HappyPills.";
    public static final String MESSAGE_INCOMPLETE_COMMAND = "    Command is incomplete. Please use the help command.\n"
            + DIVIDER;
    public static final String MESSAGE_PATIENT_NOT_FOUND = "    Patient does not exist. Please try again.\n"
            + DIVIDER;
    public static final String MESSAGE_PATIENT_RECORD_ADDED = "    Patient Record has been added.\n"
            + DIVIDER;
    public static final String MESSAGE_PATIENT_RECORD_MISSING_FIELD =
            "    Please input all necessary fields for the edit command.";
    public static final String MESSAGE_INVALID_INDEX = "    Invalid index.";
    public static final String MESSAGE_INVALID_NRIC = "    Invalid NRIC format.";
    public static final String MESSAGE_NRIC_NOT_PROVIDED = "    NRIC of the patient not provided";
    public static final String MESSAGE_PATIENT_RECORD_DETAILS_NOT_PROVIDED =
            "    Patient's record details are not provided.";
    public static final String MESSAGE_INFORM_MISSING = "    Please input your missing detail listed below";
    public static final String MESSAGE_NRIC_FORMAT = "    /ic NRIC (Format: [S/T][7-digits][A-Z])";
    public static final String MESSAGE_SYMPTOM_FORMAT = "    /sym SYMPTOMS";
    public static final String MESSAGE_DIAGNOSIS_FORMAT = "    /diag DIAGNOSIS";
    public static final String MESSAGE_DATE_FORMAT = "    /d DATE  (Format: DD/MM/YYYY)";
    public static final String MESSAGE_TIME_FORMAT = "    /t TIME (Format: HH:mm)";
    public static final String MESSAGE_COMMAND_ABORTED = "    Command has been aborted.";
    public static final String MESSAGE_PATIENT_RECORD_NOT_ADDED = "    The current information is not added.\n"
            + "    Please add all the details again! Thank you.\n"
            + DIVIDER;
    public static final String MESSAGE_USER_CONFIRMATION = "    Please input [y] for yes or [n] for no";
    public static final String MESSAGE_PATIENT_RECORD_NOT_FOUND = "    Patient record not found. Please try again.";
    public static final String MESSAGE_EDIT_ERROR = "    Something went wrong, the edit could not be made.";
    public static final String MESSAGE_INVALID_DATE = "    Invalid date or date format(DD/MM/YYYY).\n";
    public static final String MESSAGE_INVALID_DATE_OF_BIRTH = "    Please ensure that the DATE is in DD/MM/YYYY ";
    public static final String MESSAGE_INVALID_TIME = "    Invalid time or time format(HH:MM).\n";
    public static final String MESSAGE_INVALID_PHONE_NUMBER = "    Please ensure that all the phone number is 8 digit";
    public static final String MESSAGE_INVALID_TAG =
            "    Please try again. To learn more about the Edit appointment command, "
                    + "\n    enter \"help appt edit\"";

    public static final String MESSAGE_EMPTY_PATIENT = "    There are no patients in the list.\n" + DIVIDER;
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "    No record found with given Index";
    public static final String MESSAGE_PATIENT_IS_IN_THE_MAP =
            "    Patient is already in the list. Please use help command.";
    public static final String MESSAGE_CONTENT_IS_EMPTY = "    Please do not leave the field as empty string";
}

