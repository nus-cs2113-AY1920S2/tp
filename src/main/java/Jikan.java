import java.util.Scanner;

public class Jikan {
    public static final String GREETING =
            "\n" +
                    "     _\n" +
                    "   _/ }\n" +
                    "  `>' \\\n" +
                    "   `|  \\\n" +
                    "    |  /'-.    .-.\n" +
                    "    \\'   ';`--' .'\n" +
                    "     \\'.   `'-./\n" +
                    "      '.`\"-..-;`\n" +
                    "        `;-..'\n" +
                    "        _| _|\n" +
                    "        /` /` \n" +
                    "Hello! I'm Jikan\n" + "What can I do for you?\n" +
                    "____________________________________________________________\n";
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner in = new Scanner(System.in);
        ActivityList activityList = new ActivityList();
        Parser.parseUserCommands(in, activityList);
    }
}
