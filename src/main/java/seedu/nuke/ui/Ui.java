package seedu.nuke.ui;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;
import seedu.nuke.directory.TaskTag;
import seedu.nuke.util.ListCreator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;


    /**
     * Constructs the <code>UI</code>.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    /**
     * Shows the directory path to the user.
     */
    public void showDirectoryPath() {
        out.println(String.format("%s : ", DirectoryTraverser.getFullPath()));
    }

    /**
     * Reads the user input from the command line.
     *
     * @return The input given by the user
     */
    public String getInput() {
        showDirectoryPath();
        return in.nextLine().trim();
    }

    /**
     * Shows the result message after the command given by the user input is executed.
     * <br>
     * Also, shows a list to the user if requested.
     *
     * @param result
     *  The result after executing a command given by the user input
     */
    public void showResult(CommandResult result) {
        if (result.getFeedbackToUser() == null) {
            showMessage("");
            return;
        }

        if (!result.getFeedbackToUser().isEmpty()) {
            showMessage(result.getFeedbackToUser());
        }

        if ((result.getDirectoryLevel() == DirectoryLevel.NONE) && result.getHelpGuide() == null) {
            return;
        }

        String listTableToShow;
        switch (result.getDirectoryLevel()) {

        case MODULE:
            ArrayList<Module> moduleList = result.getShownList().stream()
                    .map(Module.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createModuleListTable(moduleList);
            break;

        case CATEGORY:
            ArrayList<Category> categoryList = result.getShownList().stream()
                    .map(Category.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createCategoryListTable(categoryList);
            break;

        case TASK:
            ArrayList<Task> taskList = result.getShownList().stream()
                    .map(Task.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createTaskListTable(taskList);
            break;

        case FILE:
            ArrayList<TaskFile> fileList = result.getShownList().stream()
                    .map(TaskFile.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createFileListTable(fileList);
            break;

        case TAG:
            ArrayList<TaskTag> tagList = result.getShownList().stream()
                    .map(TaskTag.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            listTableToShow = ListCreator.createTagListTable(tagList);
            break;

        case NONE:
            if (result.getHelpGuide() == null) {
                return;
            }
            ArrayList<String> helpList = result.getHelpGuide();
            listTableToShow = ListCreator.createGeneralListTable(helpList);
            break;

        default:
            return;
        }

        showMessage(listTableToShow);
    }

    /**
     * Shows a message to the user.
     *
     * @param message Message to be shown
     */
    public void showMessage(String message) {
        out.println(message.replace("\n", LS));
    }
}
