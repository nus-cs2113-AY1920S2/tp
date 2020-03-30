package seedu.duke.data;

import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Person {
    private static String name;
    private static String matricYear;
    private static double totalCap;
    private static int totalModuleCreditCompleted;
    private static boolean isPersonExist = false;


    public static int getTotalModuleCreditCompleted() {
        return totalModuleCreditCompleted;
    }

    public static void addTotalModuleCreditCompleted(int modularCredit) {
        Person.totalModuleCreditCompleted += modularCredit;
    }

    public Person(String name, String year) {
        Person.name = name;
        matricYear = year;
        isPersonExist = true;
    }

    public static Person createNewUser(Scanner in) {
        name = "User";
        String matricYear = "2018";
        return new Person(name, matricYear);
    }

    public static String getName() {
        return name;
    }

    public static String getMatricYear() {
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
