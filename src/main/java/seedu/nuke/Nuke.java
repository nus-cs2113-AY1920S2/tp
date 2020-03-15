package seedu.nuke;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.data.*;
import seedu.nuke.parser.Parser;
import seedu.nuke.ui.TextUi;
import seedu.nuke.ui.Ui;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Nuke {
    private CommandResult commandResult;
    private ModuleManager moduleManager;
    private DataManager dataManager;
    public HashMap<String, String> modulesMap; //?public
    public ScreenShotManager screenShotManager; //?public
    private ScreenShot currentScreenShot;
    private Ui ui;

    /**
     * constructor of nuke
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
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Nuke().run();
    }

    public void run() {
        welcomeUser();
        runCommandLoopUntilExitCommand();
        exit();
    }

    public void welcomeUser() {
        TextUi.clearScreen();
        TextUi.displayLogo();
        TextUi.showWelcomeMessage();
    }

    public void exit() {
        TextUi.showExitMessage();
    }

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
     * initialize the taskManager system, execute command and save the list to the file
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
        } catch (Exception ex) {
            // the out layer exception handler
            System.out.println(ex);
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
