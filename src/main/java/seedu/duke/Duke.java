package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Command;
import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModuleList;
import seedu.duke.data.SemModuleList;
import seedu.duke.module.Module;
import seedu.duke.parser.Parser;


public class Duke {

    private static SelectedModuleList moduleList = new SelectedModuleList();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine();
        while (!fullCommand.equals("bye")) {
            Command command = Parser.parse(fullCommand);
            command.execute(moduleList);
//            for(SemModuleList sem: moduleList){
//                for(Module module: sem){
//                    System.out.println(module);
//                }
//            }
            fullCommand = in.nextLine();
        }
        System.out.println(System.lineSeparator() + "bye!");
    }
}
