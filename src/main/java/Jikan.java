import jikan.storage.Storage;

import java.util.Scanner;

/**
 * Represents the Jikan time tracker.
 */
public class Jikan {
    /** Constant file path of data file. */
    private static final String DATA_FILE_PATH = "data/data.csv";

    /** Storage object for data file. */
    private static Storage storage;

    /** Activity list to store current tasks in. */
    private static ActivityList activityList;

    public static final String GREETING =
            "\n"
                    + "     _\n"
                    + "   _/ }\n"
                    + "  `>' \\\n"
                    + "   `|  \\\n"
                    + "    |  /'-.    .-.\n"
                    + "    \\'   ';`--' .'\n"
                    + "     \\'.   `'-./\n"
                    + "      '.`\"-..-;`\n"
                    + "        `;-..'\n"
                    + "        _| _|\n"
                    + "        /` /` \n"
                    + "Hello! I'm Jikan\n" + "What can I do for you?\n"
                    + "____________________________________________________________\n";

    /**
     * Creates ActivityList and loads data from data file if the data file previously existed;
     * otherwise, an empty task list is initialized.
     */
    private static void createActivityList() {
        if (storage.loadFile()) {
            activityList = new ActivityList(storage.dataFile);
        } else {
            activityList = new ActivityList();
        }
    }

    /**
     * Main entry-point for the Jikan application.
     */
    public static void main(String[] args) {
        System.out.println(GREETING);
        Scanner in = new Scanner(System.in);
        storage = new Storage(DATA_FILE_PATH);
        createActivityList();
        Parser.parseUserCommands(in, activityList);
    }
}
