package seedu.duke;

import java.util.Scanner;
import seedu.duke.module.Module;

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
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your module?");

        Scanner in = new Scanner(System.in);
        Module module = new Module("id",in.nextLine());
        System.out.println("Hello " + module);
        //System.out.println("Hello " + in.nextLine());
    }
}
