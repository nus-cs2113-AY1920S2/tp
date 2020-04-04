package seedu.duke.data;

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

    public static void createNewUser() {
        name = "User";
        String matricYear = "2020";
        new Person(name, matricYear);
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
