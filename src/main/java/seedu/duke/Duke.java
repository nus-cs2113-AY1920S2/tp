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
        Parser parser = new Parser();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("exit")) {
            parser.parseTypeOfInstruction(input);
            input = in.nextLine();
        }
    }
}
