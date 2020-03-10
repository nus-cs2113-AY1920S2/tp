package seedu.duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        String userName = sc.nextLine();

        System.out.println("Hello " + userName +
                "! We would require some basic information about you before we begin :)");

        System.out.println("Please enter your current age: ");
        int userAge = Integer.parseInt(sc.nextLine());

        System.out.println("Please enter your gender: ");
        String userGender = sc.nextLine();

        System.out.println("Please enter your current height: ");
        double userHeight = Double.parseDouble(sc.nextLine());

        System.out.println("Please enter your current weight: ");
        double userWeight = Double.parseDouble(sc.nextLine());

        System.out.println("Name: " + userName + System.lineSeparator() +
                "Age: " + userAge + System.lineSeparator() +
                "Gender: " + userGender + System.lineSeparator() +
                "Height: " + userHeight + System.lineSeparator() +
                "Weight: " + userWeight + System.lineSeparator());

    }
}
