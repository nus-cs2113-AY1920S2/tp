package seedu.happypills;

import seedu.happypills.commands.AddCommand;
import seedu.happypills.commands.RetrieveCommand;
import seedu.happypills.commands.HelpCommand;
import seedu.happypills.commands.ListCommand;
import seedu.happypills.commands.EditCommand;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.parser.Parser;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class HappyPills {
    private TextUi ui;
    private PatientList patients;

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public HappyPills() {
        ui = new TextUi();
        patients = new PatientList();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new HappyPills().run();
    }

    /**
     * Runs the program until termination.
     */
    private void run() {
        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String fullCommand = in.nextLine();
            System.out.println(ui.DIVIDER);
            Command c = parse(fullCommand);
            c.execute(patients);
        }
    }

    /**
     * Parses user input.
     */
    private Command parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split(" ");

        if (userCommand[0].equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userCommand[0].equalsIgnoreCase("add")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new HappyPillsException("length is empty");
            }
            String[] patientDetail = userCommand[1].split(",");
            return new AddCommand(patientDetail[0], patientDetail[1],
                    Integer.parseInt(patientDetail[2]), patientDetail[3],
                    patientDetail[4], patientDetail[5], patientDetail[6]);

        } else if (userCommand[0].equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (userCommand[0].equalsIgnoreCase("get")) {
            return new RetrieveCommand(userCommand[1]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return new EditCommand(userCommand[1], userCommand[2], userCommand[3]);
        } else {
            throw new HappyPillsException("Invalid Command");
            try {
                String fullCommand = in.nextLine();
                System.out.println(ui.DIVIDER);
                Command c = Parser.parse(fullCommand);
                c.execute(patients);
            } catch (HappyPillsException hpe) {
                System.out.println(hpe.getMessage());
                System.out.println(ui.DIVIDER);
            }
        }
    }
}
