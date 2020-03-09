package seedu.nuke.data.taskCategory;

import seedu.nuke.exception.DataNotFoundException;

import java.util.ArrayList;

public class TaskCategoryList {
    ArrayList<TaskCategory> taskCategoryList;

    public TaskCategoryList() {
        taskCategoryList = initialiseTaskCategories();
    }

    private ArrayList<TaskCategory> initialiseTaskCategories() {
        ArrayList<TaskCategory> initialTaskCategories = new ArrayList<>();
        initialTaskCategories.add(new TaskCategory("Lecture", 1));
        initialTaskCategories.add(new TaskCategory("Tutorial", 2));
        initialTaskCategories.add(new TaskCategory("Assignment", 4));
        initialTaskCategories.add(new TaskCategory("Lab", 3));

        return initialTaskCategories;
    }

    public void add(TaskCategory toAdd) {
        taskCategoryList.add(toAdd);
    }

    public void delete(TaskCategory toDelete) {
        taskCategoryList.remove(toDelete);
    }

    public void delete(String taskCategoryName) throws TaskCategoryNotFoundException {
        for (TaskCategory taskCategory : taskCategoryList) {
            if (taskCategory.getCategoryName().equals(taskCategoryName)) {
                taskCategoryList.remove(taskCategory);
                return;
            }
        }
        throw new TaskCategoryNotFoundException();
    }

    public static class TaskCategoryNotFoundException extends DataNotFoundException {}
}
