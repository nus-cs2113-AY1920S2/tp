package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi of the application. 
 *
 */
public class Ui {
    
    private final Scanner in;
    private final PrintStream out;
    
    public Ui() {
        this(System.in, System.out);
    }
    
    public void showWelcomeMessage() {
        System.out.println("WELCOME!!!");
    }
    
    public Ui(InputStream in, PrintStream out) {
            this.in = new Scanner(in);
            this.out = out;
    }
    
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
