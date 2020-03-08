package seedu.nuke;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class Nuke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        ansi().reset();
        System.out.println(ansi().bold().fg(Ansi.Color.RED).a("Hello from NUKE.").reset());
        //System.out.println(ansi().bold().fg(Ansi.Color.BLUE).a(logo).reset());
        System.out.println(ansi().bold().fg(Ansi.Color.MAGENTA).a("Hope to see you soon.").reset());
        AnsiConsole.systemUninstall();
    }
}
