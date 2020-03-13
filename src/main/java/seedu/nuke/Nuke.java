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

import static org.fusesource.jansi.Ansi.ansi;

public class Nuke {
    private CommandResult commandResult;
    private ModuleManager moduleManager;
    private DataManager dataManager;
    public HashMap<String,String> modulesMap;
    public ScreenShotManager screenShotManager;
    private ScreenShot screenShot;

    private Ui ui;

    public Nuke() throws FileNotFoundException {
        modulesMap  = ModuleLoader.load("moduleList.json");
        moduleManager = new ModuleManager(modulesMap);
        dataManager = new DataManager();
        screenShotManager = new ScreenShotManager();
        screenShot = new ScreenShot(moduleManager, dataManager, 0);
    }

    /**
     * ScreenShot entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws FileNotFoundException {
        TextUi.clearScreen();
        TextUi.displayLogo();
        new Nuke().run();
    }

    public void run() {
        this.ui = new Ui();
        TextUi.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
    }

    private void runCommandLoopUntilExitCommand() {
        do {
            String userCommandText = ui.getInput();
            Command command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResult(result);
        } while (!ExitCommand.isExit());
    }

    /**
     * initialize the taskManager system, execute command and save the list to the file
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) {
        try {
            // supplies the data the command will operate on.
            // if there is no file to load or the file is empty, setData will initialize a new taskManager system
            //update the module manager as well as the data manager
            command.setData(moduleManager, dataManager);
            //take the screen shot
            screenShot.takeScreenShot(moduleManager, dataManager);
            // Execute according to the command itself
            commandResult = command.execute();
            // save the taskManager to a file
            //moduleManager.getStorager().save(taskManager);
            //StorageFile.saveJson(taskManager);
        } catch (Exception ex) {
            // the out layer exception handler
            System.out.println(ex);
        }
        return commandResult;
    }
}
