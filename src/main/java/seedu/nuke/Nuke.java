package seedu.nuke;

import seedu.nuke.command.AddModuleCommand;
import seedu.nuke.command.AddTaskCommand;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.DeleteModuleCommand;
import seedu.nuke.command.DeleteTaskCommand;
import seedu.nuke.command.EditDeadlineCommand;
import seedu.nuke.command.EditTaskCommand;
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
    private CommandResult commandResult;
    private ModuleManager moduleManager;
    private DataManager dataManager;
    private HashMap<String, String> modulesMap;
    public ScreenShotManager screenShotManager; //?public
    private ScreenShot currentScreenShot;
    private Ui ui;
    private StorageManager storageManager;

    /**
     * constructor of nuke.
     * @throws FileNotFoundException if file cannot be found when loading jason file
     */
    public Nuke() throws FileNotFoundException {
        ui = new Ui();
        modulesMap  = ModuleLoader.load("moduleList.json");
        storageManager = new StorageManager("data.json");
        moduleManager = new ModuleManager(modulesMap);
        moduleManager.setModules(storageManager.load());
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
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) {
        try {
            readFromScreenShot();
            setCommandData(command);
            if (command instanceof AddModuleCommand | command instanceof AddTaskCommand |
                    command instanceof EditDeadlineCommand | command instanceof DeleteModuleCommand |
                    command instanceof DeleteTaskCommand){
                saveToScreenShot();
                addScreenShotToScreenShotList();
            }
            execute(command);
            System.out.println(ScreenShotManager.getCurrentPointer());
            System.out.println("there are "+ dataManager.getAllTasks().size() +"tasks in the module");
        } catch (Exception ignored) {

        }
        return commandResult;
    }

    private void execute(Command command) {
        commandResult = command.execute();
    }

    private void addScreenShotToScreenShotList() {
        ScreenShotManager.getScreenShotList().add(currentScreenShot);
        ScreenShotManager.setCurrentPointerForward();
    }

    private void setCommandData(Command command) {
        command.setData(moduleManager, dataManager, screenShotManager);
    }

    private void saveToScreenShot() {
        currentScreenShot.takeScreenShot(moduleManager, dataManager);
    }

    /**
     * set module manager data according to screen shot manager data
     */
    private void readFromScreenShot() {
    }
}
