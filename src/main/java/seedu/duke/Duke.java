package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.data.ModuleList;
import seedu.duke.module.Module;
import seedu.duke.parser.Parser;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Duke {

    private static ModuleList moduleList = new ModuleList();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        Command command = Parser.parse(in.nextLine());
        command.execute(moduleList);
    }
}
