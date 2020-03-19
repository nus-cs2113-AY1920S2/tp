package seedu.duke.data;

import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Person {
    private static String name;
    private static int matricYear;

    public Person(String name, int year) {
        this.name = name;
        this.matricYear = year;
    }

    public static Person createNewUser(Scanner in) {
        Ui.showInputUserNameRequest();
        String userName = in.nextLine();
        Ui.showInputUserMatricYearRequest();
        int matricYear = Integer.parseInt(in.nextLine());
        return new Person(userName, matricYear);
    }

    public static String getName() {
        return name;
    }

    public static int getMatricYear() {
        return matricYear;
    }
}
