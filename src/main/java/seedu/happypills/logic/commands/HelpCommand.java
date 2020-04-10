package seedu.happypills.logic.commands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.ui.HelpTextUi;
import seedu.happypills.ui.TextUi;

/**
 * Displays full help instructions for every command.
 */
public class HelpCommand implements Command {
    protected String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Returns help command instructions.
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @return message The command instructions.
     */
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap) {
        String message;
        String[] userCommand = command.split("\\s+", 3);
        if (userCommand[0].equalsIgnoreCase("help") && userCommand.length == 1) {
            message = HelpTextUi.GENERAL_HELP_MESSAGE;
        } else if (userCommand[1].equalsIgnoreCase("exit")) {
            message = HelpTextUi.EXIT_HELP_MESSAGE;
        } else if (userCommand[2].equalsIgnoreCase("patient")) {
            message = getPatientHelp(userCommand[1]);
        } else if (userCommand[2].equalsIgnoreCase("pr")) {
            message = getPatientRecordHelp(userCommand[1]);
        } else if (userCommand[2].equalsIgnoreCase("appt")) {
            message = getAppointmentHelp(userCommand[1]);
        } else {
            message = TextUi.incorrectCommandMessage(userCommand[1] + " " + userCommand[2]);
        }
        return message + TextUi.DIVIDER;
    }

    private String getPatientHelp(String patient) {
        String message = "";
        if (patient.equalsIgnoreCase("add")) {
            message = HelpTextUi.ADD_PATIENT_HELP_MESSAGE;
        } else if (patient.equalsIgnoreCase("list")) {
            message = HelpTextUi.LIST_PATIENT_HELP_MESSAGE;
        } else if (patient.equalsIgnoreCase("get")) {
            message = HelpTextUi.GET_PATIENT_HELP_MESSAGE;
        } else if (patient.equalsIgnoreCase("edit")) {
            message = HelpTextUi.EDIT_PATIENT_HELP_MESSAGE;
        } else if (patient.equalsIgnoreCase("delete")) {
            message = HelpTextUi.DELETE_PATIENT_HELP_MESSAGE;
        } else {
            message = HelpTextUi.GENERAL_PATIENT_HELP_MESSAGE;
        }
        return message;
    }

    private String getPatientRecordHelp(String pr) {
        String message = "";
        if (pr.equalsIgnoreCase("add")) {
            message = HelpTextUi.ADD_PATIENT_RECORD_HELP_MESSAGE;
        } else if (pr.equalsIgnoreCase("list")) {
            message = HelpTextUi.LIST_PATIENT_RECORD_HELP_MESSAGE;
        } else if (pr.equalsIgnoreCase("find")) {
            message = HelpTextUi.FIND_PATIENT_RECORD_HELP_MESSAGE;
        } else if (pr.equalsIgnoreCase("edit")) {
            message = HelpTextUi.EDIT_PATIENT_RECORD_HELP_MESSAGE;
        } else if (pr.equalsIgnoreCase("delete")) {
            message = HelpTextUi.DELETE_PATIENT_RECORD_HELP_MESSAGE;
        } else {
            message = HelpTextUi.GENERAL_PATIENT_RECORD_HELP_MESSAGE;
        }
        return message;
    }

    private String getAppointmentHelp(String appt) {
        String message = "";
        if (appt.equalsIgnoreCase("add")) {
            message = HelpTextUi.ADD_APPOINTMENT_HELP_MESSAGE;
        } else if (appt.equalsIgnoreCase("list")) {
            message = HelpTextUi.LIST_APPOINTMENT_HELP_MESSAGE;
        } else if (appt.equalsIgnoreCase("find")) {
            message = HelpTextUi.FIND_APPOINTMENT_HELP_MESSAGE;
        } else if (appt.equalsIgnoreCase("done")) {
            message = HelpTextUi.DONE_APPOINTMENT_HELP_MESSAGE;
        } else if (appt.equalsIgnoreCase("edit")) {
            message = HelpTextUi.EDIT_APPOINTMENT_HELP_MESSAGE;
        } else if (appt.equalsIgnoreCase("delete")) {
            message = HelpTextUi.DELETE_APPOINTMENT_HELP_MESSAGE;
        } else {
            message = HelpTextUi.GENERAL_APPOINTMENT_HELP_MESSAGE;
        }
        return message;
    }
}

