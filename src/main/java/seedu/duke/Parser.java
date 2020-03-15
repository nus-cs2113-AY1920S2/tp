package seedu.duke;

import java.util.Scanner;

public class Parser {
    private static Scanner in = new Scanner(System.in);

    public Command getCommand() {
        String input = in.nextLine();
        return convertToCommand(input);
    }

    /**
     * Deal with original input and convert to a command.
     * @param input user's original input.
     * @return a standard command.
     */
    public Command convertToCommand(String input) {
        Command command = new Command();
        //deal with command according to user input (i.e, set commandType and description)
        return command;
    }
}
