package seedu.happypills.logic.commands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
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
        System.out.println(command);
        if (userCommand[0].equalsIgnoreCase("help") && userCommand.length == 1) {
            message = TextUi.getHelp();
        } else if (userCommand[1].equalsIgnoreCase("exit")) {
            message = TextUi.printExitHelp();
        } else if (userCommand[2].equalsIgnoreCase("patient")) {
            message = getPatientHelp(userCommand[1]);
        } else if (userCommand[2].equalsIgnoreCase("pr")) {
            message = getVisitHelp(userCommand[1]);
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
            message = TextUi.printAddPatientHelp();
        } else if (patient.equalsIgnoreCase("list")) {
            message = TextUi.printListPatientHelp();
        } else if (patient.equalsIgnoreCase("get")) {
            message = TextUi.printGetPatientHelp();
        } else if (patient.equalsIgnoreCase("edit")) {
            message = TextUi.printEditPatientHelp();
        } else if (patient.equalsIgnoreCase("delete")) {
            message = TextUi.printDeletePatientHelp();
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message;
    }

    private String getVisitHelp(String pr) {
        String message = "";
        if (pr.equalsIgnoreCase("add")) {
            message = TextUi.printAddPatientHelp();
        } else if (pr.equalsIgnoreCase("list")) {
            message = TextUi.printListPatientHelp();
        } else if (pr.equalsIgnoreCase("find")) {
            message = TextUi.printGetPatientHelp();
        } else if (pr.equalsIgnoreCase("edit")) {
            message = TextUi.printEditPatientHelp();
        } else if (pr.equalsIgnoreCase("delete")) {
            message = TextUi.printDeletePatientHelp();
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message;
    }

    private String getAppointmentHelp(String appt) {
        String message = "";
        if (appt.equalsIgnoreCase("add")) {
            message = TextUi.printAddAppointmentHelp();
        } else if (appt.equalsIgnoreCase("list")) {
            message = TextUi.printListAppointmentHelp();
        } else if (appt.equalsIgnoreCase("find")) {
            message = TextUi.printFindAppointmentHelp();
        } else if (appt.equalsIgnoreCase("done")) {
            message = TextUi.printDoneAppointmentHelp();
        } else if (appt.equalsIgnoreCase("edit")) {
            message = TextUi.printEditAppointmentHelp();
        } else if (appt.equalsIgnoreCase("delete")) {
            message = TextUi.printDeleteAppointmentHelp();
        } else {
            message = TextUi.printIncorrectCommand(command);
        }
        return message;
    }
}

