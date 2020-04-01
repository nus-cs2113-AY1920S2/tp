package seedu.happypills.ui;

import seedu.happypills.model.data.Appointment;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecord;
import seedu.happypills.model.data.PatientRecordMap;

import java.util.ArrayList;
import java.util.Map;

public class TextUi {
    public static final String DIVIDER = "    =====================================================";

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static void printWelcomeMessage() {
        String logo = "\n"
                + " __   __  _______  _______  _______  __   __  _______  ___   ___      ___      _______\n"
                + "|  | |  ||   _   ||       ||       ||  | |  ||       ||   | |   |    |   |    |       |\n"
                + "|  |_|  ||  |_|  ||    _  ||    _  ||  |_|  ||    _  ||   | |   |    |   |    |  _____|\n"
                + "|       ||       ||   |_| ||   |_| ||       ||   |_| ||   | |   |    |   |    | |_____\n"
                + "|       ||       ||    ___||    ___||_     _||    ___||   | |   |___ |   |___ |_____  |\n"
                + "|   _   ||   _   ||   |    |   |      |   |  |   |    |   | |       ||       | _____| |\n"
                + "|__| |__||__| |__||___|    |___|      |___|  |___|    |___| |_______||_______||_______|\n";

        System.out.println("Hello from" + logo);
        System.out.println("What can I do for you today, doctor?");
    }

    /**
     * Displays Exit message.
     */
    public static void printExit() {
        System.out.println("Thank you for using HappyPills! ^.^");
        System.out.println("See you again!\n" + DIVIDER);
    }

    //General methods
    /**
     * print any string as needed.
     * @param string print any string to screen
     */
    public static void print(String string) {
        System.out.println(string);
    }

    /**
     * Append the divider to the given message.
     *
     * @param s The message that requires the divider to be appended.
     * @return The message with the appended divider.
     */
    public static String appendDivider(String s) {
        return s + DIVIDER;
    }

    /**
     * Prepend the divider to the given message.
     *
     * @param s The message that requires the divider to be prepended.
     * @return The message with the prepended divider.
     */
    public static String prependDivider(String s) {
        return DIVIDER + "\n" + s;
    }


    public static String printIncorrectCommand(String command) {
        return "    The command \"" + command + "\" does not exist. Please try again.\n";
    }

    /**
     * Construct a string for incomplete commands, probably missing something.
     * @param helpString provide the help command that user can use to find the right format
     * @return a string for incomplete commands.
     */
    public  static String incompleteCommandString(String helpString) {
        String msg = "    Command is incomplete. Please use the " + helpString + " command.";
        return msg;
    }
}
