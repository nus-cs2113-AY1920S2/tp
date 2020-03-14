package seedu.nuke.format;

import seedu.nuke.data.task.Task;

import java.util.ArrayList;
import java.util.Comparator;

public class ListCreator {
    private static final String LIST_DIVIDER = String.format("%s%s%s\n", "+", "-".repeat(78), "+");
    private static final String SEPARATOR = "|";

    private static String addSpaces(int numberOfSpaces) {
        return " ".repeat(numberOfSpaces);
    }

    public static void sortTaskList(ArrayList<Task> toSort) {
        Comparator<Task> sortByModule =
                Comparator.comparing(task -> task.getParentCategory().getParentModule().getModuleCode());
        Comparator<Task> sortByCategory =
                Comparator.comparing(task -> task.getParentCategory().getCategoryName());
        Comparator<Task> sortByTask =
                Comparator.comparing(Task::getDescription);

        toSort.sort(sortByModule.thenComparing(sortByCategory).thenComparing(sortByTask));
    }

    public static String createTaskListTable(ArrayList<Task> taskList) {
        sortTaskList(taskList);
        StringBuilder taskListTable = new StringBuilder();

        taskListTable.append(createTaskListTableHeader());

        for (Task task : taskList) {
            String module = task.getParentCategory().getParentModule().getModuleCode();
            String category = task.getParentCategory().getCategoryName();
            String taskDescription = task.getDescription();
            String deadline = (task.getDeadline() != null) ? task.getDeadline().toString() : "-NIL-";

            taskListTable.append(String.format("%s%s%s%s%s%s%s\n",
                    centraliseText(fitText(module, 10), 10), SEPARATOR,
                    centraliseText(fitText(category, 18), 18), SEPARATOR,
                    centraliseText(fitText(taskDescription, 24), 24), SEPARATOR,
                    centraliseText(fitText(deadline, 24), 24)
            ));
        }

        taskListTable.append(LIST_DIVIDER);

        return taskListTable.toString();
    }

    private static String createTaskListTableHeader() {
        StringBuilder header = new StringBuilder();

        header.append(LIST_DIVIDER);
        header.append(String.format("%s%s%s%s%s%s%s\n",
                centraliseText("MODULE", 10), SEPARATOR,
                centraliseText("CATEGORY", 18), SEPARATOR,
                centraliseText("TASK", 24), SEPARATOR,
                centraliseText("DEADLINE", 24)
        ));
        header.append(LIST_DIVIDER);

        return header.toString();
    }

    private static String centraliseText(String text, int maximumLength) {
        int numberOfSpaces = (maximumLength - text.length()) / 2;
        return addSpaces(numberOfSpaces) + text + addSpaces(numberOfSpaces);
    }

    private static String fitText(String text, int maximumLength) {
        if (text.length() <= maximumLength) {
            int padLength = maximumLength - text.length();
            return text + addSpaces(padLength);
        } else {
            // Truncate and add ellipses
            return text.substring(0 , maximumLength-3) + "...";
        }
    }
}
