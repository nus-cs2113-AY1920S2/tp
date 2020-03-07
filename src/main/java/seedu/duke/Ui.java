package seedu.duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static final void displayError(String error) {
        System.out.println(error);
    }

    public static final void displayAddStudent() {
        System.out.println("Added");
    }

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine().strip();
    }

    public void displayMarkAttendance(Student student) {
        System.out.println(student.getStudentName() + "Attended");
    }

    public void displayAttendance(Attendance student) {
        System.out.println(student.getStudentName() + "Already M");
    }
}
