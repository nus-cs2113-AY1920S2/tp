package seedu.duke.data;

import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Person {
    private static String name;
    private static int matricYear;
    private static double totalCap;
    private static int totalModularCreditCompleted;


    public static int getTotalModularCreditCompleted() {
        return totalModularCreditCompleted;
    }

    public static void addTotalModularCreditCompleted(int modularCredit) {
        Person.totalModularCreditCompleted += modularCredit;
    }

    public Person(String name, int year) {
        Person.name = name;
        matricYear = year;
    }

    public static Person createNewUser(Scanner in) {
        Ui.showInputUserNameRequest();
        String userName = in.nextLine();
        name = userName;
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
