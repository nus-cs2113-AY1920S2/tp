package seedu.duke.data;

import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Person {
    private static String name;
    private static int matricYear;
    private static double totalCap;
    private static int totalModuleCreditCompleted;
    private static boolean isPersonExist = false;


    public static int getTotalModuleCreditCompleted() {
        return totalModuleCreditCompleted;
    }

    public static void addTotalModuleCreditCompleted(int modularCredit) {
        Person.totalModuleCreditCompleted += modularCredit;
    }

    public Person(String name, int year) {
        Person.name = name;
        matricYear = year;
        isPersonExist = true;
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

    public static void setTotalCap(double cap) {
        totalCap = cap;
    }

    public static double getTotalCap() {
        return totalCap;
    }

    public static boolean isPersonExist() {
        return isPersonExist;
    }

    public static String toStorageString() {
        return name + "," + matricYear;
    }
}
