package seedu.duke;

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
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner sc = new Scanner(System.in);

        String userName = sc.nextLine();

        System.out.println("Hello " + userName +
                " We would require some basic information about you before we begin :)");
        System.out.print("Please enter your current age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print(System. lineSeparator());
        System.out.print("Please enter your gender: ");
        String gender = sc.nextLine();
        System.out.print(System. lineSeparator());
        System.out.print("Please enter your current height: ");
        double height = Double.parseDouble(sc.nextLine());
        System.out.print(System. lineSeparator());
        System.out.print("Please enter your current weight: ");
        double weight = Double.parseDouble(sc.nextLine());
        System.out.print(System. lineSeparator());

    }
}
