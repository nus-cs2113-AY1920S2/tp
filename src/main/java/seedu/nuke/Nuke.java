
package seedu.nuke;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShot;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.data.StorageManager;
import seedu.nuke.parser.Parser;
import seedu.nuke.ui.TextUi;
import seedu.nuke.ui.Ui;
import seedu.nuke.util.Message;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Nuke {
    public ScreenShotManager screenShotManager; //?public
    private CommandResult commandResult;
    private ModuleManager moduleManager;
    //private DataManager dataManager;
    private HashMap<String, String> modulesMap;
    private ScreenShot currentScreenShot;
    private Ui ui;
    private StorageManager storageManager;

    /**
     * constructor of nuke.
     *
     * @throws FileNotFoundException if file cannot be found when loading jason file
     */
    public Nuke() throws FileNotFoundException {
        ui = new Ui();
        modulesMap = ModuleLoader.load("moduleList.json");
        storageManager = new StorageManager("data.json");
        moduleManager = new ModuleManager(modulesMap);
        moduleManager.setModuleList(storageManager.load());
        //dataManager = new DataManager(moduleManager);
        screenShotManager = new ScreenShotManager();
        //currentScreenShot = new ScreenShot(moduleManager, dataManager);
        //screenShotManager.getScreenShotList().add(currentScreenShot);
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
        storageManager.save(moduleManager.getModuleList());
        //exit();
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
        ui.showSystemMessage(Message.DIVIDER);
    }

    /**
     * Method to run the command from the user's input until bye command is received.
     */
    private void runCommandLoopUntilExitCommand() {
        do {
            String userCommandText = ui.getInput();

            Command command = new Parser().parseCommand(userCommandText, moduleManager);
            CommandResult result = executeCommand(command);
            ui.showResult(result);

        } while (!ExitCommand.isExit());
    }

    /**
     * initialize the taskManager system, execute command and save the list to the file.
     *
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) {
        //load from current screen shot
        //readScreenShot();
        // supplies the data the command will operate on.
        // if there is no file to load or the file is empty, setData will initialize a new taskManager system
        //update the module manager as well as the data manager
        setCommandData(command);
        //take the screen shot
        //takeScreenShot();
        //add screen shot
        //addScreenShotToScreenShotList();
        // Execute according to the command itself
        execute(command);
        // save the taskManager to a file
        //moduleManager.getStorager().save(taskManager);
        //StorageFile.saveJson(taskManager);
        return commandResult;
    }

    private void execute(Command command) {
        commandResult = command.execute();
    }

    private void setCommandData(Command command) {
        command.setData(moduleManager);
    }
}
