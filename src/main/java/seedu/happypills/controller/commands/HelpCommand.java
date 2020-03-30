package seedu.happypills.controller.commands;

import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.VisitMap;
import seedu.happypills.view.ui.TextUi;

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
    public String execute(PatientMap patients, AppointmentMap appointments, VisitMap visits) {
        String message;
        String[] userCommand = command.split(" ", 2);
        if (command.equals("")) {
            message = TextUi.getHelp();
        } else if (userCommand[0].equalsIgnoreCase("help")) {
            message = TextUi.printHelpHelp();
        } else if (userCommand[0].equalsIgnoreCase("exit")) {
            message = TextUi.printExitHelp();
        } else if (userCommand[1].equalsIgnoreCase("patient")) {
            message = getPatientHelp(userCommand[0]);
        } else if (command.equalsIgnoreCase("visit")) {
            message = getVisitHelp(userCommand[0]);
        } else if (command.equalsIgnoreCase("appt")) {
            message = getAppointmentHelp(userCommand[0]);
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

    private String getVisitHelp(String visit) {
        String message = "";
        if (visit.equalsIgnoreCase("add")) {
            message = TextUi.printAddPatientHelp();
        } else if (visit.equalsIgnoreCase("list")) {
            message = TextUi.printListPatientHelp();
        } else if (visit.equalsIgnoreCase("find")) {
            message = TextUi.printGetPatientHelp();
        } else if (visit.equalsIgnoreCase("edit")) {
            message = TextUi.printEditPatientHelp();
        } else if (visit.equalsIgnoreCase("done")) {
            message = TextUi.printEditPatientHelp();
        } else if (visit.equalsIgnoreCase("delete")) {
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
