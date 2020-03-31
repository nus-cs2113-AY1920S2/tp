package seedu.duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import seedu.duke.command.Command;
import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.Person;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.ModuleManagerException;
import seedu.duke.exception.StorageException;
import seedu.duke.parser.Controller;
import seedu.duke.storage.StorageAvailableModulesList;
import seedu.duke.storage.StoragePersonInfo;
import seedu.duke.storage.StorageSemesterList;
import seedu.duke.ui.Ui;


public class Duke {

    private static AvailableModulesList availableModulesList;
    private static SemesterList semesterList;
    private static Ui ui;
    private static final Logger logr = Logger.getLogger("Duke");

    /**
     * Instantiate all required classes.
     */
    public Duke() {
        ui = new Ui();
        try {
            StoragePersonInfo.load();
        } catch (StorageException e) {
            logr.log(Level.WARNING, e.getMessage());
        }
        try {
            availableModulesList = StorageAvailableModulesList.load();
        } catch (StorageException e) {
            logr.log(Level.WARNING, e.getMessage());
        }
        try {
            semesterList = StorageSemesterList.load();
        } catch (StorageException e) {
            logr.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Main program to run.
     */
    public void run() {
        setupLogger();
        Scanner in = new Scanner(System.in);
        Ui.greetUser();
        if (!Person.isPersonExist()) {
            Person.createNewUser(in);
        }
        Ui.showHelpMessage();
        String fullCommand;
        boolean isExit = false;
        do {
            try {
                fullCommand = in.nextLine();
                Command command = Controller.control(fullCommand);
                command.execute(semesterList, availableModulesList);
                isExit = command.isExit();
            } catch (ModuleManagerException e) {
                logr.log(Level.WARNING, e.getMessage());
            }
        } while (!isExit);
        Ui.greetFarewell();
    }

    static void setupLogger() {
        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logr.addHandler(consoleHandler);
        try {
            FileHandler fileHandler = new FileHandler("myLogger.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.FINE);
            logr.addHandler(fileHandler);
        } catch (IOException e) {
            logr.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
