package seedu.duke;

import java.util.Scanner;

public class Parser {
    private static Scanner in = new Scanner(System.in);

    public Command getCommand(){
        String input = in.nextLine();
        return convertToCommand(input);
    }

    public Command convertToCommand(String input){
        Command command = new Command();
        //deal with command according to user input (i.e, set commandType and description)
        return command;
    }
}
