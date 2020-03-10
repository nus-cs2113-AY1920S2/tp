package seedu.duke;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    private Ui() {
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        System.out.println("Hello " + sc.nextLine());
    }
}
