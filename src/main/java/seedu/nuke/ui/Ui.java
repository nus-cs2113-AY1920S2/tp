package seedu.nuke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }
}
