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
     * Return help command instructions.
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @return message The command instructions.
     */
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecordMap) {
        String message;
        String[] userCommand = command.split(" ", 3);
        if (userCommand[0].equalsIgnoreCase("help") && userCommand.length == 1) {
            message = HelpTextUi.generalHelpMessage;
        } else if (userCommand[1].equalsIgnoreCase("exit")) {
            message = HelpTextUi.exitHelpMessage;
        } else if (userCommand[2].equalsIgnoreCase("patient")) {
            message = getPatientHelp(userCommand[1]);
        } else if (userCommand[2].equalsIgnoreCase("pr")) {
            message = getPatientRecordHelp(userCommand[1]);
        } else if (userCommand[2].equalsIgnoreCase("appt")) {
            message = getAppointmentHelp(userCommand[1]);
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message;
    }

    private String getPatientHelp(String patient) {
        String message = "";
        if (patient.equalsIgnoreCase("add")) {
            message = HelpTextUi.AddPatientHelpMessage;
        } else if (patient.equalsIgnoreCase("list")) {
            message = HelpTextUi.listPatientHelpMessage;
        } else if (patient.equalsIgnoreCase("get")) {
            message = HelpTextUi.getPatientHelpMessage;
        } else if (patient.equalsIgnoreCase("edit")) {
            message = HelpTextUi.editPatientHelpMessage;
        } else if (patient.equalsIgnoreCase("delete")) {
            message = HelpTextUi.deletePatientHelpMessage;
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message + TextUi.DIVIDER;
    }

    private String getPatientRecordHelp(String pr) {
        String message = "";
        if (pr.equalsIgnoreCase("add")) {
            message = HelpTextUi.AddPatientRecordHelpMessage;
        } else if (pr.equalsIgnoreCase("list")) {
            message = HelpTextUi.listPatientRecordHelpMessage;
        } else if (pr.equalsIgnoreCase("find")) {
            message = HelpTextUi.findPatientRecordHelpMessage;
        } else if (pr.equalsIgnoreCase("edit")) {
            message = HelpTextUi.editPatientRecordHelpMessage;
        } else if (pr.equalsIgnoreCase("delete")) {
            message = HelpTextUi.deletePatientRecordHelpMessage;
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message + TextUi.DIVIDER;
    }

    private String getAppointmentHelp(String appt) {
        String message = "";
        if (appt.equalsIgnoreCase("add")) {
            message = HelpTextUi.printAddAppointmentHelp;
        } else if (appt.equalsIgnoreCase("list")) {
            message = HelpTextUi.printListAppointmentHelp;
        } else if (appt.equalsIgnoreCase("find")) {
            message = HelpTextUi.printFindAppointmentHelp;
        } else if (appt.equalsIgnoreCase("done")) {
            message = HelpTextUi.printDoneAppointmentHelp;
        } else if (appt.equalsIgnoreCase("edit")) {
            message = HelpTextUi.printEditAppointmentHelp;
        } else if (appt.equalsIgnoreCase("delete")) {
            message = HelpTextUi.printDeleteAppointmentHelp;
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message + TextUi.DIVIDER;
    }
}

