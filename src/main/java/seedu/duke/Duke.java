package seedu.duke;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "  _   _ _    _ _  ________ \n" +
                " | \\ | | |  | | |/ /  ____|\n" +
                " |  \\| | |  | | ' /| |__   \n" +
                " | . ` | |  | |  < |  __|  \n" +
                " | |\\  | |__| | . \\| |____ \n" +
                " |_| \\_|\\____/|_|\\_\\______|\n" +
                "                           \n" +
                "                           ";
        AnsiConsole.systemInstall();
        ansi().reset();
        AnsiConsole.systemUninstall();
        System.out.println(ansi().bold().fg(Ansi.Color.RED).a("Hello from").reset());
        System.out.println(ansi().bold().fg(Ansi.Color.BLUE).a(logo).reset());
        System.out.println(ansi().bold().fg(Ansi.Color.MAGENTA).a("Where are you from?").reset());
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }

}
