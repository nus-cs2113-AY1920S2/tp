package seedu.nuke.command.deletecommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_DELETE_ABORTED;
import static seedu.nuke.util.Message.MESSAGE_DELETE_CATEGORY_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_INVALID_DELETE_INDICES;
import static seedu.nuke.util.Message.MESSAGE_NO_CATEGORIES_FOUND;
import static seedu.nuke.util.Message.messageConfirmDeleteCategory;
import static seedu.nuke.util.Message.messagePromptDeleteCategoryIndices;

public class DeleteCategoryCommandTest {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("delc");
        assertEquals(MESSAGE_NO_CATEGORIES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delc Lab");
        assertEquals(MESSAGE_NO_CATEGORIES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delc Lab -e");
        assertEquals(MESSAGE_NO_CATEGORIES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delc Lab -a");
        assertEquals(MESSAGE_NO_CATEGORIES_FOUND, result.getFeedbackToUser());
    }


    @Test
    public void testNormalCategoryList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");

        result = Executor.executeCommand("delc");
        ArrayList<Category> categories = new ArrayList<>();
        try {
            categories.addAll(ModuleManager.getModule("CS2113").getCategories().getCategoryList());
            categories.addAll(ModuleManager.getModule("CS3235").getCategories().getCategoryList());
        } catch (ModuleManager.ModuleNotFoundException e) {
            System.out.println("Cannot find the module");
        }
        Comparator<Category> sortByModule =
                Comparator.comparing(category -> category.getParent().getModuleCode());
        Comparator<Category> sortByCategory =
                Comparator.comparing(Category::getCategoryName);

        categories.sort(sortByModule.thenComparing(sortByCategory));
        assertEquals(messagePromptDeleteCategoryIndices(new ArrayList<>(categories)), result.getFeedbackToUser());
        result = Executor.executeCommand("n");
        assertEquals(MESSAGE_INVALID_DELETE_INDICES, result.getFeedbackToUser());

        result = Executor.executeCommand("delc Lab");
        categories.clear();
        try {
            categories.add(ModuleManager.getCategory("CS2113", "Lab"));
            categories.add(ModuleManager.getCategory("CS3235", "Lab"));
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException e) {
            System.out.println("Cannot find the module or category");
        }
        assertEquals(messagePromptDeleteCategoryIndices(new ArrayList<>(categories)), result.getFeedbackToUser());
        result = Executor.executeCommand("1");
        assertEquals("Confirm delete these categories?\nLab\n", result.getFeedbackToUser());
        result = Executor.executeCommand("y");
        assertEquals(MESSAGE_DELETE_CATEGORY_SUCCESS, result.getFeedbackToUser());

        result = Executor.executeCommand("delc Tutorial -m CS2113");
        try {
            assertEquals(messageConfirmDeleteCategory(ModuleManager.getCategory("CS2113", "Tutorial")),
                    result.getFeedbackToUser());
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException e) {
            System.out.println("Cannot find the module or category");
        }
        result = Executor.executeCommand("n");
        assertEquals(MESSAGE_DELETE_ABORTED, result.getFeedbackToUser());
    }
}
