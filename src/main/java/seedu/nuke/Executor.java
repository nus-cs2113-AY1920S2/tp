package seedu.nuke;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.promptCommand.PromptType;
import seedu.nuke.common.DataType;
import seedu.nuke.directory.Directory;
import seedu.nuke.parser.Parser;

import java.util.ArrayList;

public class Executor {
    /* Variables to transit between parsing regular commands and prompts */
    private static PromptType promptType = PromptType.NONE;
    private static String userMessage;
    private static DataType dataType = DataType.NONE;
    private static ArrayList<Directory> filteredList;
    private static ArrayList<Integer> indices;

    public static void preparePromptIndices() {
        promptType = PromptType.INDICES;
    }

    public static void preparePromptConfirmation() {
        promptType = PromptType.CONFIRMATION;
    }

    public static void terminatePrompt() {
        promptType = PromptType.NONE;
    }

    public static ArrayList<Integer> getIndices() {
        return indices;
    }

    public static void setIndices(ArrayList<Integer> indices) {
        Executor.indices = indices;
    }

    public static DataType getDataType() {
        return dataType;
    }

    public static ArrayList<Directory> getFilteredList() {
        return filteredList;
    }

    public static void setFilteredList(ArrayList<Directory> filteredList, DataType dataType) {
        Executor.filteredList = filteredList;
        Executor.dataType = dataType;
    }


    /**
     * Executes command and save the list to the file.
     *
     * @param userInput The input from the user to be parsed and executed as a command
     * @return commandResult that contains the execute output information
     */
    public static CommandResult executeCommand(String userInput) {
        Command command;
        switch (promptType) {
            case CONFIRMATION:
                command = new Parser().parseInputAsConfirmation(userInput.toLowerCase());
                break;

            case INDICES:
                command = new Parser().parseInputAsIndices(userInput);
                break;

            default:
                command = new Parser().parseCommand(userInput);
                break;
        }

        //load from current screen shot
        //readScreenShot();
        // supplies the data the command will operate on.
        // if there is no file to load or the file is empty, setData will initialize a new taskManager system
        //update the module manager as well as the data manager
        // setCommandData(command);
        //take the screen shot
        //takeScreenShot();
        //add screen shot
        //addScreenShotToScreenShotList();
        // Execute according to the command itself
        return command.execute();
        // save the taskManager to a file
        //moduleManager.getStorager().save(taskManager);
        //StorageFile.saveJson(taskManager);
    }


//    public void setCommandData(Command command) {
//        command.setData(moduleManager);
//    }
}
