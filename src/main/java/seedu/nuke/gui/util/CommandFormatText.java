package seedu.nuke.gui.util;

import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import seedu.nuke.command.ChangeDirectoryCommand;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.HelpCommand;
import seedu.nuke.command.addcommand.AddCategoryCommand;
import seedu.nuke.command.addcommand.AddModuleCommand;
import seedu.nuke.command.addcommand.AddTaskCommand;
import seedu.nuke.command.editcommand.EditCategoryCommand;
import seedu.nuke.command.editcommand.EditModuleCommand;
import seedu.nuke.command.editcommand.EditTaskCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListCategoryCommand;
import seedu.nuke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskCommand;

import static seedu.nuke.gui.io.GuiParser.*;
import static seedu.nuke.gui.util.TextUtil.createText;

public class CommandFormatText {
    private static TextFlow commandFormat = new TextFlow();

    public static TextFlow getCommandFormat(String commandWord) {
        commandFormat.getChildren().clear();
        commandFormat.setStyle("-fx-font-size: 12pt");
        switch (commandWord) {
        case AddModuleCommand.COMMAND_WORD:
            return getAddModuleFormat();

        case AddCategoryCommand.COMMAND_WORD:
            return getAddCategoryFormat();

        case AddTaskCommand.COMMAND_WORD:
            return getAddTaskFormat();

        case DeleteModuleCommand.COMMAND_WORD:
            return getDeleteModuleFormat();

        case ListModuleCommand.COMMAND_WORD:
            return getListModuleFormat();

        case DeleteCategoryCommand.COMMAND_WORD:
            return getDeleteCategoryFormat();

        case ListCategoryCommand.COMMAND_WORD:
            return getListCategoryFormat();

        case DeleteTaskCommand.COMMAND_WORD:
            return getDeleteTaskFormat();

        case ListTaskCommand.COMMAND_WORD:
            return getListTaskFormat();

        case EditModuleCommand.COMMAND_WORD:
            return getEditModuleFormat();

        case EditCategoryCommand.COMMAND_WORD:
            return getEditCategoryFormat();

        case EditTaskCommand.COMMAND_WORD:
            return getEditTaskFormat();

        case ChangeDirectoryCommand.COMMAND_WORD:
            return getChangeDirectoryFormat();

        case ExitCommand.COMMAND_WORD:
            return getExitFormat();

        case HelpCommand.COMMAND_WORD:
            return getHelpFormat();

        default:
            String invalidString = "Invalid Command Word!";
            commandFormat.getChildren().add(createText(invalidString, Color.CRIMSON));
            return commandFormat;
        }
    }

    private static TextFlow getAddModuleFormat() {
        commandFormat.getChildren().addAll(
                createText(AddModuleCommand.COMMAND_WORD, Color.GREEN),
                createText(" <module code>", Color.BLUE)
        );
        return commandFormat;
    }

    private static TextFlow getAddCategoryFormat() {
        commandFormat.getChildren().addAll(
                createText(AddCategoryCommand.COMMAND_WORD, Color.GREEN),
                createText(" <category name> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <module code>", Color.BLUE),
                createText(String.format(" %s %s %s %s", "[", PRIORITY_PREFIX, "<priority>", "]"), Color.DARKGRAY)

        );
        return commandFormat;
    }

    private static TextFlow getAddTaskFormat() {
        commandFormat.getChildren().addAll(
                createText(AddTaskCommand.COMMAND_WORD, Color.GREEN),
                createText(" <task description> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <module code> ", Color.BLUE),
                createText(CATEGORY_NAME_PREFIX, Color.GREEN),
                createText(" <category name>", Color.BLUE),
                createText(String.format(" %s %s %s %s %s %s", "[", DEADLINE_PREFIX, "<deadline>",
                        PRIORITY_PREFIX, "<priority>", "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getDeleteModuleFormat() {
        commandFormat.getChildren().addAll(
                createText(DeleteModuleCommand.COMMAND_WORD, Color.GREEN),
                createText(" <module code>", Color.BLUE),
                createText(String.format(" %s %s %s %s", "[", EXACT_FLAG, ALL_FLAG, "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getDeleteCategoryFormat() {
        commandFormat.getChildren().addAll(
                createText(DeleteCategoryCommand.COMMAND_WORD, Color.GREEN),
                createText(" <category name> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <module code>", Color.BLUE),
                createText(String.format(" %s %s %s %s", "[", EXACT_FLAG, ALL_FLAG, "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getDeleteTaskFormat() {
        commandFormat.getChildren().addAll(
                createText(DeleteTaskCommand.COMMAND_WORD, Color.GREEN),
                createText(" <task description> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <module code> ", Color.BLUE),
                createText(CATEGORY_NAME_PREFIX, Color.GREEN),
                createText(" <category name>", Color.BLUE),
                createText(String.format(" %s %s %s %s", "[", EXACT_FLAG, ALL_FLAG, "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getListModuleFormat() {
        commandFormat.getChildren().addAll(
                createText(ListModuleCommand.COMMAND_WORD, Color.GREEN),
                createText(String.format(" %s %s %s %s %s", "[", "<module keyword>",
                        EXACT_FLAG, ALL_FLAG, "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getListCategoryFormat() {
        commandFormat.getChildren().addAll(
                createText(ListCategoryCommand.COMMAND_WORD, Color.GREEN),
                createText(String.format(" %s %s %s %s %s %s %s", "[", "<category keyword>",
                        MODULE_CODE_PREFIX, "<module keyword>", EXACT_FLAG, ALL_FLAG, "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getListTaskFormat() {
        commandFormat.getChildren().addAll(
                createText(ListTaskCommand.COMMAND_WORD, Color.GREEN),
                createText(String.format(" %s %s %s %s %s %s %s %s %s", "[", "<task keyword>",
                        MODULE_CODE_PREFIX, "<module keyword>",
                        CATEGORY_NAME_PREFIX, "<category keyword>", EXACT_FLAG, ALL_FLAG, "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getEditModuleFormat() {
        commandFormat.getChildren().addAll(
                createText(EditModuleCommand.COMMAND_WORD, Color.GREEN),
                createText(" <module code> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <new module code> ", Color.BLUE)
        );
        return commandFormat;
    }

    private static TextFlow getEditCategoryFormat() {
        commandFormat.getChildren().addAll(
                createText(EditCategoryCommand.COMMAND_WORD, Color.GREEN),
                createText(" <category name> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <module code>", Color.BLUE),
                createText(String.format(" %s %s %s %s %s %s", "[", CATEGORY_NAME_PREFIX, "<new category name>",
                        PRIORITY_PREFIX, "<new priority>", "]"), Color.DARKGRAY)

        );
        return commandFormat;
    }

    private static TextFlow getEditTaskFormat() {
        commandFormat.getChildren().addAll(
                createText(EditTaskCommand.COMMAND_WORD, Color.GREEN),
                createText(" <task description> ", Color.BLUE),
                createText(MODULE_CODE_PREFIX, Color.GREEN),
                createText(" <module code> ", Color.BLUE),
                createText(CATEGORY_NAME_PREFIX, Color.GREEN),
                createText(" <category name>", Color.BLUE),
                createText(String.format(" %s %s %s %s %s %s %s %s", "[",
                        TASK_DESCRIPTION_PREFIX, "<new task description>", DEADLINE_PREFIX, "<new deadline>",
                        PRIORITY_PREFIX, "<new priority>", "]"), Color.DARKGRAY)
        );
        commandFormat.setStyle("-fx-font-size: 10pt");
        return commandFormat;
    }

    private static TextFlow getChangeDirectoryFormat() {
        commandFormat.getChildren().addAll(
                createText(ChangeDirectoryCommand.COMMAND_WORD, Color.GREEN),
                createText(" <next directory name>", Color.BLUE),
                createText(" to traverse down; ", Color.DARKGRAY),
                createText(ChangeDirectoryCommand.COMMAND_WORD, Color.GREEN),
                createText("..", Color.BLUE),
                createText(" to traverse up", Color.DARKGRAY)
        );
        return commandFormat;
    }

    private static TextFlow getExitFormat() {
        commandFormat.getChildren().addAll(
                createText(ExitCommand.COMMAND_WORD, Color.GREEN)
        );
        return commandFormat;
    }

    private static TextFlow getHelpFormat() {
        commandFormat.getChildren().addAll(
                createText(HelpCommand.COMMAND_WORD, Color.GREEN),
                createText(String.format(" %s %s %s", "[", "<command word>", "]"), Color.DARKGRAY)
        );
        return commandFormat;
    }
}
