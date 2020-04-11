package seedu.nuke.util;

import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;
import seedu.nuke.directory.TaskTag;

import java.util.ArrayList;

/**
 * A utility class to create a sorted list table from an Array List.
 */
public class ListCreator {
    private static final String LIST_DIVIDER = String.format("%s%s%s\n", "+", "-".repeat(98), "+");
    private static final String SEPARATOR = "|";

    /**
     * Creates a general list table from a list of Strings.
     * @param listToShow
     *  The list to be converted to a table
     * @return
     *  The list table to show to the user
     */
    public static String createGeneralListTable(ArrayList<String> listToShow) {
        StringBuilder listTable = new StringBuilder(String.format("%s\n", LIST_DIVIDER));

        for (String listItem : listToShow) {
            listTable.append(String.format("%s\n", listItem));
        }

        listTable.append(LIST_DIVIDER);
        return listTable.toString();
    }


    /**
     * Creates a sorted module list table from a list of modules.
     *
     * @param moduleList
     *  The list of modules to be converted into a table
     * @return
     *  The sorted module list table
     */
    public static String createModuleListTable(ArrayList<Module> moduleList) {
        StringBuilder moduleListTable = new StringBuilder();

        moduleListTable.append(createModuleListTableHeader());

        int id = 1;
        for (Module module : moduleList) {
            String moduleCode = module.getModuleCode();
            String moduleTitle = (module.getTitle() != null) ? module.getTitle() : "-NIL-";

            moduleListTable.append(String.format("%s%s%s%s%s\n",
                    centraliseText(fitText(Integer.toString(id++), 4), 4), SEPARATOR,
                    centraliseText(fitText(moduleCode, 16), 16), SEPARATOR,
                    centraliseText(fitText(moduleTitle, 78), 78)
            ));
        }

        moduleListTable.append(LIST_DIVIDER);
        moduleListTable.append(String.format("Total modules: %d\n", moduleList.size()));
        moduleListTable.append(LIST_DIVIDER);

        return moduleListTable.toString();
    }

    /**
     * Creates the header of the module list table.
     *
     * @return
     *  The header of the module list table
     */
    private static String createModuleListTableHeader() {
        StringBuilder header = new StringBuilder();

        header.append(LIST_DIVIDER);
        header.append(String.format("%s%s%s%s%s\n",
                centraliseText("NO", 4), SEPARATOR,
                centraliseText("MODULE CODE", 16), SEPARATOR,
                centraliseText("MODULE TITLE", 78)
        ));
        header.append(LIST_DIVIDER);

        return header.toString();
    }

    /**
     * Creates a sorted category list table from a list of categories.
     *
     * @param categoryList
     *  The list of categories to be converted into a table
     * @return
     *  The sorted category list table
     */
    public static String createCategoryListTable(ArrayList<Category> categoryList) {
        StringBuilder categoryListTable = new StringBuilder();

        categoryListTable.append(createCategoryListTableHeader());

        int id = 1;
        for (Category category : categoryList) {
            String moduleCode = category.getParent().getModuleCode();
            String categoryName = category.getCategoryName();
            String priority = String.valueOf(category.getCategoryPriority());

            categoryListTable.append(String.format("%s%s%s%s%s%s%s\n",
                    centraliseText(fitText(Integer.toString(id++), 4), 4), SEPARATOR,
                    centraliseText(fitText(moduleCode, 16), 16), SEPARATOR,
                    centraliseText(fitText(categoryName, 72), 72), SEPARATOR,
                    centraliseText(fitText(priority, 5), 5)
            ));
        }

        categoryListTable.append(LIST_DIVIDER);
        categoryListTable.append(String.format("Total categories: %d\n", categoryList.size()));
        categoryListTable.append(LIST_DIVIDER);

        return categoryListTable.toString();
    }

    /**
     * Creates the header of the category list table.
     *
     * @return
     *  The header of the category list table
     */
    private static String createCategoryListTableHeader() {
        StringBuilder header = new StringBuilder();

        header.append(LIST_DIVIDER);
        header.append(String.format("%s%s%s%s%s%s%s\n",
                centraliseText("NO", 4), SEPARATOR,
                centraliseText("MODULE", 16), SEPARATOR,
                centraliseText("CATEGORY", 72), SEPARATOR,
                centraliseText("PTY", 5)
        ));
        header.append(LIST_DIVIDER);

        return header.toString();
    }

    /**
     * Creates a sorted task list table from a list of tasks.
     *
     * @param taskList
     *  The list of tasks to be converted into a table
     * @return
     *  The sorted task list table
     */
    public static String createTaskListTable(ArrayList<Task> taskList) {
        StringBuilder taskListTable = new StringBuilder();

        taskListTable.append(createTaskListTableHeader());

        int id = 1;
        for (Task task : taskList) {
            String moduleCode = task.getParent().getParent().getModuleCode();
            String categoryName = task.getParent().getCategoryName();
            String taskDescription = task.getDescription();
            String deadline = task.getDeadline().isPresent() ? task.getDeadline().toShow() : "-NIL-";
            String priority = String.valueOf(task.getPriority());
            String statusIcon = task.getStatusIcon();

            taskListTable.append(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s\n",
                    centraliseText(fitText(Integer.toString(id++), 4), 4), SEPARATOR,
                    centraliseText(fitText(moduleCode, 10), 10), SEPARATOR,
                    centraliseText(fitText(categoryName, 16), 16), SEPARATOR,
                    centraliseText(fitText(taskDescription, 25), 25), SEPARATOR,
                    centraliseText(fitText(deadline, 28), 28), SEPARATOR,
                    centraliseText(fitText(priority, 5), 5), SEPARATOR,
                    centraliseText(fitText(statusIcon, 6), 6)
            ));
        }

        taskListTable.append(LIST_DIVIDER);
        taskListTable.append(String.format("Total tasks: %d\n", taskList.size()));
        taskListTable.append(LIST_DIVIDER);

        return taskListTable.toString();
    }

    /**
     * Creates the header of the task list table.
     *
     * @return
     *  The header of the task list table
     */
    private static String createTaskListTableHeader() {
        StringBuilder header = new StringBuilder();

        header.append(LIST_DIVIDER);
        header.append(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s\n",
                centraliseText("NO", 4), SEPARATOR,
                centraliseText("MODULE", 10), SEPARATOR,
                centraliseText("CATEGORY", 16), SEPARATOR,
                centraliseText("TASK", 25), SEPARATOR,
                centraliseText("DEADLINE", 28), SEPARATOR,
                centraliseText("PTY", 5), SEPARATOR,
                centraliseText("DONE", 6)
        ));
        header.append(LIST_DIVIDER);

        return header.toString();
    }

    /**
     * create a sorted tag list of table from a list of tags.
     * @param tags the list of tags to be converted into table
     * @return a sorted list of table of tags
     */
    public static String createTagListTable(ArrayList<TaskTag> tags) {
        StringBuilder tagListTable = new StringBuilder();

        tagListTable.append(createTagListTableHeader());

        int id = 1;
        for (TaskTag tag: tags) {
            String moduleCode = tag.getParent().getParent().getParent().getModuleCode();
            String categoryName = tag.getParent().getParent().getCategoryName();
            String taskDescription = tag.getParent().getDescription();
            String tagInfo = tag.getTagInfo();

            tagListTable.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                    centraliseText(fitText(Integer.toString(id++), 4), 4), SEPARATOR,
                    centraliseText(fitText(moduleCode, 15), 15), SEPARATOR,
                    centraliseText(fitText(categoryName, 25), 25), SEPARATOR,
                    centraliseText(fitText(taskDescription, 32), 32), SEPARATOR,
                    centraliseText(fitText(tagInfo, 20), 20)
            ));
        }

        tagListTable.append(LIST_DIVIDER);
        tagListTable.append(String.format("Total tags: %d\n", tags.size()));
        tagListTable.append(LIST_DIVIDER);

        return tagListTable.toString();
    }

    private static String createTagListTableHeader() {
        StringBuilder header = new StringBuilder();

        header.append(LIST_DIVIDER);
        header.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                centraliseText("NO", 4), SEPARATOR,
                centraliseText("MODULE", 15), SEPARATOR,
                centraliseText("CATEGORY", 25), SEPARATOR,
                centraliseText("TASK", 32), SEPARATOR,
                centraliseText("TAG", 20)
        ));
        header.append(LIST_DIVIDER);

        return header.toString();
    }


    /**
     * Creates a sorted file list table from a list of files.
     *
     * @param fileList
     *  The list of files to be converted into a table
     * @return
     *  The sorted file list table
     */
    public static String createFileListTable(ArrayList<TaskFile> fileList) {
        StringBuilder fileListTable = new StringBuilder();

        fileListTable.append(createFileListTableHeader());

        int id = 1;
        for (TaskFile file : fileList) {
            String moduleCode = file.getParent().getParent().getParent().getModuleCode();
            String categoryName = file.getParent().getParent().getCategoryName();
            String taskDescription = file.getParent().getDescription();
            String fileName = file.getFileName();

            fileListTable.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                    centraliseText(fitText(Integer.toString(id++), 4), 4), SEPARATOR,
                    centraliseText(fitText(moduleCode, 15), 15), SEPARATOR,
                    centraliseText(fitText(categoryName, 20), 20), SEPARATOR,
                    centraliseText(fitText(taskDescription, 27), 27), SEPARATOR,
                    centraliseText(fitText(fileName, 30), 30)
            ));
        }

        fileListTable.append(LIST_DIVIDER);
        fileListTable.append(String.format("Total files: %d\n", fileList.size()));
        fileListTable.append(LIST_DIVIDER);

        return fileListTable.toString();
    }

    /**
     * Creates the header of the task list table.
     *
     * @return
     *  The header of the task list table
     */
    private static String createFileListTableHeader() {
        StringBuilder header = new StringBuilder();

        header.append(LIST_DIVIDER);
        header.append(String.format("%s%s%s%s%s%s%s%s%s\n",
                centraliseText("NO", 4), SEPARATOR,
                centraliseText("MODULE", 15), SEPARATOR,
                centraliseText("CATEGORY", 20), SEPARATOR,
                centraliseText("TASK", 27), SEPARATOR,
                centraliseText("FILE", 30)
        ));
        header.append(LIST_DIVIDER);

        return header.toString();
    }

    /**
     * Adds the specified number of spaces.
     *
     * @param numberOfSpaces
     *  The number of spaces to be added
     * @return
     *  A string containing the specified number of spaces
     */
    private static String addSpaces(int numberOfSpaces) {
        return " ".repeat(numberOfSpaces);
    }

    /**
     * Centralises the given text in a segment of given length by padding with spaces before and after the text.
     *
     * @param text
     *  The text to be centralised
     * @param segmentLength
     *  The length of the segment in which the text is centralised
     * @return
     *  The centralised text
     */
    private static String centraliseText(String text, int segmentLength) {
        int numberOfLeftSpaces = (segmentLength - text.length()) / 2;
        int numberOfRightSpaces = (segmentLength - text.length() + 1) / 2;
        return addSpaces(numberOfLeftSpaces) + text + addSpaces(numberOfRightSpaces);
    }

    /**
     * Fits the given text into a segment of given length, truncating the text with ellipses if the text exceeds
     * the segment length.
     *
     * @param text
     *  The text to be fitted
     * @param segmentLength
     *  The length of the segment in which the text is fitted
     * @return
     *  The fitted text
     */
    private static String fitText(String text, int segmentLength) {
        String ellipses = "...";
        return (text.length() > segmentLength) ? (text.substring(0, segmentLength - 3) + ellipses) : text;
    }
}
