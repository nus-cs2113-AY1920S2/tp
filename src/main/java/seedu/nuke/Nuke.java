package seedu.nuke;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.ExitCommand;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
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
    private ScreenShotManager screenShotManager = new ScreenShotManager();
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

//        ScreenShot toAdd = ScreenShotManager.takeNewScreenShot(moduleManager);
//        /** save screen shot */
//        ScreenShotManager.saveNewScreenShot(toAdd);
//        /** get moduleListInJsonStr*/
//        String moduleListInJsonStr = ScreenShotManager.getCurrentScreenShot().getModuleListInJsonStr();
//        /** load module list from moduleListInJsonStr */
//        moduleManager.setModules(ScreenShotManager.readFromScreenShot(moduleListInJsonStr));
//        dataManager = new DataManager(moduleManager);
        /** set command attribute*/
        setCommandData(command);
        /** if necessary command */
//        if (command instanceof AddModuleCommand | command instanceof AddTaskCommand |
//                command instanceof EditDeadlineCommand | command instanceof DeleteModuleCommand |
//                command instanceof DeleteTaskCommand){
//            /** take new screen shot of current module manager */
//            toAdd = ScreenShotManager.takeNewScreenShot(moduleManager);
//            /** save screen shot */
//            ScreenShotManager.saveNewScreenShot(toAdd);
//        }
        execute(command);

        return commandResult;
    }

    private void execute(Command command) {
        commandResult = command.execute();
    }

    /**
     * set command attributes from module manager and data manager
     * @param command
     */
    private void setCommandData(Command command) {
        command.setData(moduleManager, dataManager, screenShotManager);
    }
}
