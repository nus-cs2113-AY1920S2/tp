package seedu.nuke;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShot;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.parser.Parser;
import seedu.nuke.ui.TextUi;
import seedu.nuke.ui.Ui;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Nuke {
    private CommandResult commandResult;
    private ModuleManager moduleManager;
    private DataManager dataManager;
    private HashMap<String, String> modulesMap;
    public ScreenShotManager screenShotManager; //?public
    private ScreenShot currentScreenShot;
    private Ui ui;

    /**
     * constructor of nuke.
     * @throws FileNotFoundException if file cannot be found when loading jason file
     */
    public Nuke() throws FileNotFoundException {
        ui = new Ui();
        modulesMap  = ModuleLoader.load("moduleList.json");
        moduleManager = new ModuleManager(modulesMap);
        dataManager = new DataManager(moduleManager);
        screenShotManager = new ScreenShotManager();
        currentScreenShot = new ScreenShot(moduleManager, dataManager);
        screenShotManager.getScreenShotList().add(currentScreenShot);
    }

    /**
     * ScreenShot entry-point for the java.duke.Duke application.
     *
     * @param args arguments passed to the programme.
     * @throws FileNotFoundException exception is thrown if the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Nuke().run();
    }

    /**
     * run method for Nuke class.
     */
    public void run() {
        welcomeUser();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Method to print the welcome message to the user.
     */
    public void welcomeUser() {
        TextUi.clearScreen();
        TextUi.displayLogo();
        TextUi.showWelcomeMessage();
    }

    /**
     * Method to print the exit message to the user.
     */
    public void exit() {
        TextUi.showExitMessage();
    }

    /**
     * Method to run the command from the user's input until bye command is received.
     */
    private void runCommandLoopUntilExitCommand() {
        do {
            String userCommandText = ui.getInput();
            try {
                Command command = new Parser().parseCommand(userCommandText);
                CommandResult result = executeCommand(command);
                ui.showResult(result);
            } catch (Exception e) {
                ui.showSystemMessage(e.getMessage());
            }
        } while (!ExitCommand.isExit());
    }

    /**
     * initialize the taskManager system, execute command and save the list to the file.
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) {
        try {
            //load from current screen shot
            readScreenShot();
            // supplies the data the command will operate on.
            // if there is no file to load or the file is empty, setData will initialize a new taskManager system
            //update the module manager as well as the data manager
            setCommandData(command);
            //take the screen shot
            takeScreenShot();
            //add screen shot
            addScreenShotToScreenShotList();
            // Execute according to the command itself
            execute(command);
            // save the taskManager to a file
            //moduleManager.getStorager().save(taskManager);
            //StorageFile.saveJson(taskManager);
        } catch (Exception e) {
            // the out layer exception handler
            ui.showSystemMessage(e.getMessage());
        }
        return commandResult;
    }

    private void execute(Command command) {
        commandResult = command.execute();
    }

    private void addScreenShotToScreenShotList() {
        screenShotManager.getScreenShotList().add(currentScreenShot);
    }

    private void setCommandData(Command command) {
        command.setData(moduleManager, dataManager, screenShotManager);
    }

    private void takeScreenShot() {
        currentScreenShot.takeScreenShot(moduleManager, dataManager);
    }

    private void readScreenShot() {
        dataManager.setAllTasks(screenShotManager.getCurrentScreenShot().getDataManager().getAllTasks());
        moduleManager.setModules(screenShotManager.getCurrentScreenShot().getModuleManager().getModuleList());
    }
}
