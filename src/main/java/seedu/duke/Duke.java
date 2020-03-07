package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("What is your name?");
        Parser parser = new Parser();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("exit")) {
            parser.parseTypeOfInstruction(input);
            input = in.nextLine();
        }
    }
}
