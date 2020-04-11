package seedu.techtoday.commands;

import com.google.gson.JsonSyntaxException;
import org.json.JSONException;
import seedu.techtoday.common.Messages;
import seedu.techtoday.storage.Loader;
import seedu.techtoday.ui.Ui;

import java.io.File;
import java.io.IOException;

/** Class representing the commands essential for the logic that laods the data and runs the program. */
public class ProgramExecutor {

    /**
     * Executes the program when isRunning condition is met.
     *
     * @param isRunning - Boolean value representing weather user has typed the command "exit"
     */
    public static void executeProgram(Boolean isRunning) {
        while (isRunning) {
            String userResponse = Ui.getCommand();
            String command = userResponse.split(" ")[0].trim().toLowerCase();

            switch (command) {
            case "view": {
                InformationViewer.execute(userResponse);
                Messages.printStraightLine();
                break;
            } case "exit": {
                ApplicationTerminator.execute();
                isRunning = false;
                break;
            } case "save": {
                InformationSaver.execute(userResponse);
                break;
            } case "list": {
                InformationLister.execute(userResponse);
                Messages.printStraightLine();
                break;
            } case "create": {
                InformationCreator.execute(userResponse);
                break;
            } case "delete": {
                InformationDeleter.execute(userResponse);
                break;
            } case "addinfo": {
                InformationAdder.execute(userResponse);
                break;
            } case "help": {
                Helper.execute();
                break;
            } default: {
                System.out.println("Invalid command. Try again using a valid command! "
                        + "Type \"help\" for looking at commands");
                Messages.printStraightLine();
                break;
            }
            }
        }
    }

    /** Loads the data of the program from the memory. */
    public static void loadData() {
        File articleListFile = new File("articleList.json");
        File jobListFile = new File("articleList.json");
        File noteListFile = new File("noteList.json");

        boolean existsArticle = articleListFile.exists();
        boolean existsJob = jobListFile.exists();
        boolean existsNote = noteListFile.exists();

        if (existsArticle & existsJob & existsNote) {
            try {
                System.out.println("Loading \"articleList.json\", \"jobList.json\" and \"notelist.json\"...\n");
                Loader.execute("articleList.json", "jobList.json",
                        "noteList.json");
                System.out.println("Done loading files, enter your command now:");
                Messages.printStraightLine();
            } catch (JsonSyntaxException | JSONException e) {
                System.out.println("Corrupted Json Files encountered.");
            }
        } else {
            System.out.println("All the required files do not exist. "
                    + "We will create completely new files to save your data.");
            Messages.printStraightLine();
        }
    }

    /** Initializes the logger for Information Adder class- the only class that utilizes logger. */
    public static void startLogger() {
        try {
            InformationAdder.startLogger();
        } catch (IOException e) {
            System.out.println("Logging setup failed! Logs will be printed to console instead of saved to file.");
        }
    }
}
